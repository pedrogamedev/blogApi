package com.pedro.blogAPI.layers.control.controller;

import com.pedro.blogAPI.layers.control.assembler.BlogPostResponseModelAssembler;
import com.pedro.blogAPI.layers.domain.dto.BlogPostRequest;
import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import com.pedro.blogAPI.layers.service.services.BlogPostService;
import com.pedro.blogAPI.miscelaneous.exceptions.BlogPostNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogPostService service;

    @Autowired
    BlogPostResponseModelAssembler assembler;

    //check if values are not empty, check if the required values are present
    @PostMapping("/blogPost")
    public ResponseEntity<EntityModel<BlogPostResponse>> saveBlogPost(@Valid @RequestBody BlogPostRequest request){
            return ResponseEntity.ok().body(assembler.toModel(service.saveBlogPost(request)));
    }

    @GetMapping("/blogPost")
    public ResponseEntity<EntityModel<BlogPostResponse>> getBlogPost(
            @NotNull @NotBlank @RequestParam(name = "id", required = false) Long id)
    {
            return ResponseEntity.ok().body(assembler.toModel(service.getBlogPostById(id)));
    }

    @GetMapping("/blogPost/all")
    public ResponseEntity<CollectionModel<EntityModel<BlogPostResponse>>> getAllBlogPosts() {
        return ResponseEntity.ok().body(assembler.toCollectionModel(service.getAllBlogPost()));
    }

    //validate the body and param
    @PatchMapping("/blogPost")
    public ResponseEntity<EntityModel<BlogPostResponse>> updateBlogPost(
             @NotNull @RequestParam(name = "id") Long id, @RequestBody BlogPostRequest request){
        //check if there is at least one value in the request body json
            return ResponseEntity.ok().body(assembler.toModel(service.updateBlogPost(request, id)));
    }

    //validate the request param
    @DeleteMapping("/blogPost")
    public ResponseEntity<Void> deleteBlogPost( @NotNull @RequestParam(name = "id") Long id){
            service.deleteBlogPost(id);
            return ResponseEntity.noContent().build();
    }
}

