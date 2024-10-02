package com.sharewise.sharewise.notes_resources;

// import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

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
                // .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();
    }
}
