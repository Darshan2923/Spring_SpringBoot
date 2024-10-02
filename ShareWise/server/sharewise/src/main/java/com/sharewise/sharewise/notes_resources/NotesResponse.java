package com.sharewise.sharewise.notes_resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesResponse {
    private Integer id;
    private String title;
    private String authorName;
    private String synopsis;
    private String owner;
    private byte[] notes_content;
    private double rate;
    private boolean archived;
    private boolean shareable;
}
