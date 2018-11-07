package io.nambm.sachviet.controller.impl;

import io.nambm.sachviet.controller.CategoryController;
import io.nambm.sachviet.entity.Category;
import io.nambm.sachviet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller
@SessionScope
public class CategoryControllerImpl implements CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryControllerImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories;
        categories = categoryRepository.searchAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
