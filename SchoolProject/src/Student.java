// the student class is used to manage the information of 1 student
// it holds their name, grade, and number
// it is used by the school class to create new students
// the class can also be accessed to change information about a student through the getter and setter methods

public class Student
{
    // declare variables
    private String firstName;
    private String lastName;
    private String grade;
    private int studentNumber;
    static int id = 1;

    // appropriate constructor
    // handles when name and capacity is given and when it's not
    Student()
    {
        firstName = "empty";
        lastName = "empty";
        grade = "empty";
        studentNumber = id;
        id++;
    }
    Student(String firstName, String lastName, String grade)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentNumber = id;
        id++;
    }

    public void printStudent()
    {
        // print out students first and last name and their grade
        System.out.println("Name: " + firstName + " " + lastName + "\t Grade: " + grade);
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

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public int getStudentNumber()
    {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber)
    {
        this.studentNumber = studentNumber;
    }
}
