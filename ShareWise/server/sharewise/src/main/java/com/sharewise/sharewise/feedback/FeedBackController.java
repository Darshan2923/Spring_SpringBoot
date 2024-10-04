package com.sharewise.sharewise.feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharewise.sharewise.commonFields.PageResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@Tag(name = "Feedbacks")
@RequiredArgsConstructor
public class FeedBackController {

    public final FeedbackService service;

    @PostMapping
    public ResponseEntity<Integer> saveFeedBack(
            @Valid @RequestBody FeedBackRequest request,
            Authentication connectedUser) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/notes/{notes-id}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findAllFeedbackByNotes(
            @PathVariable("note-id") Integer notesId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "0", required = false) int size,
            Authentication connectedUser) {
        return ResponseEntity.ok(service.findAllFeedbackByNotes(notesId, page, size, connectedUser));
    }

}
