package com.sharewise.sharewise.notes_resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NotesRepo extends JpaRepository<Notes, Integer>, JpaSpecificationExecutor<Notes> {

    @Query("""
            SELECT note
            FROM Notes note
            WHERE note.archived = false
            AND note.shareable = true
            AND note.owner.id != :userId
            """)
    Page<Notes> findAllDisplayableNotes(Pageable pageable, Integer userId);
}
