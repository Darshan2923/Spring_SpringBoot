package com.sharewise.sharewise.notes_resources;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sharewise.sharewise.commonFields.PageResponse;
import com.sharewise.sharewise.exception.OperationNotPermittedException;
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
        private final FileStorageService fileStorageService;

        public Integer saveNotes(NotesRequest request, Authentication connectedUser) {
                User user = ((User) connectedUser.getPrincipal());
                Notes notes = notesMapper.toNotes(request);
                notes.setOwner(user);
                return notesRepo.save(notes).getId();
        }

        public NotesResponse findById(Integer notesId) {
                return notesRepo.findById(notesId)
                                .map(notesMapper::toNotesResponse)
                                .orElseThrow(() -> new EntityNotFoundException("No notes with id: " + notesId));
        }

        public PageResponse<NotesResponse> findAllNotes(int page, int size, Authentication connectedUser) {
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

        public PageResponse<NotesResponse> findAllNotesByOwner(int page, int size, Authentication connectedUser) {
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

        public PageResponse<BorrowedNotesResponse> findAllBorrowedNotes(int page, int size,
                        Authentication connectedUser) {
                User user = ((User) connectedUser.getPrincipal());
                Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
                Page<NotesTransactionHistory> allBorrowedNotes = notesTransactionHistoryRepo.findALlBorrowedNotes(
                                pageable,
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

        public PageResponse<BorrowedNotesResponse> findAllReturnedNotes(int page, int size,
                        Authentication connectedUser) {
                User user = ((User) connectedUser.getPrincipal());
                Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
                Page<NotesTransactionHistory> allBorrowedNotes = notesTransactionHistoryRepo.findAllReturnedNotes(
                                pageable,
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

        public Integer updateShareableStatus(Integer notesId, Authentication connectedUser) {
                Notes notes = notesRepo.findById(notesId)
                                .orElseThrow(() -> new EntityNotFoundException("No note found with Id: " + notesId));
                User user = ((User) connectedUser.getPrincipal());
                if (!Objects.equals(notes.getOwner().getId(), user.getId())) {
                        // throw an exception
                        throw new OperationNotPermittedException("You cannot update others notes shareable status");
                }
                notes.setShareable(!notes.isShareable());
                notesRepo.save(notes);
                return notesId;

        }

        public Integer updateArchivedStatus(Integer notesId, Authentication connectedUser) {
                Notes notes = notesRepo.findById(notesId)
                                .orElseThrow(() -> new EntityNotFoundException("No note found with Id: " + notesId));
                User user = ((User) connectedUser.getPrincipal());
                if (!Objects.equals(notes.getOwner().getId(), user.getId())) {
                        // throw an exception
                        throw new OperationNotPermittedException("You cannot update others notes archived status");
                }
                notes.setArchived(!notes.isArchived());
                notesRepo.save(notes);
                return notesId;
        }

        public Integer borrowNotes(Integer notesId, Authentication connectedUser) {
                Notes notes = notesRepo.findById(notesId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No notes found with the Id: " + notesId));
                if (notes.isArchived() || !notes.isShareable()) {
                        throw new OperationNotPermittedException(
                                        "The requested notes cannot be borrowed since it is archived or not shareable");
                }
                User user = ((User) connectedUser.getPrincipal());
                if (Objects.equals(notes.getOwner().getId(), user.getId())) {
                        // throw an exception
                        throw new OperationNotPermittedException("You cannot borrow your own notes");
                }
                final boolean isAlreadyBorrowed = notesTransactionHistoryRepo.isAlreadyBorrowed(notesId, user.getId());
                if (isAlreadyBorrowed) {
                        throw new OperationNotPermittedException("The requested notes are already borrowed");
                }
                NotesTransactionHistory notesTransactionHistory = NotesTransactionHistory.builder()
                                .user(user)
                                .notes(notes)
                                .returned(false)
                                .returnApproved(false)
                                .build();
                return notesTransactionHistoryRepo.save(notesTransactionHistory).getId();

        }

        public Integer returnBorrowedNotes(Integer notesId, Authentication connectedUser) {
                Notes notes = notesRepo.findById(notesId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No notes found with the Id: " + notesId));
                if (notes.isArchived() || !notes.isShareable()) {
                        throw new OperationNotPermittedException(
                                        "The requested notes cannot be borrowed since it is archived or not shareable");
                }
                User user = ((User) connectedUser.getPrincipal());
                if (Objects.equals(notes.getOwner().getId(), user.getId())) {
                        // throw an exception
                        throw new OperationNotPermittedException("You cannot borrow or return your own notes");
                }
                NotesTransactionHistory notesTransactionHistory = notesTransactionHistoryRepo
                                .findByNotesIdAndUserId(notesId, user.getId())
                                .orElseThrow(() -> new OperationNotPermittedException(
                                                "You did not borrowed this notes"));
                notesTransactionHistory.setReturned(true);
                return notesTransactionHistoryRepo.save(notesTransactionHistory).getId();

        }

        public Integer approvereturnBorrowedNotes(Integer notesId, Authentication connectedUser) {
                Notes notes = notesRepo.findById(notesId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No notes found with the Id: " + notesId));
                if (notes.isArchived() || !notes.isShareable()) {
                        throw new OperationNotPermittedException(
                                        "The requested notes cannot be borrowed since it is archived or not shareable");
                }
                User user = ((User) connectedUser.getPrincipal());
                if (Objects.equals(notes.getOwner().getId(), user.getId())) {
                        // throw an exception
                        throw new OperationNotPermittedException("You cannot borrow or return your own notes");
                }
                NotesTransactionHistory notesTransactionHistory = notesTransactionHistoryRepo
                                .findByNotesIdAndOwnerId(notesId, user.getId())
                                .orElseThrow(() -> new OperationNotPermittedException(
                                                "Notes are not returned yet so cant be approved"));

                notesTransactionHistory.setReturnApproved(true);
                return notesTransactionHistoryRepo.save(notesTransactionHistory).getId();
        }

        public void uploadNotesCoverPage(MultipartFile file, Authentication connectedUser, Integer notesId) {
                Notes notes = notesRepo.findById(notesId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "No notes found with the Id: " + notesId));
                User user = ((User) connectedUser.getPrincipal());
                var notesCover = fileStorageService.saveFile(file, user.getId());
                notes.setNotes_content(notesCover);
                notesRepo.save(notes);
        }
}
