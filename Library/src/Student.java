import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    public int Id;
    public String Name;
    public String Course;
    public String Section;
    public String EnrollNo;
    public String IssuedBook;
    public String IFSCCode;
    public LocalDate IssuedDate;
    public static ArrayList<Student> StudentList = new ArrayList<Student>();
    

    static int IDCounter = 0;
    //Global global = new Global();

    public Student(){
    }

    public void addStudent(String name, String course, String section, String enrollNo, String issuedBook,String iFSCCode, LocalDate issuedDate) {

        this.Id = IDCounter++;
        this.Name = name;
        this.Course = course;
        this.Section = section;
        this.EnrollNo = enrollNo;
        this.IssuedBook = issuedBook;
        this.IFSCCode = iFSCCode;
        this.IssuedDate = issuedDate;

        StudentList.add(this); // Add student to the list upon creation
    }

    public int getId(){
        return Id;
    }

    // Method to edit an existing student
    public static void editStudent(int id, Student stu) {
        for (Student student : StudentList) {
            if (student.getId() == id) {

                StudentList.set(StudentList.indexOf(student), stu);
                // student.Name = stu.Name;
                // student.Course = stu.Course;
                // student.Section = stu.Section;
                // student.EnrollNo = stu.EnrollNo;
                // student.IssuedBook = stu.IssuedBook;
                // student.IFSCCode = stu.IFSCCode;
                // student.IssuedDate = stu.IssuedDate;
                break; // Exit the loop after finding and editing the student
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Method to delete a student
    public static void deleteStudent(int id) {
        Student studentToRemove = null;
        for (Student student : StudentList) {
            if (student.getId() == id) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            StudentList.remove(studentToRemove);
            System.out.println("Student with ID " + id + " deleted.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Method to get a specific student by ID
    public static Student getStudent(int id) {
        for (Student student : StudentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Method to get the list of all students
    public static List<Student> getList() {
        return StudentList;
    }

    // Method to convert the list of students to a CSV file
    public static void ListToCsv() {
        // Code to save the list of students to a CSV file
        try {
            FileWriter writer = new FileWriter("student.csv");
            for (Student student : StudentList) {
            writer.write(student.getId() + "," + student.Name + "," + student.Course + "," + student.Section + "," + student.EnrollNo + "," + student.IssuedBook + "," + student.IFSCCode + "," + student.IssuedDate + "\n");
            }
            writer.close();
            System.out.println("Data written to student.csv successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to student.csv.");
            e.printStackTrace();
        }
    }

    // Method to read the list of students from a CSV file
    public static void CsvToList() {
        // Code to read the list of students from a CSV file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("student.csv"));
            String line = "";
            StudentList.clear();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student();
                student.Id = Integer.parseInt(data[0]);
                IDCounter = Integer.parseInt(data[0]);
                student.Name = data[1];
                student.Course = data[2];
                student.Section = data[3];
                student.EnrollNo = data[4];
                student.IssuedBook = data[5];
                student.IFSCCode = data[6];
                student.IssuedDate = LocalDate.parse(data[7]);
                StudentList.add(student);
            }
            reader.close();
            System.out.println("Data read from student.csv successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from student.csv.");
            e.printStackTrace();
        }
    }
}
