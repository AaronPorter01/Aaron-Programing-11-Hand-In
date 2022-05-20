import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller
{
    // variable declaration
    public TextField txtFieldFirstName;
    public TextField txtFieldLastName;
    public TextField txtFieldHobby;
    public TextField txtFieldMonth;
    public TextField txtFieldDay;
    public TextField txtFieldYear;
    public Button btnCreateFriend;
    public ListView<Friend> friendsList;
    public Text txtFirstName;
    public Text txtLastName;
    public Text txtHobby;
    public Text txtBirthday;
    public Text displayName;
    public Text displayHobby;
    public Text displayBirthday;
    public Tab tabFriends;
    public Tab tabCreateFriend;
    public MenuItem btnDeleteFriend;
    public TabPane tabPane;
    public TextField txtFieldGroupName;
    public Menu menuGroups;
    public Text txtGroupName;
    public MenuItem btnFindFiles;

    public String currentLoadFile = "";
    ArrayList<String> textFiles = new ArrayList<>();

    // creates a friend on button clicked
    public void createFriend(ActionEvent actionEvent) throws IOException
    {
        // make a list of all the text fields
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(txtFieldFirstName);
        textFields.add(txtFieldLastName);
        textFields.add(txtFieldHobby);
        textFields.add(txtFieldMonth);
        textFields.add(txtFieldDay);
        textFields.add(txtFieldYear);

        // make a list of all the indicator texts
        ArrayList<Text> texts = new ArrayList<>();
        texts.add(txtFirstName);
        texts.add(txtLastName);
        texts.add(txtHobby);
        texts.add(txtBirthday);
        texts.add(txtBirthday);
        texts.add(txtBirthday);

        // create a list of the different birthday info slots
        ArrayList<TextField> bDates = new ArrayList<>();
        bDates.add(txtFieldMonth);
        bDates.add(txtFieldDay);
        bDates.add(txtFieldYear);

        setNotFullColour(textFields, texts, bDates);

        // check all fields are full
        if (!fieldsFull(textFields))
        {
            System.out.println("Not Full");
            return;
        }

        // create a new friend and add it to the list of friends
        Friend newFriend = new Friend(
                txtFieldFirstName.getText(), txtFieldLastName.getText(), txtFieldDay.getText(),
                txtFieldMonth.getText(), txtFieldYear.getText(), txtFieldHobby.getText());
        friendsList.getItems().add(newFriend);

        newFriend.writeToFile(currentLoadFile);

        tabFriends.setDisable(false);
        clearFields(textFields);
    }

    // checks that all the fields are full before creating a friend
    public boolean fieldsFull(ArrayList<TextField> fields)
    {
        boolean result = false;

        // goes through every field
        for (int i = 0; i < fields.size(); i++)
        {
            // checks if empty and returns the result
            if (fields.get(i).getText().isEmpty())
            {
                result = false;
                break;
            }
            else
            {
                result = true;
            }
        }

        return result;
    }

    // sets the color of the indicator texts to red if the field is empty
    public void setNotFullColour(ArrayList<TextField> fields, ArrayList<Text> texts, ArrayList<TextField> b)
    {
        // cycle through each field
        for (int i = 0; i < fields.size(); i++)
        {
            // if the field is empty set the text colour to red
            if (fields.get(i).getText().isEmpty())
            {
                texts.get(i).setFill(Color.RED);
            }
            else
            {
                texts.get(i).setFill(Color.BLACK);
            }
        }

        // check bday fields again to make sure the indicator is set correctly
        for (int i = 0; i < b.size(); i++)
        {
            if (b.get(i).getText().isEmpty())
            {
                txtBirthday.setFill(Color.RED);
                break;
            }
            else
            {
                txtBirthday.setFill(Color.BLACK);
            }
        }
    }

    // clears all fields after a new friend has successfully been created
    public void clearFields(ArrayList<TextField> fields)
    {
        // cycle through each field
        for (int i = 0; i < fields.size(); i++)
        {
            fields.get(i).clear();
        }
    }

    // called when friend is clicked on
    public void displayFriend(MouseEvent mouseEvent)
    {
        // get selected friend
        Friend display = friendsList.getSelectionModel().getSelectedItem();
        // set details
        displayName.setText(display.toString());
        displayHobby.setText(display.getHobby());
        displayBirthday.setText(display.getFullBirthday());
        // enable delete button
        btnDeleteFriend.setDisable(false);
    }

    // delete selected friend on clicked
    public void deleteFriend(ActionEvent actionEvent) throws IOException
    {
        // remove from list view
        friendsList.getItems().remove(friendsList.getSelectionModel().getSelectedItem());

        // if there is 0 friends left disable tab
        if (friendsList.getItems().stream().count() == 0)
        {
            tabFriends.setDisable(true);
            // disable buttons
            btnDeleteFriend.setDisable(true);

            // reset display texts
            displayName.setText("");
            displayHobby.setText("");
            displayBirthday.setText("");

            tabPane.getSelectionModel().select(tabCreateFriend);

            LoadData.clearFriends(currentLoadFile);

            return;
        }

        LoadData.clearFriends(currentLoadFile);
        LoadData.deleteFriend(currentLoadFile, friendsList);

        // set display texts
        displayName.setText(friendsList.getSelectionModel().getSelectedItem().toString());
        displayHobby.setText(friendsList.getSelectionModel().getSelectedItem().getHobby());
        displayBirthday.setText(friendsList.getSelectionModel().getSelectedItem().getFullBirthday());
    }

    public void loadAllFriends(ActionEvent actionEvent) throws IOException
    {
        friendsList.getItems().clear();
        ArrayList<Friend> friends = LoadData.loadGroup(currentLoadFile);
        for (Friend f : friends)
        {
            friendsList.getItems().add(f);
        }

        friends.clear();
        if (friendsList.getItems().size() > 0)
        {
            tabFriends.setDisable(false);
        }
    }

    public void loadGroup(ActionEvent actionEvent, String name) throws IOException
    {
        tabFriends.setText(name);
        currentLoadFile = tabFriends.getText().toLowerCase() + ".txt";
        displayName.setText("");
        displayHobby.setText("");
        displayBirthday.setText("");

        friendsList.getItems().clear();
        ArrayList<Friend> friends = LoadData.loadGroup(currentLoadFile);
        if (friends.isEmpty())
            return;
        for (Friend f : friends)
        {
            friendsList.getItems().add(f);
        }

        friends.clear();
        if (friendsList.getItems().size() > 0)
        {
            tabFriends.setDisable(false);
        }
    }

    public void createGroup(ActionEvent actionEvent) throws IOException
    {
        tabCreateFriend.setDisable(false);
        String groupName = txtFieldGroupName.getText();
        txtFieldGroupName.clear();
        MenuItem menuItem = new MenuItem(groupName);
        if (groupName.contains(" ") || groupName.isEmpty())
        {
            txtGroupName.setFill(Color.RED);
            return;
        }
        else if (!menuGroups.getItems().isEmpty())
        {
            for (int i = 0; i < menuGroups.getItems().size(); i++)
            {
                if (menuGroups.getItems().get(i).getText().toLowerCase().equals(menuItem.getText().toLowerCase()))
                {
                    txtGroupName.setFill(Color.RED);
                    return;
                }
            }
        }
        txtGroupName.setFill(Color.BLACK);
        menuGroups.getItems().add(menuItem);
        menuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    loadGroup(actionEvent, menuItem.getText());
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        FileWriter fw = new FileWriter(groupName.toLowerCase() + ".txt", true);
        fw.close();
        loadGroup(actionEvent, menuItem.getText());
    }

    public void findMissingGroups(ActionEvent actionEvent) throws IOException
    {
        File temp = new File("_temp.txt");
        File folder = new File(temp.getAbsoluteFile().getParent());
        File[] listOfFiles = folder.listFiles();
        System.out.println(folder);

        for (File file : listOfFiles)
        {
            if (file.getName().endsWith(".txt"))
            {
                String fileName = file.getName().replace(".txt", "");
                fileName = stringToUpperCase(fileName);
                txtFieldGroupName.setText(fileName);
                createGroup(actionEvent);
                System.out.println("found");
            }
        }
    }

    public String stringToUpperCase(String s)
    {
        s = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        return s;
    }
}
