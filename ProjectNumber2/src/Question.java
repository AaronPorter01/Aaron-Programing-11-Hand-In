import java.util.ArrayList;

public class Question
{
    private ArrayList<Answer> answers;
    private String question;
    private boolean correctAnswer;

    Question(String question)
    {
        this.question = question;
        answers = new ArrayList<>();
        correctAnswer = false;
    }

    public ArrayList<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers)
    {
        this.answers = answers;
    }

    public String toString()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public boolean isCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }
}
