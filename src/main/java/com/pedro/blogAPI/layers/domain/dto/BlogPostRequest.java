package com.pedro.blogAPI.layers.domain.dto;

import com.pedro.blogAPI.miscelaneous.enums.Category;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import static com.pedro.blogAPI.miscelaneous.enums.Category.*;

public record BlogPostRequest(


        @NotBlank
        @NotNull
        @Schema(description = "Title of the blogpost.",
        name = "title",
        type = "String",
        example = "My first blogpost!")
        String title,

        @NotNull
        @NotBlank
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
        @Size(min = 1)
        ArrayList< @NotBlank @NotNull String> tags)  {
}
