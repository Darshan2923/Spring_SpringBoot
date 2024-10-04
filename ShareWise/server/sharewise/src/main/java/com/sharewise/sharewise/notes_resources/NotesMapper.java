package com.sharewise.sharewise.notes_resources;

// import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import com.sharewise.sharewise.history.NotesTransactionHistory;

@Service
public class NotesMapper {

    public Notes toNotes(NotesRequest request) {
        return Notes.builder()
                .id(request.id())
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public NotesResponse toNotesResponse(Notes book) {
        return NotesResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .synopsis(book.getSynopsis())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .owner(book.getOwner().fullName())
                .notes_content(FileUtils.readFileFromLocation(book.getNotes_content()))
                .build();
    }

    public BorrowedNotesResponse toBorrowedNotesResponse(NotesTransactionHistory history) {
        return BorrowedNotesResponse.builder()
                .id(history.getNotes().getId())
                .title(history.getNotes().getTitle())
                .authorName(history.getNotes().getAuthorName())
                .rate(history.getNotes().getRate())
                .returned(history.isReturned())
                .returnApproved(history.isReturnApproved())
                .build();
    }
}
