import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // copy each line into an ArrayList
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

        // search for word in each line
        ArrayList<Integer> linePositions = searchLines(word, lines);

        // print which lines have the word
        for (int i = 0; i < lines.size(); i++)
        {
            boolean inLine = false;

            // check the lines index against the lines that have the word in them
            for (int j = 0; j < linePositions.size(); j++)
            {
                // check that the word is in the line
                if (i == linePositions.get(j) && !inLine)
                {
                    inLine = true;
                    // search for the words positions
                    ArrayList<Integer> wordPosition = searchLineForWord(word ,lines.get(i));
                    // print out each position of the word
                    System.out.print("Line " + (i + 1) + " at: ");
                    for (int k = 0; k < wordPosition.size(); k++)
                    {
                        System.out.print(wordPosition.get(k) + " ");
                        if (k == wordPosition.size() - 1)
                        {
                            System.out.print("\n");
                        }
                    }
                }
            }

            // if the word was not in the line print out that it was not found
            if (!inLine)
            {
                System.out.println("Line " + (i + 1) + ": Not Found");
            }
        }
    }

    // finds word in a line
    public static ArrayList<Integer> searchLineForWord(String word, String line)
    {
        // holds all positions the word is found at
        ArrayList<Integer> wordsPosition = new ArrayList<>();

        // create a line that has a no punctuation
        String searchLine = " " + line.replaceAll("\\p{Punct}", " ").toLowerCase();

        // adds current index to the array
        for (int i = -1; (i = searchLine.indexOf(word, i + 1)) != -1; i++)
        {
            wordsPosition.add(i - 1);
        }

        return wordsPosition;
    }

    // finds the lines the word is in
    public static ArrayList<Integer> searchLines(String word, ArrayList<String> lines)
    {
        // holds all line index's the word is found in
        ArrayList<Integer> linePositions = new ArrayList<>();

        // check each line for word
        for (int i = 0; i < lines.size(); i++)
        {
            // create a line that has a no punctuation
            String findLine = " " + lines.get(i).replaceAll("\\p{Punct}", " ").toLowerCase();

            // check contains word
            boolean found = findLine.contains(" " + word.toLowerCase() + " ");

            if (found)
            {
                // add line to positions
                linePositions.add(i);
            }
        }

        return linePositions;
    }
}
