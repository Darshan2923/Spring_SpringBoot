package com.sharewise.sharewise.notes_resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharewise.sharewise.commonFields.PageResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("notes")
@RequiredArgsConstructor
@Tag(name = "Notes")
public class NotesController {

    private final NoteService notesService;

    @PostMapping("path")
    public ResponseEntity<Integer> saveNote(
            @Valid @RequestBody NotesRequest request,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.saveNotes(request, connectedUser));
    }

    @GetMapping("{book-id}")
    public ResponseEntity<NotesResponse> findNotesById(
            @PathVariable("book-id") Integer notesId) {
        return ResponseEntity.ok(notesService.findById(notesId));
    }

    @GetMapping("")
    public ResponseEntity<PageResponse<NotesResponse>> findAllNotes(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.findAllBooks(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<NotesResponse>> findAllBooksByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.findAllBooksByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedNotesResponse>> findAllBorrowedBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.findAllBorrowedBooks(page, size, connectedUser));
    }

}
