package org.example.service.impl;

import org.example.entity.Category;
import org.example.mapper.CategoryMapper;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        List<Category> list = categoryMapper.selectAll();
        return list;
    }

    @Override
    public void insertCategory(Category category) {

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertCategory(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteById(id);
    }
}
