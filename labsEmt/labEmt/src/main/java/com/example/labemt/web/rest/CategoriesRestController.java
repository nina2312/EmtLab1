package com.example.labemt.web.rest;

import com.example.labemt.model.enumerations.Category;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesRestController {
    @GetMapping
    public List<Category> findAll(){ return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
