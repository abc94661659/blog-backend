package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Category;
import org.example.entity.Result;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result findAll(){
        log.info("查询所有分类");
        List<Category> list= categoryService.findAll();
        return Result.success(list);
    }

    @PostMapping
    public Result insertCategory(@RequestBody Category category){
        log.info("添加分类");
        categoryService.insertCategory(category);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        log.info("删除分类");
        categoryService.deleteCategory(id);
        return Result.success();
    }

}
