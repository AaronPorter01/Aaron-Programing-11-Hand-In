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
    // variable declarations
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

    // starts selected quiz
    public void startQuiz(ActionEvent actionEvent) throws IOException
    {
        // new quiz window is created
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quizView.fxml"));
        Parent root = loader.load();
        QuizViewController viewController = loader.getController();
        // loads quiz into new windows
        viewController.load(quizList.getSelectionModel().getSelectedItem());

        // creates stage
        Stage stage = new Stage();
        stage.setTitle("Quiz Me! - Quiz");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    // deletes selected quiz
    public void deleteQuiz(ActionEvent actionEvent)
    {
        // removes quiz from list
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        quizList.getItems().remove(quiz);

        // deletes save file
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

    // loads saved quizzes
    public void loadQuizzes(ActionEvent actionEvent) throws IOException
    {
        // clear list and find all files in directory
        quizList.getItems().clear();
        File temp = new File("_temp.txt");
        File folder = new File(temp.getAbsoluteFile().getParent());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
        {
            // finds if _Questions.txt file
            if (file.getName().endsWith("_Questions.txt"))
            {
                // creates new quiz and adds it to the list
                ArrayList<Question> questions = new ArrayList<>();
                questions.addAll(QuizDataLoader.loadQuestions(file.getName()));
                String quizName = file.getName().replace("_Questions.txt", "");
                Quiz quiz = new Quiz(quizName);
                quiz.getQuestions().addAll(questions);
                quizList.getItems().add(quiz);
                System.out.println("found");
            }
        }
        System.out.println(folder);

        // reset displays and buttons
        txtTitle.setText("Title:");
        txtQuestions.setText("Questions:");
        btnStart.setDisable(true);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
    }

    // select quiz in list
    public void selectQuiz(MouseEvent mouseEvent)
    {
        // set text correctly and enable buttons
        txtTitle.setText("Title: " + quizList.getSelectionModel().getSelectedItem().toString());
        txtQuestions.setText("Questions: " + quizList.getSelectionModel().getSelectedItem().getQuestions().size());
        btnStart.setDisable(false);
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    // creates a fresh quiz
    public void createNewQuiz(ActionEvent actionEvent)
    {
        // reset fields, check boxes, and text
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

    // saves the current quiz
    public void saveQuiz(ActionEvent actionEvent) throws IOException
    {
        // checks to make sure it has a title
        if (txtFieldQuizTitle.getText().isEmpty())
            return;

        // looks through all other quizzes for one with the same name
        for (int i = 0; i < quizList.getItems().size(); i++)
        {
            // if one has the same name it removes it
            if (quizList.getItems().get(i).toString().equals(txtFieldQuizTitle.getText()))
            {
                quizList.getItems().remove(quizList.getItems().get(i));
            }
        }

        Quiz quiz = new Quiz(txtFieldQuizTitle.getText());

        // all created questions are added to a new quiz object
        for (Question q : quizQuestionsList.getItems())
        {
            quiz.getQuestions().add(q);
        }

        // new quiz is added to list of quizzes and saved to a .txt file
        quizList.getItems().add(quiz);
        quiz.writeToFile();

        // reset displays and buttons
        txtTitle.setText("Title:");
        txtQuestions.setText("Questions:");
        btnStart.setDisable(true);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
    }

    // creates a fresh question
    public void newQuestion(ActionEvent actionEvent)
    {
        // resets all fields and check boxes
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

    // saves current question
    public void saveQuestion(ActionEvent actionEvent)
    {
        // checks to make sure there is a question
        if (txtFieldQuestion.getText().isEmpty())
            return;

        // looks for any questions that are the same
        for (int i = 0; i < quizQuestionsList.getItems().size(); i++)
        {
            // replaces question if it's the same
            if (quizQuestionsList.getItems().get(i).toString().equals(txtFieldQuestion.getText()))
            {
                quizQuestionsList.getItems().remove(quizQuestionsList.getItems().get(i));
            }
        }

        Question question = new Question(txtFieldQuestion.getText());

        // answers are added to a new question object
        question.getAnswers().add(new Answer(txtFieldAnswer1.getText(), checkBoxAnswer1.isSelected()));
        question.getAnswers().add(new Answer(txtFieldAnswer2.getText(), checkBoxAnswer2.isSelected()));
        question.getAnswers().add(new Answer(txtFieldAnswer3.getText(), checkBoxAnswer3.isSelected()));
        question.getAnswers().add(new Answer(txtFieldAnswer4.getText(), checkBoxAnswer4.isSelected()));

        // checks that there is answers
        if (question.getAnswers().isEmpty())
            return;

        // adds question to list
        quizQuestionsList.getItems().add(question);
    }

    // deletes current question
    public void deleteQuestion(ActionEvent actionEvent) throws IOException
    {
        // remove question from list
        Question question = quizQuestionsList.getSelectionModel().getSelectedItem();
        quizQuestionsList.getItems().remove(question);

        // remove question from save file
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

        // reset fields and check boxes
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

    // marks clicked box as true and all others false
    public void markTrue(ActionEvent actionEvent)
    {
        CheckBox answer = (CheckBox) actionEvent.getSource();

        if (!answer.isSelected())
            return;

        // if answer id is not equal set that box to false
        // makes sure only 1 box is checked at a time
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

    // select question in list
    public void selectedQuizQuestion(MouseEvent mouseEvent)
    {
        CheckBox[] checkBoxes = {checkBoxAnswer1, checkBoxAnswer2, checkBoxAnswer3, checkBoxAnswer4};

        // create a new question object and set fields to show saved information
        Question question = quizQuestionsList.getSelectionModel().getSelectedItem();
        txtFieldQuestion.setText(question.toString());
        txtFieldAnswer1.setText(question.getAnswers().get(0).toString());
        txtFieldAnswer2.setText(question.getAnswers().get(1).toString());
        txtFieldAnswer3.setText(question.getAnswers().get(2).toString());
        txtFieldAnswer4.setText(question.getAnswers().get(3).toString());

        btnDeleteQuestion.setDisable(false);

        // set check box to true or false depending on the saved answer
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

    // opens selected quiz in the quiz editor
    public void editQuiz(ActionEvent actionEvent)
    {
        // open correct tab and load saved information (title, questions, answers)
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        tabPane.getSelectionModel().select(tabCreateQuiz);
        quizQuestionsList.getItems().clear();
        quizQuestionsList.getItems().addAll(quiz.getQuestions());
        txtFieldQuizTitle.setText(quiz.toString());
    }
}
