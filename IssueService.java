package com.codevedx.library.service;

import com.codevedx.library.model.Book;
import com.codevedx.library.model.IssueRecord;
import com.codevedx.library.model.Student;
import com.codevedx.library.repository.BookRepository;
import com.codevedx.library.repository.IssueRecordRepository;
import com.codevedx.library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Issue a book to a student
    public IssueRecord issueBook(Long bookId, Long studentId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already issued and not available.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        book.setAvailable(false);
        bookRepository.save(book);

        IssueRecord record = new IssueRecord(book, student, LocalDate.now());
        return issueRecordRepository.save(record);
    }

    // Return a book
    public IssueRecord returnBook(Long bookId) {
        IssueRecord record = issueRecordRepository.findByBookIdAndReturnDateIsNull(bookId);
        if (record == null) {
            throw new RuntimeException("No active issue found for book id: " + bookId);
        }

        record.setReturnDate(LocalDate.now());
        issueRecordRepository.save(record);

        Book book = record.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return record;
    }

    // Full issue/return history for a specific book
    public List<IssueRecord> getHistoryForBook(Long bookId) {
        return issueRecordRepository.findByBookId(bookId);
    }

    // Full issue/return history for a specific student
    public List<IssueRecord> getHistoryForStudent(Long studentId) {
        return issueRecordRepository.findByStudentId(studentId);
    }

    // Every issue/return record in the system
    public List<IssueRecord> getAllHistory() {
        return issueRecordRepository.findAll();
    }
}
