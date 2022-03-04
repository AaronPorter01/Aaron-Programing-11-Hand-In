// the teacher class holds the information of 1 teacher
// the class holds their name and the subject they teach
// the teacher class is used by the school class to create new teachers
// the class can also be reached to change information about a teacher through the getter and setter methods

public class Teacher
{
    // declare variables
    private String firstName;
    private String lastName;
    private String subject;

    // appropriate constructor
    // handles when name and capacity is given and when it's not
    Teacher()
    {
        firstName = "empty";
        lastName = "empty";
        subject = "empty";
    }
    Teacher(String firstName, String lastName, String subject)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public void printTeacher()
    {
        // print out teachers first and last name and the subject they teach
        System.out.println("Name: " + firstName + " " + lastName + "\t Subject: " + subject);
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

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}
