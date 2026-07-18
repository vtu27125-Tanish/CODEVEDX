public class Book {

    private int id;
    private String title;
    private String author;
    private boolean available;
    private String issuedTo;

    // Constructor for a NEW book (no id yet - DB auto-generates it)
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Constructor used when reading a book BACK from the database
    public Book(int id, String title, String author, boolean available, String issuedTo) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.issuedTo = issuedTo;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Issued to: " + issuedTo;
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Status: " + status;
    }
}