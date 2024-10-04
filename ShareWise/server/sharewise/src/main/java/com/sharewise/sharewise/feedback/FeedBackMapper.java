package com.sharewise.sharewise.feedback;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sharewise.sharewise.notes_resources.Notes;

@Service
public class FeedBackMapper {

    public FeedBack toFeedBack(FeedBackRequest request) {
        return FeedBack.builder()
                .note(request.note())
                .comment(request.comment())
                .notes(Notes.builder()
                        .id(request.noteId())
                        .archived(false) // Not required and has no impact, just to satisfy lombok
                        .shareable(false) // Not required and has no impact, just to satisfy lombok
                        .build())
                .build();
    }

    public FeedBackResponse toFeedBackResponse(FeedBack feedback, Integer id) {
        return FeedBackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedBack(Objects.equals(feedback.getCreatedBy(), id))
                .build();
    }

}
