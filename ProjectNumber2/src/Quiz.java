import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Quiz
{
    private ArrayList<Question> questions;
    private String title;

    Quiz(String title)
    {
        this.title = title;
        questions = new ArrayList<>();
    }

    public void writeToFile() throws IOException
    {
        // write
        FileWriter fw = new FileWriter(title + "_Questions.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (Question q : questions)
        {
            bw.write(q.toString() + ",\r");
            for (Answer a : q.getAnswers())
            {
                bw.write(a.toString() + "." + a.isCorrect() + ",\r");
            }
            bw.write(";\r");
        }
        bw.close();
    }

    public ArrayList<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions)
    {
        this.questions = questions;
    }

    public String toString()
    {
        return title;
    }
}
