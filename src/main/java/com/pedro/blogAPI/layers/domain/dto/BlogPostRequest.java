package com.pedro.blogAPI.layers.domain.dto;

import com.pedro.blogAPI.miscelaneous.enums.Category;

import java.util.ArrayList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record BlogPostRequest(

        @NotNull
        @NotBlank
        String title,

        @NotNull
        @NotBlank
        String content,

        Category category,

        @Size(min = 1)
        ArrayList< @NotBlank @NotNull String> tags)  {
}
