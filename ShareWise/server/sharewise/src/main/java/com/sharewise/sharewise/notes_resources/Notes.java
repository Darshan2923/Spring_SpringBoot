package com.sharewise.sharewise.notes_resources;

import java.util.List;

import com.sharewise.sharewise.commonFields.BaseEntity;
import com.sharewise.sharewise.feedback.FeedBack;
import com.sharewise.sharewise.history.NotesTransactionHistory;
import com.sharewise.sharewise.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity
public class Notes extends BaseEntity {

    private String title;
    private String authorName;
    private String synopsis;
    private String notes_content;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "notes")
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "notes")
    private List<NotesTransactionHistory> histories;

    @Transient
    public double getRate() {
        if (feedBacks == null || feedBacks.isEmpty()) {
            return 0.0;
        }
        var rate = this.feedBacks.stream()
                .mapToDouble(FeedBack::getNote)
                .average()
                .orElse(0.0);
        double roundedRate = Math.round(rate * 10.0) / 10.0;
        return roundedRate;
    }
}
