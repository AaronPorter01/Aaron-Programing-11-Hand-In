import java.util.ArrayList;

// the school class holds the number of teachers and students as well as other information about the school
// since the school class holds information about the school it is used to add, remove, or list students and teachers
// the school class is the class that deals with the different array lists and adds instances of the other classes to those lists

public class School
{
    // declare variables
    private String name;
    private int capacity;
    private int district;
    static int districtID = 1;

    // setup arrays
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<String> courses = new ArrayList<>();

    // appropriate constructor
    // handles when name and capacity is given and when it's not
    School()
    {
        name = "n/a";
        capacity = 200;
        district = districtID;
        districtID++;
    }
    School(String name, int capacity)
    {
        this.name = name;
        this.capacity = 200;
        this.district = districtID;
        districtID++;
    }

    public void addTeacher(String firstName, String lastName, String subject)
    {
        // add new teacher to teachers array
        teachers.add(new Teacher(firstName, lastName, subject));
    }
    public void deleteTeacher(int num)
    {
        // delete a number of teachers from the end of the array
        for (int i = 0; i < num; i++)
        {
            teachers.remove(teachers.size() - 1);
        }
    }

    public void addStudent(String firstName, String lastName, String grade)
    {
        // add new student to students array
        students.add(new Student(firstName, lastName, grade));
    }
    public void deleteStudent(int num)
    {
        // delete a number of students from the end of the array
        for (int i = 0; i < num; i++)
        {
            students.remove(students.size() - 1);
        }
    }

    public void listTeachers()
    {
        // list teachers out
        System.out.println("Teachers");
        for (int i = 0; i < teachers.size(); i++)
        {
            // print teacher at position i
            teachers.get(i).printTeacher();
        }
        System.out.println("\n");
    }
    public void listStudents()
    {
        // list students out
        System.out.println("Students:");
        for (int i = 0; i < students.size(); i++)
        {
            // print student at position i
            students.get(i).printStudent();
        }
        System.out.println("\n");
    }

    public void addCourse(String name)
    {
        // add a course
        courses.add(name);
    }
    public void deleteCourse(int num)
    {
        // delete a number of courses from the end of the array
        for (int i = 0; i < num; i++)
        {
            courses.remove(courses.size() - 1);
        }
    }

    // getters and setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public int getDistrict()
    {
        return district;
    }

    public void setDistrict(int district)
    {
        this.district = district;
    }
}
