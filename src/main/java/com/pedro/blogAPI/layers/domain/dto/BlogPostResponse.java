package com.pedro.blogAPI.layers.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedro.blogAPI.miscelaneous.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BlogPostResponse(

        @Schema(description = "Id used for searching and retrieving blogposts and their info.",
        name = "id",
        type = "Long",
        example = "1")
        Long id,


        @Schema(description = "Title of the blogpost.",
                name = "title",
                type = "String",
                example = "My first blogpost!")
        String title,

        @Schema(description = "Text content of the blogpost.",
                name = "content",
                type = "String",
                example = "Lorem ipsum...")
        String content,

        @Schema(description = "Category of the blogpost. Can be one of the following values: "+
                "PETS, TECH, NATURE, CULTURE or SPORTS",
                name = "category",
                type = "Enum",
                example = "PETS")
        Category category,

        @Schema(description = "List of tags of the blogpost.",
                name = "tags",
                type = "ArrayList<STRING>",
                examples = {"dogs", "cats", "birds"})
        ArrayList<String> tags,
        @Schema(description = "When the blogpost was created.",
        name = "createdAt",
        type = "LocalDateTime",
        example = "2025-09-03T17:54:31.194Z")
        LocalDateTime createdAt,

        @Schema(description = "When the blogpost was updated.",
                name = "updatedAt",
                type = "LocalDateTime",
                example = "2025-09-04T18:21:12.528Z")
        LocalDateTime updatedAt){
}
