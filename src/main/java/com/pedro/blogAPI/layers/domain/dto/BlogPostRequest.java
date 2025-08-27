package com.pedro.blogAPI.layers.domain.dto;

import com.pedro.blogAPI.miscelaneous.enums.Category;

import java.util.ArrayList;

public record BlogPostRequest(String title, String content, Category category, ArrayList<String> tags)  {
}
