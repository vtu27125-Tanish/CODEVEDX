import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Basic input validation
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-4): ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter department: ");
                    String department = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    dao.addStudent(new Student(name, age, department, email));
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No student records found.");
                    } else {
                        System.out.println("\n--- Student Records ---");
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter ID of student to delete: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Invalid ID. Please enter a numeric ID: ");
                        sc.next();
                    }
                    int id = sc.nextInt();
                    dao.deleteStudent(id);
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }

        } while (choice != 4);

        sc.close();
    }
}3