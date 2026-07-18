import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add a new student record
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, department, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getDepartment());
            stmt.setString(4, student.getEmail());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to add student.");
            e.printStackTrace();
        }
    }

    // View all student records
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("email")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch students.");
            e.printStackTrace();
        }

        return students;
    }

    // Delete a student record by id
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
                return true;
            } else {
                System.out.println("⚠️ No student found with ID: " + id);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to delete student.");
            e.printStackTrace();
            return false;
        }
    }
}