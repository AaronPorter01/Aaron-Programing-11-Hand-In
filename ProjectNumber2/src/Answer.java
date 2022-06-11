public class Answer
{
    // variable declarations
    private String answer;
    private boolean isCorrect;

    Answer(String answer, boolean isCorrect)
    {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    // getters and setters
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
