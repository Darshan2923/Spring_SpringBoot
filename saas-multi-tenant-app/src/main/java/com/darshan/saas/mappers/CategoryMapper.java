package com.darshan.saas.mappers;

import org.springframework.stereotype.Service;

import com.darshan.saas.entities.Category;
import com.darshan.saas.requests.CategoryRequest;
import com.darshan.saas.responses.CategoryResponse;

@Service
public class CategoryMapper {

    public Category toEntity(final CategoryRequest request) {
        return Category.builder().name(request.getName()).description(request.getDescription()).build();
    }

    public CategoryResponse toResponse(final Category entity) {
        return CategoryResponse.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription())
                .build();
    }
}
