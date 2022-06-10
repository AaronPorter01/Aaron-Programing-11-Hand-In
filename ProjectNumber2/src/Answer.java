public class Answer
{
    private String answer;
    private boolean isCorrect;

    Answer(String answer, boolean isCorrect)
    {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String toString()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public boolean isCorrect()
    {
        return isCorrect;
    }

    public void setCorrect(boolean correct)
    {
        isCorrect = correct;
    }
}
