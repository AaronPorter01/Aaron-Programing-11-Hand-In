import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller
{
    public ListView<Quiz> quizList;
    public Text txtTitle;
    public Text txtQuestions;
    public Button btnDelete;
    public Button btnSaveQuiz;
    public ListView<Question> quizQuestionsList;
    public TextField txtFieldQuizTitle;
    public TextField txtFieldQuestion;
    public TextField txtFieldAnswer1;
    public TextField txtFieldAnswer2;
    public TextField txtFieldAnswer3;
    public TextField txtFieldAnswer4;
    public Button btnSaveQuestion;
    public Button btnDeleteQuestion;
    public CheckBox checkBoxAnswer1;
    public CheckBox checkBoxAnswer2;
    public CheckBox checkBoxAnswer3;
    public CheckBox checkBoxAnswer4;
    public Tab tabCreateQuiz;
    public TabPane tabPane;
    public Button btnStart;
    public Button btnEdit;

    public void startQuiz(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quizView.fxml"));
        Parent root = loader.load();
        QuizViewController viewController = loader.getController();
        viewController.load(quizList.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setTitle("Quiz Me! - Quiz");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void deleteQuiz(ActionEvent actionEvent)
    {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        quizList.getItems().remove(quiz);

        File temp = new File("_temp.txt");
        File folder = new File(temp.getAbsoluteFile().getParent());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
        {
            if (file.getName().endsWith(quiz.toString() + "_Questions.txt"))
            {
                file.delete();
            }
        }
    }

    public void loadQuizzes(ActionEvent actionEvent) throws IOException
    {
        quizList.getItems().clear();
        ArrayList<Question> questions = new ArrayList<>();
        File temp = new File("_temp.txt");
        File folder = new File(temp.getAbsoluteFile().getParent());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
        {
            if (file.getName().endsWith(".txt"))
            {
                questions.addAll(QuizDataLoader.loadGroup(file.getName()));
                String quizName = file.getName().replace("_Questions.txt", "");
                Quiz quiz = new Quiz(quizName);
                quiz.getQuestions().addAll(questions);
                quizList.getItems().add(quiz);
                System.out.println("found");
            }
        }
        System.out.println(folder);
        System.out.println(questions);
        System.out.println(questions.get(0).getAnswers());

    }

    public void selectQuiz(MouseEvent mouseEvent)
    {
        txtTitle.setText("Title: " + quizList.getSelectionModel().getSelectedItem().toString());
        txtQuestions.setText("Questions: " + quizList.getSelectionModel().getSelectedItem().getQuestions().size());
        btnStart.setDisable(false);
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void createNewQuiz(ActionEvent actionEvent)
    {
        quizQuestionsList.getItems().clear();
        txtFieldQuizTitle.setText("");
        txtFieldQuestion.setText("");
        txtFieldAnswer1.setText("");
        txtFieldAnswer2.setText("");
        txtFieldAnswer3.setText("");
        txtFieldAnswer4.setText("");
        checkBoxAnswer1.setSelected(false);
        checkBoxAnswer2.setSelected(false);
        checkBoxAnswer3.setSelected(false);
        checkBoxAnswer4.setSelected(false);
        btnDeleteQuestion.setDisable(true);
    }

    public void saveQuiz(ActionEvent actionEvent) throws IOException
    {
        if (txtFieldQuizTitle.getText().isEmpty())
            return;

        for (int i = 0; i < quizList.getItems().size(); i++)
        {
            if (quizList.getItems().get(i).toString().equals(txtFieldQuizTitle.getText()))
            {
                quizList.getItems().remove(quizList.getItems().get(i));
            }
        }

        Quiz quiz = new Quiz(txtFieldQuizTitle.getText());

        //if (quizQuestionsList.getItems().isEmpty())
          //  return;

        for (Question q : quizQuestionsList.getItems())
        {
            quiz.getQuestions().add(q);
        }

        quizList.getItems().add(quiz);
        quiz.writeToFile();
    }

    public void newQuestion(ActionEvent actionEvent)
    {
        txtFieldQuestion.setText("");
        txtFieldAnswer1.setText("");
        txtFieldAnswer2.setText("");
        txtFieldAnswer3.setText("");
        txtFieldAnswer4.setText("");
        checkBoxAnswer1.setSelected(false);
        checkBoxAnswer2.setSelected(false);
        checkBoxAnswer3.setSelected(false);
        checkBoxAnswer4.setSelected(false);
    }

    public void saveQuestion(ActionEvent actionEvent)
    {
        for (int i = 0; i < quizQuestionsList.getItems().size(); i++)
        {
            if (quizQuestionsList.getItems().get(i).toString().equals(txtFieldQuestion.getText()))
            {
                quizQuestionsList.getItems().remove(quizQuestionsList.getItems().get(i));
            }
        }

        Question question = new Question(txtFieldQuestion.getText());

        if (!txtFieldAnswer1.getText().isEmpty())
            question.getAnswers().add(new Answer(txtFieldAnswer1.getText(), checkBoxAnswer1.isSelected()));
        if (!txtFieldAnswer2.getText().isEmpty())
            question.getAnswers().add(new Answer(txtFieldAnswer2.getText(), checkBoxAnswer2.isSelected()));
        if (!txtFieldAnswer3.getText().isEmpty())
            question.getAnswers().add(new Answer(txtFieldAnswer3.getText(), checkBoxAnswer3.isSelected()));
        if (!txtFieldAnswer4.getText().isEmpty())
            question.getAnswers().add(new Answer(txtFieldAnswer4.getText(), checkBoxAnswer4.isSelected()));

        if (question.getAnswers().isEmpty())
            return;

        quizQuestionsList.getItems().add(question);
    }

    public void deleteQuestion(ActionEvent actionEvent) throws IOException
    {
        Question question = quizQuestionsList.getSelectionModel().getSelectedItem();
        quizQuestionsList.getItems().remove(question);

        File temp = new File("_temp.txt");
        File folder = new File(temp.getAbsoluteFile().getParent());
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles)
        {
            if (file.getName().equals(txtFieldQuestion.getText()))
            {
                if (quizQuestionsList.getItems().isEmpty())
                    return;
                else
                    QuizDataLoader.deleteQuestion(txtFieldQuizTitle.getText(), quizQuestionsList.getItems());
            }
        }

        txtFieldQuestion.setText("");
        txtFieldAnswer1.setText("");
        txtFieldAnswer2.setText("");
        txtFieldAnswer3.setText("");
        txtFieldAnswer4.setText("");
        checkBoxAnswer1.setSelected(false);
        checkBoxAnswer2.setSelected(false);
        checkBoxAnswer3.setSelected(false);
        checkBoxAnswer4.setSelected(false);
    }

    public void markTrue(ActionEvent actionEvent)
    {
        CheckBox answer = (CheckBox) actionEvent.getSource();

        if (!answer.isSelected())
            return;

        if (!answer.getId().equals("1") || txtFieldAnswer1.getText().isEmpty())
            checkBoxAnswer1.setSelected(false);
        if (!answer.getId().equals("2") || txtFieldAnswer2.getText().isEmpty())
            checkBoxAnswer2.setSelected(false);
        if (!answer.getId().equals("3") || txtFieldAnswer3.getText().isEmpty())
            checkBoxAnswer3.setSelected(false);
        if (!answer.getId().equals("4") || txtFieldAnswer4.getText().isEmpty())
            checkBoxAnswer4.setSelected(false);

        System.out.println(answer.getId());
    }

    public void selectedQuizQuestion(MouseEvent mouseEvent)
    {
        CheckBox[] checkBoxes = {checkBoxAnswer1, checkBoxAnswer2, checkBoxAnswer3, checkBoxAnswer4};

        Question question = quizQuestionsList.getSelectionModel().getSelectedItem();
        txtFieldQuestion.setText(question.toString());
        txtFieldAnswer1.setText(question.getAnswers().get(0).toString());
        txtFieldAnswer2.setText(question.getAnswers().get(1).toString());
        txtFieldAnswer3.setText(question.getAnswers().get(2).toString());
        txtFieldAnswer4.setText(question.getAnswers().get(3).toString());

        btnDeleteQuestion.setDisable(false);

        for (int i = 0; i < question.getAnswers().size(); i++)
        {
            if (question.getAnswers().get(i).isCorrect())
            {
                checkBoxes[i].setSelected(true);
            }
            else
            {
                checkBoxes[i].setSelected(false);
            }
        }
    }

    public void editQuiz(ActionEvent actionEvent)
    {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        tabPane.getSelectionModel().select(tabCreateQuiz);
        quizQuestionsList.getItems().clear();
        quizQuestionsList.getItems().addAll(quiz.getQuestions());
        txtFieldQuizTitle.setText(quiz.toString());
    }
}
