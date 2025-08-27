package com.pedro.blogAPI.layers.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedro.blogAPI.miscelaneous.enums.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BlogPostResponse(Long id, String title, String content, Category category, ArrayList<String> tags, LocalDateTime createdAt, LocalDateTime updatedAt){
}
