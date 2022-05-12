import javafx.scene.control.ListView;

import java.io.*;
import java.util.ArrayList;

public class LoadData
{
    private static String firstName;
    private static String lastName;
    private static String birthDay;
    private static String birthMonth;
    private static String birthYear;
    private static String hobby;
    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Friend> friends = new ArrayList<>();

    public static ArrayList loadAllFriends(String fileName) throws IOException
    {
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String friendString = "";

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

    public static void deleteFriend(String fileName, ListView<Friend> friendsList) throws IOException
    {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Friend f : friendsList.getItems())
        {
            f.writeToFile();
        }
    }

    private static void parseFriend(String string)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        String firstName = "";
        String lastName = "";
        String birthDay = "";
        String birthMonth = "";
        String birthYear = "";
        String hobby = "";
        for (int i = -1; (i = string.indexOf(",", i + 1)) != -1; i++)
        {
            positions.add(i);
        }

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
