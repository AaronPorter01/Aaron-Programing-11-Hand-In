import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // copy each line in ArrayList
        ArrayList<String> lines = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null)
        {
            lines.add(line);
        }
        br.close();

        // get search word
        System.out.println("Search Word: ");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        // make sure it's a word
        for (int i = 0; i < word.length(); i++)
        {
            if (!Character.isLetter(word.charAt(i)))
            {
                System.out.println("You can only use letters");
                return;
            }
        }

        // search for word
        ArrayList<Integer> positions = searchWord(word, lines);

        // print out found or not found message
       if (positions.isEmpty())
       {
           System.out.println("Word was not found");
       }
       else
       {
           System.out.print("Word found on lines: ");
           for (int i = 0; i < positions.size(); i++)
           {
               // add each position to message
               // offset by 1 to make sure it prints the correct line
               System.out.print((positions.get(i) + 1) + " ");
           }
           System.out.println("");
       }
    }

    public static ArrayList<Integer> searchWord(String word, ArrayList<String> lines)
    {
        ArrayList<Integer> positions = new ArrayList<>();

        // check each line for word
        for (int i = 0; i < lines.size(); i++)
        {
            // create a line that has a no punctuation
            String findLine = " " + lines.get(i).replaceAll("\\p{Punct}", " ");

            // check for word
            boolean found = findLine.contains(" " + word + " ");

            if (found)
            {
                // add line to positions
                positions.add(i);
            }
        }

        return positions;
    }
}
