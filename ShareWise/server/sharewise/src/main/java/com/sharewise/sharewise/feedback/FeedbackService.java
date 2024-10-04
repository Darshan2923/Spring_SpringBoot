package com.sharewise.sharewise.feedback;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sharewise.sharewise.commonFields.PageResponse;
import com.sharewise.sharewise.exception.OperationNotPermittedException;
import com.sharewise.sharewise.notes_resources.Notes;
import com.sharewise.sharewise.notes_resources.NotesRepo;
import com.sharewise.sharewise.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final NotesRepo notesRepo;
    private final FeedBackMapper feedBackMapper;
    private final FeedBackRepo feedbackRepo;

    public Integer save(FeedBackRequest request, Authentication connectedUser) {
        Notes notes = notesRepo.findById(request.noteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No notes found with the Id: " + request.noteId()));
        if (notes.isArchived() || !notes.isShareable()) {
            throw new OperationNotPermittedException(
                    "You can't give feedback for archived or non-shareable notes");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(notes.getOwner().getId(), user.getId())) {
            // throw an exception
            throw new OperationNotPermittedException("You cannot give feedback for your own notes");
        }
        FeedBack feedBack = feedBackMapper.toFeedBack(request);
        return feedbackRepo.save(feedBack).getId();
    }

    public PageResponse<FeedBackResponse> findAllFeedbackByNotes(Integer notesId, int page, int size,
            Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<FeedBack> feedBacks = feedbackRepo.findAllByNotesId(notesId, pageable);
        List<FeedBackResponse> feedBackResponses = feedBacks.stream()
                .map(f -> feedBackMapper.toFeedBackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                feedBackResponses,
                feedBacks.getNumber(),
                feedBacks.getSize(),
                feedBacks.getTotalElements(),
                feedBacks.getTotalPages(),
                feedBacks.isFirst(),
                feedBacks.isLast());

    }

}
