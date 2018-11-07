package io.nambm.sachviet.controller;

import io.nambm.sachviet.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryController {

    ResponseEntity<List<Category>> getAllCategories();
}
