package com.sharewise.sharewise.notes_resources;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NotesRequest(
        Integer id,
        @NotNull(message = "100") @NotEmpty(message = "100") String title,
        @NotNull(message = "101") @NotEmpty(message = "101") String authorName,
        @NotNull(message = "102") @NotEmpty(message = "102") String synopsis,
        boolean shareable) {

}
