package com.pedro.blogAPI.layers.control.assembler;

import com.pedro.blogAPI.layers.control.controller.BlogController;
import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BlogPostResponseModelAssembler implements RepresentationModelAssembler<BlogPostResponse, EntityModel<BlogPostResponse>> {

    @Override
    public EntityModel<BlogPostResponse> toModel(BlogPostResponse entity) {
        return EntityModel.of(entity);
    }
}
