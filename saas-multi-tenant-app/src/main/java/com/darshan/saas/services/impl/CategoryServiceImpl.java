package com.darshan.saas.services.impl;

import com.darshan.saas.entities.Category;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darshan.saas.mappers.CategoryMapper;
import com.darshan.saas.repositories.CategoryRepository;
import com.darshan.saas.requests.CategoryRequest;
import com.darshan.saas.responses.CategoryResponse;
import com.darshan.saas.services.CategoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public void create(final CategoryRequest request) {
        // check if category already existsapplication
        checkIfCategoryExists(request.getName());

        final Category category = categoryMapper.toEntity(request);
        this.categoryRepository.save(category);
    }

    @Override
    public void update(final String id, final CategoryRequest request) {
        final Optional<Category> existingCategory = this.categoryRepository.findById(id);
        if (existingCategory.isEmpty()) {
            log.debug("Category not found");
            throw new EntityNotFoundException("Category not found");
        }

        final Category category = existingCategory.get();
        if (!category.getName().equalsIgnoreCase(request.getName())) {
            checkIfCategoryExists(request.getName());
        }

        final Category updatedCategory = categoryMapper.toEntity(request);
        updatedCategory.setId(id);
        this.categoryRepository.save(updatedCategory);
    }

    @Override
    public CategoryResponse findById(final String id) {
        return this.categoryRepository.findById(id).map(this.categoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    @Override
    public void delete(final String id) {
        final Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        this.categoryRepository.delete(category);

    }

    private void checkIfCategoryExists(String name) {
        final Optional<Category> category = this.categoryRepository.findByNameIgnoreCase(name);
        if (category.isPresent()) {
            log.debug("Category already exists");
            throw new RuntimeException("Category already exists");
        }
    }

}
