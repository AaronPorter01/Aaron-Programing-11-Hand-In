import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDataLoader
{
    // loads all questions in save file
    public static ArrayList loadQuestions(String fileName) throws IOException
    {
        ArrayList<Question> questions = new ArrayList<>();

        // set up readers
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String questionStrings = "";

        // read lines until ";" is found
        while ((line = br.readLine()) != null)
        {
            if (!line.equals(";"))
            {
                questionStrings += line;
            }
            else
            {
                parseQuiz(questionStrings, questions);
                questionStrings = "";
            }
        }

        br.close();

        return questions;
    }

    // called to remove a question from a save file
    public static void  deleteQuestion(String fileName, List<Question> questions) throws IOException
    {
        Quiz quiz = new Quiz(fileName);
        quiz.getQuestions().addAll(questions);
        quiz.writeToFile();
    }

    // gets information from save file and assigns variables to it
    private static void parseQuiz(String questionStrings, ArrayList<Question> questions)
    {
        // declare all variables
        ArrayList<Integer> positions = new ArrayList<>();
        String question = "";
        ArrayList<String> answerStrings = new ArrayList<>();
        // set each beginning and end positions to correct spots
        for (int i = -1; (i = questionStrings.indexOf(",", i + 1)) != -1; i++)
        {
            positions.add(i);
        }

        // set variables based on txt file
        question = questionStrings.substring(0, positions.get(0));

        answerStrings.add(questionStrings.substring(positions.get(0) + 1, positions.get(1)));
        answerStrings.add(questionStrings.substring(positions.get(1) + 1, positions.get(2)));
        answerStrings.add(questionStrings.substring(positions.get(2) + 1, positions.get(3)));
        answerStrings.add(questionStrings.substring(positions.get(3) + 1, positions.get(4)));

        // add answer strings and true/false values to question
        Question temp = new Question(question);
        for (String a : answerStrings)
        {
            System.out.println();
            System.out.println();
            temp.getAnswers().add(new Answer(
                    a.substring(0, (a.indexOf("."))),
                    Boolean.parseBoolean(a.substring(a.indexOf(".") + 1))));
        }
        questions.add(temp);
    }
}
