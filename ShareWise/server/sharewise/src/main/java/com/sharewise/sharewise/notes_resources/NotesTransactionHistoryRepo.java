package com.sharewise.sharewise.notes_resources;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sharewise.sharewise.history.NotesTransactionHistory;

public interface NotesTransactionHistoryRepo extends JpaRepository<NotesTransactionHistory, Integer> {

        @Query("""
                        SELECT history
                        FROM NotesTransactionHistory history
                        WHERE history.user.id=:userId
                        """)
        Page<NotesTransactionHistory> findALlBorrowedNotes(Pageable pageable, Integer userId);

        @Query("""
                        SELECT history
                        FROM NotesTransactionHistory history
                        WHERE history.notes.owner.id=:userId
                        """)
        Page<NotesTransactionHistory> findAllReturnedNotes(Pageable pageable, Integer userId);

        @Query("""
                        SELECT (COUNT(*)>0) AS isBorrowed
                        FROM NotesTransactionHistory history
                        WHERE history.user.id = :userId
                        AND history.notes.id = :notesId
                        AND history.returnApproved = false
                            """)
        boolean isAlreadyBorrowed(Integer notesId, Integer userId);

        @Query("""
                        SELECT transaction
                        FROM NotesTransactionHistory transaction
                        WHERE transaction.user.id= :userId
                        AND transaction.notes.id= :notesId
                        AND transaction.returned = false
                        AND transaction.returnApproved = false
                        """)
        Optional<NotesTransactionHistory> findByNotesIdAndUserId(Integer notesId, Integer userId);

        @Query("""
                        SELECT transaction
                        FROM NotesTransactionHistory transaction
                        WHERE transaction.notes.owner.id= :userId
                        AND transaction.notes.id= :notesId
                        AND transaction.returned = true
                        AND transaction.returnApproved = false
                        """)
        Optional<NotesTransactionHistory> findByNotesIdAndOwnerId(Integer notesId, Integer userId);

}
