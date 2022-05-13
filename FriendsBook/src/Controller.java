import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
    public Tab tabAllFriends;
    public Tab tabCreateFriend;
    public MenuItem btnDeleteFriend;
    public MenuItem btnLoadAllFriends;
    public TabPane tabPane;
    public TextField txtFieldGroupName;
    public Menu menuGroups;
    public Text txtGroupName;

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

        newFriend.writeToFile();

        tabAllFriends.setDisable(false);
        clearFields(textFields);
        btnLoadAllFriends.setDisable(false);
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
            tabAllFriends.setDisable(true);
            // disable buttons
            btnDeleteFriend.setDisable(true);

            // reset display texts
            displayName.setText("");
            displayHobby.setText("");
            displayBirthday.setText("");

            tabPane.getSelectionModel().select(tabCreateFriend);

            LoadData.clearFriends("friends.txt");

            return;
        }

        LoadData.deleteFriend("friends.txt", friendsList);

        // set display texts
        displayName.setText(friendsList.getSelectionModel().getSelectedItem().toString());
        displayHobby.setText(friendsList.getSelectionModel().getSelectedItem().getHobby());
        displayBirthday.setText(friendsList.getSelectionModel().getSelectedItem().getFullBirthday());
    }

    public void loadAllFriends(ActionEvent actionEvent) throws IOException
    {
        friendsList.getItems().clear();
        ArrayList<Friend> friends = LoadData.loadGroup("friends.txt");
        for (Friend f : friends)
        {
            friendsList.getItems().add(f);
        }

        friends.clear();
        if (friendsList.getItems().size() > 0)
        {
            tabAllFriends.setDisable(false);
        }
    }

    public void loadGroup(ActionEvent actionEvent, String name) throws IOException
    {
        Tab groupTab = FXMLLoader.load(getClass().getResource("groupTab.fxml"));
        groupTab.setText(name);
        tabPane.getTabs().add(groupTab);
        tabPane.getSelectionModel().select(groupTab);
        groupTab.setOnSelectionChanged(new EventHandler<Event>()
        {
            @Override
            public void handle(Event event)
            {
                closeTab(event, groupTab);
            }
        });
    }

    public void createGroup(ActionEvent actionEvent) throws IOException
    {
        String groupName = txtFieldGroupName.getText();
        txtFieldGroupName.clear();
        MenuItem menuItem = new MenuItem(groupName);
        if (groupName.contains(" ") || groupName.isEmpty())
        {
            txtGroupName.setFill(Color.RED);
            return;
        }
        else if (menuGroups.getItems().size() > 0)
        {
            for (int i = 0; i < menuGroups.getItems().size(); i++)
            {
                if (menuItem.getText().toLowerCase().equals(menuGroups.getItems().get(i).getText().toLowerCase()))
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
        //FileWriter fw = new FileWriter(groupName.toLowerCase() + ".txt", true);
        //fw.close();
    }

    public void closeTab(Event event, Tab tab)
    {
        tabPane.getTabs().remove(tab);
        System.out.println("close");
    }
}
