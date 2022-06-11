import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class QuizViewController
{
    // variable declarations
    public Text txtQuizTitle;
    public Text txtScore;
    public Text txtQuestionNumber;
    public Text txtQuestion;
    public CheckBox answerBox1;
    public CheckBox answerBox2;
    public CheckBox answerBox3;
    public CheckBox answerBox4;
    public Button btnNextQuestion;
    public int question = 0;
    public int quizLength = 0;
    public int score = 0;
    public AnchorPane startPane;
    public AnchorPane quizPane;
    public Quiz quiz;
    public CheckBox answer;
    public Button btnSubmit;
    public Text txtEndScore;
    public AnchorPane endPane;
    public Text txtEndPercentage;
    public Text txtEndMessage;
    public double percentageScore;

    // called to set the quiz
    public void load (Quiz _quiz)
    {
        quiz = _quiz;
    }

    // switches to next question
    public void nextQuestion(ActionEvent actionEvent)
    {
        // check if quiz is over
        if (question + 1 == quizLength)
        {
            // set end screen text
            txtEndScore.setText("Score: " + score + "/" + quizLength);
            txtEndPercentage.setText(getPercentageText(score, quizLength));
            txtEndMessage.setText(getScreenMessage(percentageScore));
            // enables and disables correct panes
            endPane.setDisable(false);
            endPane.setVisible(true);
            startPane.setVisible(false);
            startPane.setDisable(true);
            quizPane.setVisible(false);
            quizPane.setDisable(true);
            return;
        }

        btnNextQuestion.setDisable(true);
        // updates current question and sets the question text display
        question++;
        txtQuestionNumber.setText("Question: " + (question + 1));

        // resets answer boxes
        answerBox1.setDisable(false);
        answerBox2.setDisable(false);
        answerBox3.setDisable(false);
        answerBox4.setDisable(false);
        answerBox1.setTextFill(Color.BLACK);
        answerBox2.setTextFill(Color.BLACK);
        answerBox3.setTextFill(Color.BLACK);
        answerBox4.setTextFill(Color.BLACK);
        answerBox1.setSelected(false);
        answerBox2.setSelected(false);
        answerBox3.setSelected(false);
        answerBox4.setSelected(false);

        // set questions and answers to display for current question
        setAnswer();
        txtQuestion.setText(quiz.getQuestions().get(question).toString());

        System.out.println("Next");
    }

    // called when an answer is selected
    public void answered(ActionEvent actionEvent)
    {
        btnSubmit.setDisable(false);

        // get the checkbox that was answered
        answer = (CheckBox) actionEvent.getSource();
        answer.setId(((CheckBox) actionEvent.getSource()).getId());

        if (!answer.isSelected())
            return;

        // set other answer boxes to be false
        if (!answer.getId().equals("1"))
            answerBox1.setSelected(false);
        if (!answer.getId().equals("2"))
            answerBox2.setSelected(false);
        if (!answer.getId().equals("3"))
            answerBox3.setSelected(false);
        if (!answer.getId().equals("4"))
            answerBox4.setSelected(false);

        System.out.println(answer.getId());
    }

    // called to start quiz and load any remaining information
    public void startQuiz(ActionEvent actionEvent)
    {
        // set correct pane to be displayed
        startPane.setVisible(false);
        startPane.setDisable(true);
        quizPane.setVisible(true);
        quizPane.setDisable(false);
        endPane.setDisable(true);
        endPane.setVisible(false);

        // set variables and set text to display correctly
        txtQuizTitle.setText(quiz.toString());
        txtQuestion.setText(quiz.getQuestions().get(0).toString());
        quizLength = quiz.getQuestions().size();
        System.out.println(quizLength);
        txtScore.setText("Score: 0/" + quizLength);
        setAnswer();
    }

    // sets answer boxes to have correct text
    // sets answer boxes to be visible only if they have text to display
    public void setAnswer()
    {
        answerBox1.setText(quiz.getQuestions().get(question).getAnswers().get(0).toString());
        answerBox1.setVisible(!answerBox1.getText().isEmpty());
        answerBox2.setText(quiz.getQuestions().get(question).getAnswers().get(1).toString());
        answerBox2.setVisible(!answerBox2.getText().isEmpty());
        answerBox3.setText(quiz.getQuestions().get(question).getAnswers().get(2).toString());
        answerBox3.setVisible(!answerBox3.getText().isEmpty());
        answerBox4.setText(quiz.getQuestions().get(question).getAnswers().get(3).toString());
        answerBox4.setVisible(!answerBox4.getText().isEmpty());
    }

    // called to complete question and submit answer
    public void submitQuestion(ActionEvent actionEvent)
    {
        // sets correct answer int
        int correctAnswer = 0;
        for (int i = 0; i < quiz.getQuestions().get(question).getAnswers().size(); i++)
        {
            if (quiz.getQuestions().get(question).getAnswers().get(i).isCorrect())
                correctAnswer = i + 1;

        }

        // check that id of user answer is the same as correct answer int
        if (isAnswerCorrect(correctAnswer, Integer.parseInt(answer.getId())))
        {
            // increase the score and update display
            score++;
            txtScore.setText("Score: " + score + "/" + quizLength);
        }

        CheckBox[] answerBoxes = {answerBox1, answerBox2, answerBox3, answerBox4};

        // set answer boxes to red if they are wrong and green if they are correct
        for (CheckBox answerBox : answerBoxes)
        {
            if (isAnswerCorrect(correctAnswer, Integer.parseInt(answerBox.getId())))
                answerBox.setTextFill(Color.GREEN);
            else
                answerBox.setTextFill(Color.RED);

            answerBox.setDisable(true);
        }

        // disable submit button and enable next button
        btnSubmit.setDisable(true);
        btnNextQuestion.setDisable(false);
    }

    // get the percentage from users score
    public String getPercentageText(double score, double total)
    {
        int percentageInt;
        String percentageDisplay;

        // divide score by total and then multiply by 100
        // cast to in to not show all decimal places
        percentageScore = ((score / total) * 100);
        percentageInt = (int) ((score / total) * 100);
        // create display string
        percentageDisplay = "You got " + percentageInt + "%";

        System.out.println(percentageDisplay);

        return percentageDisplay;
    }

    // compare correct answer id and user answer id
    public boolean isAnswerCorrect(int correctAnswerId, int answerId)
    {
        if (correctAnswerId == answerId)
        {
            //System.out.println("Answer " + answerId + " is correct!");
            return true;
        }
        else
        {
            //System.out.println("Answer " + answerId + " is incorrect. " + correctAnswerId + " was the correct answer.");
            return false;
        }
    }

    // gives different message depending on users final percentage
    public String getScreenMessage(double percentageScore)
    {
        if (percentageScore <= 25)
            return "Nice Try!";
        else if (percentageScore <= 50)
            return "You're Getting There!";
        else if (percentageScore <= 75)
            return "Great Job!";
        else if (percentageScore <= 95)
            return "Awesome Work!";
        else
            return "Bravo! Amazing! Spectacular!";
    }
}
