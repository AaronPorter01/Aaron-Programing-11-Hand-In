// the main class is used to run the code

public class Main
{
    public static void main(String[] args)
    {
        // create new school
        School school = new School("Gordon Secondary", 1400);

        // add 10 students
        // done this way so each one can have an individual name and grade
        school.addStudent("Bob", "Henderson", "A");
        school.addStudent("Jeffery", "Burn", "C+");
        school.addStudent("Kaylie", "Zuniga", "B");
        school.addStudent("Helin", "Gallagher", "C");
        school.addStudent("Jeevan", "Moreno", "B+");
        school.addStudent("Dominique", "Hayden", "A+");
        school.addStudent("Lacie", "Rowland", "C-");
        school.addStudent("Victoria", "England", "A");
        school.addStudent("Marianne", "Garner", "C");
        school.addStudent("Evelina", "Poole", "B+");

        // add 3 teachers
        // set their name and subject
        school.addTeacher("Steve", "Smith", "Math");
        school.addTeacher("John", "Raymond", "Science");
        school.addTeacher("Carl", "Jones", "Social Studies");

        // list students and teachers
        school.listStudents();
        school.listTeachers();

        // delete 2 students and 1 teacher
        school.deleteStudent(2);
        school.deleteTeacher(1);

        // list students and teachers
        school.listStudents();
        school.listTeachers();
    }
}
