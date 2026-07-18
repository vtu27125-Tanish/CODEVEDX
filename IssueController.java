package com.codevedx.library.controller;

import com.codevedx.library.model.IssueRecord;
import com.codevedx.library.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    // ADMIN and USER can both issue/return in this simple model (adjust to ADMIN-only if you prefer)
    @PostMapping("/issue")
    public IssueRecord issueBook(@RequestParam Long bookId, @RequestParam Long studentId) {
        return issueService.issueBook(bookId, studentId);
    }

    @PostMapping("/return")
    public IssueRecord returnBook(@RequestParam Long bookId) {
        return issueService.returnBook(bookId);
    }

    // Book issue & return history - required by Level 3
    @GetMapping("/history")
    public List<IssueRecord> getAllHistory() {
        return issueService.getAllHistory();
    }

    @GetMapping("/history/book/{bookId}")
    public List<IssueRecord> getHistoryForBook(@PathVariable Long bookId) {
        return issueService.getHistoryForBook(bookId);
    }

    @GetMapping("/history/student/{studentId}")
    public List<IssueRecord> getHistoryForStudent(@PathVariable Long studentId) {
        return issueService.getHistoryForStudent(studentId);
    }
}
