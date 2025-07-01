package org.example.service;

import org.example.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void insertCategory(Category category);

    void deleteCategory(Integer id);
}
