package com.sharewise.sharewise.notes_resources;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sharewise.sharewise.commonFields.PageResponse;
import com.sharewise.sharewise.history.NotesTransactionHistory;
import com.sharewise.sharewise.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NotesMapper notesMapper;
    private final NotesRepo notesRepo;
    private final NotesTransactionHistoryRepo notesTransactionHistoryRepo;

    public Integer saveNotes(NotesRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Notes notes = notesMapper.toNotes(request);
        notes.setOwner(user);
        return notesRepo.save(notes).getId();
    }

    public NotesResponse findById(Integer notesId) {
        return notesRepo.findById(notesId)
                .map(notesMapper::toNotesResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book with id: " + notesId));
    }

    public PageResponse<NotesResponse> findAllBooks(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Notes> notes = notesRepo.findAllDisplayableNotes(pageable, user.getId());
        List<NotesResponse> notesResponses = notes.stream()
                .map(notesMapper::toNotesResponse)
                .toList();

        return new PageResponse<>(
                notesResponses,
                notes.getNumber(),
                notes.getSize(),
                notes.getTotalElements(),
                notes.getTotalPages(),
                notes.isFirst(),
                notes.isLast());
    }

    public PageResponse<NotesResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Notes> notes = notesRepo.findAll(NoteSpecification.withOwnerId(user.getId()), pageable);
        List<NotesResponse> notesResponses = notes.stream()
                .map(notesMapper::toNotesResponse)
                .toList();

        return new PageResponse<>(
                notesResponses,
                notes.getNumber(),
                notes.getSize(),
                notes.getTotalElements(),
                notes.getTotalPages(),
                notes.isFirst(),
                notes.isLast());
    }

    public PageResponse<BorrowedNotesResponse> findAllBorrowedBooks(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<NotesTransactionHistory> allBorrowedNotes = notesTransactionHistoryRepo.findALlBorrowedNotes(pageable,
                user.getId());
        List<BorrowedNotesResponse> notesResponses = allBorrowedNotes.stream()
                .map(notesMapper::toBorrowedNotesResponse)
                .toList();
        return new PageResponse<>(
                notesResponses,
                allBorrowedNotes.getNumber(),
                allBorrowedNotes.getSize(),
                allBorrowedNotes.getTotalElements(),
                allBorrowedNotes.getTotalPages(),
                allBorrowedNotes.isFirst(),
                allBorrowedNotes.isLast());
    }
}
