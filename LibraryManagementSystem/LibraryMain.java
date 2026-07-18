import java.util.List;
import java.util.Scanner;

public class LibraryMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Available Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-6): ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    dao.addBook(new Book(title, author));
                    break;

                case 2:
                    List<Book> available = dao.getAvailableBooks();
                    if (available.isEmpty()) {
                        System.out.println("No books currently available.");
                    } else {
                        System.out.println("\n--- Available Books ---");
                        for (Book b : available) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Invalid ID. Enter a numeric ID: ");
                        sc.next();
                    }
                    int issueId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = sc.nextLine();
                    dao.issueBook(issueId, studentName);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Invalid ID. Enter a numeric ID: ");
                        sc.next();
                    }
                    int returnId = sc.nextInt();
                    dao.returnBook(returnId);
                    break;

                case 5:
                    List<Book> all = dao.getAllBooks();
                    if (all.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println("\n--- All Books ---");
                        for (Book b : all) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }

        } while (choice != 6);

        sc.close();
    }
}