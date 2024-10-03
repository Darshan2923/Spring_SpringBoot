package com.sharewise.sharewise.notes_resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sharewise.sharewise.history.NotesTransactionHistory;

public interface NotesTransactionHistoryRepo extends JpaRepository<NotesTransactionHistoryRepo, Integer> {

    @Query("""
            SELECT history
            FROM NotesTransactionHistory history
            WHERE history.user.id=:userId
            """)
    Page<NotesTransactionHistory> findALlBorrowedNotes(Pageable pageable, Integer userId);

}
