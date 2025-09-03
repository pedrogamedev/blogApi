package com.pedro.blogAPI.miscelaneous.problemDetailDocumentation;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProblemDetail", description = "Error response.")
public record ProblemDetailDoc (

    @Schema(name = "title",
    type = "String",
    description = "Title of the error.",
    example = "Bad request.")
    String title,

    @Schema(name = "status",
    type = "int",
    description = "HTTP error status number.",
    example = "400")
    int status,

    @Schema(name = "detail",
    type = "String",
    description = "Message detailing the error.",
    example = "Lorem ipsum...")
     String detail,

    @Schema(name = "type",
    type = "String",
    description = "URI reference identifying the problem type.",
    example = "about:blank") String type){ }


