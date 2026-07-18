package com.codevedx.library.repository;

import com.codevedx.library.model.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {
    List<IssueRecord> findByBookId(Long bookId);
    List<IssueRecord> findByStudentId(Long studentId);
    IssueRecord findByBookIdAndReturnDateIsNull(Long bookId); // finds the active (unreturned) issue for a book
}
