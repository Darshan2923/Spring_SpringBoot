package com.sharewise.sharewise.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedBackRepo extends JpaRepository<FeedBack, Integer> {

    @Query("""
            SELECT feedback
            FROM FeedBack feedback
            WHERE feedback.note.id = :notesId
            """)
    Page<FeedBack> findAllByNotesId(Integer notesId, Pageable pageable);

}
