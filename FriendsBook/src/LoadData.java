import javafx.scene.control.ListView;

import java.io.*;
import java.util.ArrayList;

public class LoadData
{
    // variable declaration
    private static String firstName;
    private static String lastName;
    private static String birthDay;
    private static String birthMonth;
    private static String birthYear;
    private static String hobby;
    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Friend> friends = new ArrayList<>();

    // load a group
    public static ArrayList loadGroup(String fileName) throws IOException
    {
        // set upp readers
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String friendString = "";

        // read lines until ";" is found
        while ((line = br.readLine()) != null)
        {
            if (!line.equals(";"))
            {
                friendString += line;
            }
            else
            {
                parseFriend(friendString);
                friendString = "";
            }
        }

        br.close();

        return friends;
    }

    // delete a friend from group
    public static void deleteFriend(String fileName, ListView<Friend> friendsList) throws IOException
    {
        // for each friend still in list write them to file
        for (Friend f : friendsList.getItems())
        {
            f.writeToFile(fileName);
        }
    }

    // clear everything from file
    public static void clearFriends(String fileName) throws IOException
    {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("");
        bw.close();
    }

    // adds friend correctly formatted with the correct information
    private static void parseFriend(String string)
    {
        // declare all variables
        ArrayList<Integer> positions = new ArrayList<>();
        String firstName = "";
        String lastName = "";
        String birthDay = "";
        String birthMonth = "";
        String birthYear = "";
        String hobby = "";
        // set each beginning and end positions to correct spots
        for (int i = -1; (i = string.indexOf(",", i + 1)) != -1; i++)
        {
            positions.add(i);
        }

        // set variables based on txt file
        for (int i = 0; i < string.length(); i++)
        {
            firstName = string.substring(0, positions.get(0));
            lastName = string.substring(positions.get(0) + 1, positions.get(1));
            birthDay = string.substring(positions.get(1) + 1, positions.get(2));
            birthMonth = string.substring(positions.get(2) + 1, positions.get(3));
            birthYear = string.substring(positions.get(3) + 1, positions.get(4));
            hobby = string.substring(positions.get(4) + 1, positions.get(5));
        }

        friends.add(new Friend(firstName, lastName, birthDay, birthMonth, birthYear, hobby));
    }
}
