package com.sharewise.sharewise.history;

import com.sharewise.sharewise.commonFields.BaseEntity;
import com.sharewise.sharewise.notes_resources.Notes;
import com.sharewise.sharewise.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotesTransactionHistory extends BaseEntity {

    // user-relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // book-relationship
    @ManyToOne
    @JoinColumn(name = "notes_id")
    private Notes notes;

    private boolean returned;
    private boolean returnApproved;

}
