package com.sharewise.sharewise.notes_resources;

import org.springframework.data.jpa.domain.Specification;

public class NoteSpecification {

    public static Specification<Notes> withOwnerId(Integer ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    }
}
