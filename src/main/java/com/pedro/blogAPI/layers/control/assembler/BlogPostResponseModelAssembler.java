package com.pedro.blogAPI.layers.control.assembler;

import com.pedro.blogAPI.layers.control.controller.BlogController;
import com.pedro.blogAPI.layers.domain.dto.BlogPostRequest;
import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import com.pedro.blogAPI.miscelaneous.enums.Category;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BlogPostResponseModelAssembler implements RepresentationModelAssembler<BlogPostResponse, EntityModel<BlogPostResponse>> {

    @Override
    public EntityModel<BlogPostResponse> toModel(BlogPostResponse entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(BlogController.class).getBlogPost(entity.id())).withSelfRel(),
                linkTo(methodOn(BlogController.class).getAllBlogPosts()).withRel("getAll"),
                linkTo(methodOn(BlogController.class).deleteBlogPost(entity.id())).withRel("delete"),
                linkTo(methodOn(BlogController.class).updateBlogPost(entity.id(),
                        new BlogPostRequest("title", "content", Category.PETS,  new ArrayList<>()))).withRel("update"),
                linkTo(methodOn(BlogController.class).getBlogPostsByTerm("term")).withRel("searchByTerm"));
    }

    @Override
    public CollectionModel<EntityModel<BlogPostResponse>> toCollectionModel(Iterable<? extends BlogPostResponse> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
