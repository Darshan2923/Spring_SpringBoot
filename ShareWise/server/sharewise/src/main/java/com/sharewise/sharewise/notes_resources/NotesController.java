package com.sharewise.sharewise.notes_resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sharewise.sharewise.commonFields.PageResponse;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
        return ResponseEntity.ok(notesService.findAllNotes(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<NotesResponse>> findAllNotesByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.findAllNotesByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedNotesResponse>> findAllBorrowedNotes(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.findAllBorrowedNotes(page, size, connectedUser));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedNotesResponse>> findAllReturnedNotes(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.findAllReturnedNotes(page, size, connectedUser));
    }

    @PatchMapping("/shareable/{notes-id}")
    public ResponseEntity<Integer> updateShareableStatus(
            @PathVariable("notes-id") Integer notesId,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.updateShareableStatus(notesId, connectedUser));
    }

    @PatchMapping("/archived/{notes-id}")
    public ResponseEntity<Integer> updateArchivedStatus(
            @PathVariable("notes-id") Integer notesId,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.updateArchivedStatus(notesId, connectedUser));
    }

    @PostMapping("/borrowed/{notes-id}")
    public ResponseEntity<Integer> borrowNotes(
            @PathVariable("notes-id") Integer notesId,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.borrowNotes(notesId, connectedUser));
    }

    @PatchMapping("/borrow/return/{notes-id}")
    public ResponseEntity<Integer> returnBorrowNotes(
            @PathVariable("notes-id") Integer notesId,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.returnBorrowedNotes(notesId, connectedUser));
    }

    @PatchMapping("/borrow/return/approve/{notes-id}")
    public ResponseEntity<Integer> approvereturnBorrowNotes(
            @PathVariable("notes-id") Integer notesId,
            Authentication connectedUser) {
        return ResponseEntity.ok(notesService.approvereturnBorrowedNotes(notesId, connectedUser));
    }

    @PostMapping(value = "/cover/{notes-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadNotesCoverPage(
            @PathVariable("notes-id") Integer notesId,
            @Parameter() @RequestPart("file") MultipartFile file,
            Authentication connectedUser) {
        notesService.uploadNotesCoverPage(file, connectedUser, notesId);
        return ResponseEntity.accepted().build();
    }

}
