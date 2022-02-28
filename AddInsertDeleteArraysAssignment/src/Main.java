import java.util.Scanner;

public class Main
{
    static int[] addValues(int[] array, int numOfValues, int[] values)
    {
        // set the size of the new array that's going to replace the old one
        int[] newArray = new int[array.length + numOfValues];

        // set values in that new array
        for (int i = 0; i < newArray.length; i++)
        {
            // set the pre-existing values
            if (i < array.length)
                newArray[i] = array[i];
            // set the new values
            else
                newArray[i] = values[i - array.length];
        }

        return newArray;
    }

    static int[] deleteValues(int[] array, int numOfValues)
    {
        // set size of new array
        int[] newArray = new int[array.length - numOfValues];

        // set the pre-existing values
        for (int i = 0; i < newArray.length; i++)
        {
            newArray[i] = array[i];
        }

        return newArray;
    }

    static int[] insertValues(int[] array, int value, int valuePosition)
    {
        // set size of new array
        int[] newArray = new int[array.length + 1];
        // set index that will keep track of what part of the original array the program is on to 0
        int arrayIndex = 0;

        for (int i = 0; i < newArray.length; i++)
        {
            // check if i is at the values position
            // if it is not put in the original array number and increment the index
            if (i != valuePosition)
            {
                newArray[i] = array[arrayIndex];
                arrayIndex++;
            }
            // if it is set the value at that position to the value entered by the user
            else
            {
                newArray[i] = value;
            }
        }

        return newArray;
    }

    public static void main(String[] args)
    {
        // create scanner for input
        Scanner scanner = new Scanner(System.in);

        // declare variables
        int[] intArray;
        int numOfValues;
        int valuePosition;
        int insertValue;
        int[] values;
        String action;
        String[] validActions = new String[4];
        boolean validChoice = true;
        boolean on = true;

        // set valid actions
        validActions[0] = "a";
        validActions[1] = "d";
        validActions[2] = "i";
        validActions[3] = "x";

        // get size of original array
        System.out.println("Enter size of the array: ");
        intArray = new int[scanner.nextInt()];

        // get values of original array for each position
        for (int i = 0; i < intArray.length; i++)
        {
            System.out.println("Enter value at position " + i + " then hit enter");
            intArray[i] = scanner.nextInt();
        }

        // print array for user to see
        System.out.println("\nYour array:");
        for (int i = 0; i < intArray.length; i++)
        {
            System.out.print(i + "[" + intArray[i] + "]\t");
        }

        do
        {
            // ask what the user wants to do next
            do
            {
                System.out.println("\nWhat would you like to do next? a = add, d = delete, i = insert, x = stop:");
                action = scanner.next();

                // check that choice is valid
                for (int i = 0; i < validActions.length; i++)
                {
                    if (action.equals(validActions[i]))
                    {
                        validChoice = true;
                        break;
                    }
                    else
                        validChoice = false;
                }

            } while (!validChoice);

            if (action.equals("a"))
            {
                do
                {
                    // get number of values to add to the array
                    System.out.println("Enter number of values to add between 1 and " + intArray.length + ": ");
                    numOfValues = scanner.nextInt();
                } while (numOfValues < 1 || numOfValues > intArray.length);

                // set values array length to the number of values the user wants to add
                values = new int[numOfValues];
                for (int i = 0; i < numOfValues; i++)
                {
                    // get value for each position in array
                    System.out.println("Enter value at position " + (i + intArray.length) + " then hit enter");
                    values[i] = scanner.nextInt();
                }

                // call addValues function
                intArray = addValues(intArray, numOfValues, values);
                System.out.println("\nYour array:");
                // print out new array
                for (int i = 0; i < intArray.length; i++)
                {
                    System.out.print(i + "[" + intArray[i] + "]\t");
                }
                System.out.print("\n");
            }
            else if (action.equals("d"))
            {
                // check that user entered valid number
                do
                {
                    // get number of values to add to the array
                    System.out.println("Enter number of values to delete between 1 and " + intArray.length + ":");
                    numOfValues = scanner.nextInt();
                } while (numOfValues < 1 || numOfValues > intArray.length);

                // call deleteValues function
                intArray = deleteValues(intArray, numOfValues);

                // print out new array
                System.out.println("\nYour array:");
                for (int i = 0; i < intArray.length; i++)
                {
                    System.out.print(i + "[" + intArray[i] + "]\t");
                }
                System.out.print("\n");
            }
            else if (action.equals("i"))
            {
                // get value to be inserted
                System.out.println("Enter value:");
                insertValue = scanner.nextInt();
                // check that value position is valid
                do
                {
                    // get position to insert value at
                    System.out.println("Enter value position between 0 and " + intArray.length + ":");
                    valuePosition = scanner.nextInt();
                } while (valuePosition > intArray.length);

                // call insertValue function
                intArray = insertValues(intArray, insertValue, valuePosition);

                // print out new array
                System.out.println("\nYour array:");
                for (int i = 0; i < intArray.length; i++)
                {
                    System.out.print(i + "[" + intArray[i] + "]\t");
                }
                System.out.print("\n");
            }
            else if (action.equals("x"))
            {
                System.out.println("\nGoodbye");
                on = false;
            }
        } while (on);
    }
}
