package com.pedro.blogAPI.layers.control.controller;

import com.pedro.blogAPI.layers.control.assembler.BlogPostResponseModelAssembler;
import com.pedro.blogAPI.layers.domain.dto.BlogPostRequest;
import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import com.pedro.blogAPI.layers.service.services.BlogPostService;
import com.pedro.blogAPI.miscelaneous.exceptions.InvalidParameterException;
import com.pedro.blogAPI.miscelaneous.problemDetailDocumentation.ProblemDetailDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogPostService service;

    @Autowired
    BlogPostResponseModelAssembler assembler;

    @Operation(summary = "Save blogpost.", description = "Saves the new blogpost. Requires the HTTP body containing the " +
            "new blogpost and its info.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Created the blogpost.",
                    content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BlogPostResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid body supplied.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class)))
    })
    @PostMapping("/blogPost")
    public ResponseEntity<EntityModel<BlogPostResponse>> saveBlogPost(@Valid @RequestBody BlogPostRequest request){
            return ResponseEntity.ok().body(assembler.toModel(service.saveBlogPost(request)));
    }

    @Operation(summary = "Get blogpost by Id.", description = "Get blogpost by its id. Returns the blogpost.")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the blogpost.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = BlogPostResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class))),
            @ApiResponse(responseCode = "404", description = "Id does not correspond to an existing blogpost.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class)))
    })
    @GetMapping("/blogPost")
    public ResponseEntity<EntityModel<BlogPostResponse>> getBlogPost(
             @RequestParam(name = "id") Long id)
    {
                return ResponseEntity.ok().body(assembler.toModel(service.getBlogPostById(id)));
    }
    @Operation(summary = "Get all blogpost containing the parameter 'term'.", description = "Performs a search and returns all" +
            " blogposts containing the 'term', defined as a request parameter, in content, category or title.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found blogposts.",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BlogPostResponse.class)))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid term supplied.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class))),
            @ApiResponse(responseCode = "404", description = "No blogpost contains the provided term.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class)))
    })
    @GetMapping("/blogPost/search")
    public ResponseEntity<CollectionModel<EntityModel<BlogPostResponse>>> getBlogPostsByTerm(
           @RequestParam(name = "term") String term
    )
    {
        if(term.isBlank()){
            throw new InvalidParameterException("term");
        }
        return ResponseEntity.ok().body(assembler.toCollectionModel(service.getBlogPostByTerm(term)));
    }

    @Operation(summary = "Returns all blogposts.", description = "Returns list containing all blogpost in the DB.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found blogposts.",
                    content = {
                            @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = BlogPostResponse.class)))
                    }),
            @ApiResponse(responseCode = "404", description = "The DB is empty, no blogposts found.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class)))
    })
    @GetMapping("/blogPost/all")
    public ResponseEntity<CollectionModel<EntityModel<BlogPostResponse>>> getAllBlogPosts() {
        return ResponseEntity.ok().body(assembler.toCollectionModel(service.getAllBlogPost()));
    }

    @Operation(summary = "Update the blogpost by id.", description = "Gets the blogpost by id and updates it by using the info" +
            " provided in the HTTP request body.")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated the blogpost.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = BlogPostResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid body or invalid id.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class))),
            @ApiResponse(responseCode = "404", description = "Blogpost not found.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class)))
    })
    @PatchMapping("/blogPost")
    public ResponseEntity<EntityModel<BlogPostResponse>> updateBlogPost(
            @RequestParam(name = "id") Long id, @RequestBody BlogPostRequest request){
            return ResponseEntity.ok().body(assembler.toModel(service.updateBlogPost(request, id)));
    }

    @Operation(summary = "Deletes the blogpost by id.", description = "Deletes the blogpost by its id and returns empty body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the blogpost."),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class))),
            @ApiResponse(responseCode = "404", description = "Book not found.", content = @Content(mediaType= "application/problem+json", schema = @Schema(implementation = ProblemDetailDoc.class)))
    })
    @DeleteMapping("/blogPost")
    public ResponseEntity<Void> deleteBlogPost(@Parameter(description = "id of the blogpost to be searched.") @NotNull @RequestParam(name = "id") Long id){
            service.deleteBlogPost(id);
            return ResponseEntity.noContent().build();
    }
}