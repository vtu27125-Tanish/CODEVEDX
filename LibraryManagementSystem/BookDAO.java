import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, is_available) VALUES (?, ?, TRUE)";

        try (Connection conn = LibraryDBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Book added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to add book.");
            e.printStackTrace();
        }
    }

    // View only available (not issued) books
    public List<Book> getAvailableBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE is_available = TRUE";

        try (Connection conn = LibraryDBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("is_available"),
                        rs.getString("issued_to")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch available books.");
            e.printStackTrace();
        }

        return books;
    }

    // View ALL books (available + issued)
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = LibraryDBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("is_available"),
                        rs.getString("issued_to")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch books.");
            e.printStackTrace();
        }

        return books;
    }

    // Issue a book to a student
    public boolean issueBook(int bookId, String studentName) {
        String sql = "UPDATE books SET is_available = FALSE, issued_to = ? WHERE id = ? AND is_available = TRUE";

        try (Connection conn = LibraryDBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentName);
            stmt.setInt(2, bookId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Book issued to " + studentName + "!");
                return true;
            } else {
                System.out.println("⚠️ Book not available or ID not found.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to issue book.");
            e.printStackTrace();
            return false;
        }
    }

    // Return a book
    public boolean returnBook(int bookId) {
        String sql = "UPDATE books SET is_available = TRUE, issued_to = NULL WHERE id = ? AND is_available = FALSE";

        try (Connection conn = LibraryDBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Book returned successfully!");
                return true;
            } else {
                System.out.println("⚠️ Book was not issued or ID not found.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to return book.");
            e.printStackTrace();
            return false;
        }
    }
}