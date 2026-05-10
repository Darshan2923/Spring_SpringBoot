package com.darshan.saas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darshan.saas.requests.CategoryRequest;
import com.darshan.saas.responses.CategoryResponse;
import com.darshan.saas.services.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(
            @Valid @RequestBody final CategoryRequest request) {
        this.categoryService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Void> updateCategory(
            @Valid @RequestBody final CategoryRequest request, @PathVariable("category-id") final String id) {
        this.categoryService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @PathVariable("category-id") final String id) {
        return ResponseEntity.ok(this.categoryService.findById(id));
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable("category-id") final String id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
