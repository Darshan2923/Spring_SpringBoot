package com.sharewise.sharewise.notes_resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BorrowedNotesResponse {
    private Integer id;
    private String title;
    private String authorName;
    private double rate;
    private boolean returned;
    private boolean returnApproved;
}
