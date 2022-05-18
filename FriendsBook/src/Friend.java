import javafx.scene.control.ListView;

import java.io.*;
import java.util.ArrayList;

public class Friend
{
    // variable declaration
    private String firstName;
    private String lastName;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String hobby;

    // constructor
    Friend(String _firstName, String _lastName, String _birthDay, String _birthMonth, String _birthYear, String _hobby)
    {
        firstName = _firstName;
        lastName = _lastName;
        birthDay = _birthDay;
        birthMonth = _birthMonth;
        birthYear = _birthYear;
        hobby = _hobby;
    }

    public void writeToFile(String fileName) throws IOException
    {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(firstName + ",\r");
        bw.write(lastName + ",\r");
        bw.write(birthDay + ",\r");
        bw.write(birthMonth + ",\r");
        bw.write(birthYear + ",\r");
        bw.write(hobby + ",\r");
        bw.write(";\r");
        bw.close();
    }

    public void deleteWriter(String fileName) throws IOException
    {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(firstName + ",\r");
        bw.write(lastName + ",\r");
        bw.write(birthDay + ",\r");
        bw.write(birthMonth + ",\r");
        bw.write(birthYear + ",\r");
        bw.write(hobby + ",\r");
        bw.write(";\r");
        bw.close();
        System.out.println(firstName);
    }

    // getters and setters
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getBirthDay()
    {
        return birthDay;
    }

    public void setBirthDay(String birthDay)
    {
        this.birthDay = birthDay;
    }

    public String getBirthMonth()
    {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth)
    {
        this.birthMonth = birthMonth;
    }

    public String getBirthYear()
    {
        return birthYear;
    }

    public void setBirthYear(String birthYear)
    {
        this.birthYear = birthYear;
    }

    public String getFullBirthday()
    {
        return birthMonth + " " + birthDay + ", " + birthYear;
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }

    public String toString()
    {
        return firstName + " " + lastName;
    }
}
