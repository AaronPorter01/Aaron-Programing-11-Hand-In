import com.sun.deploy.ref.AppModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizViewController
{
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

    public void load (Quiz _quiz)
    {
        quiz = _quiz;
    }

    public void nextQuestion(ActionEvent actionEvent)
    {
        btnNextQuestion.setDisable(true);
        question++;
        txtQuestionNumber.setText("Question: " + (question + 1));

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

        setAnswerText();
        txtQuestion.setText(quiz.getQuestions().get(question).toString());

        System.out.println("Next");
    }

    /*public void previousQuestion(ActionEvent actionEvent)
    {
        question--;
        if (question == 1)
            btnPreviousQuestion.setDisable(true);

        btnNextQuestion.setDisable(false);
        txtQuestionNumber.setText("Question: " + question);

        System.out.println("Previous");
    }*/

    public void answered(ActionEvent actionEvent)
    {
        btnSubmit.setDisable(false);

        answer = (CheckBox) actionEvent.getSource();
        answer.setId(((CheckBox) actionEvent.getSource()).getId());

        if (!answer.isSelected())
            return;

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

    public void startQuiz(ActionEvent actionEvent)
    {
        startPane.setVisible(false);
        startPane.setDisable(true);
        quizPane.setVisible(true);
        quizPane.setDisable(false);

        txtQuizTitle.setText(quiz.toString());
        txtQuestion.setText(quiz.getQuestions().get(0).toString());
        quizLength = quiz.getQuestions().size();
        System.out.println(quizLength);
        txtScore.setText("Score: 0/" + quizLength);
        setAnswerText();
    }

    public void setAnswerText()
    {
        answerBox1.setText(quiz.getQuestions().get(question).getAnswers().get(0).toString());
        answerBox2.setText(quiz.getQuestions().get(question).getAnswers().get(1).toString());
        answerBox3.setText(quiz.getQuestions().get(question).getAnswers().get(2).toString());
        answerBox4.setText(quiz.getQuestions().get(question).getAnswers().get(3).toString());
    }

    public void submitQuestion(ActionEvent actionEvent)
    {
        int correctAnswer = 0;
        for (int i = 0; i < quiz.getQuestions().get(question).getAnswers().size(); i++)
        {
            if (quiz.getQuestions().get(question).getAnswers().get(i).isCorrect())
                correctAnswer = i + 1;

        }

        answerBox1.setDisable(true);
        answerBox2.setDisable(true);
        answerBox3.setDisable(true);
        answerBox4.setDisable(true);

        if ((question + 1) == quizLength)
            btnNextQuestion.setDisable(true);

        if (Integer.parseInt(answer.getId()) == correctAnswer)
        {
            score++;
            txtScore.setText("Score: " + score + "/" + quizLength);
        }

        if (Integer.parseInt(answerBox1.getId()) == correctAnswer)
            answerBox1.setTextFill(Color.GREEN);
        else
            answerBox1.setTextFill(Color.RED);
        if (Integer.parseInt(answerBox2.getId()) == correctAnswer)
            answerBox2.setTextFill(Color.GREEN);
        else
            answerBox2.setTextFill(Color.RED);
        if (Integer.parseInt(answerBox3.getId()) == correctAnswer)
            answerBox3.setTextFill(Color.GREEN);
        else
            answerBox3.setTextFill(Color.RED);
        if (Integer.parseInt(answerBox4.getId()) == correctAnswer)
            answerBox4.setTextFill(Color.GREEN);
        else
            answerBox4.setTextFill(Color.RED);

        btnSubmit.setDisable(true);

        if (question + 1 != quizLength)
            btnNextQuestion.setDisable(false);
    }
}
