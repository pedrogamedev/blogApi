package com.pedro.blogAPI.layers.control.controller;

import com.pedro.blogAPI.miscelaneous.exceptions.BlogPostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BlogPostNotFoundException.class)
    public ResponseStatusException handleBlogPostNotFoundException(BlogPostNotFoundException exception){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
    }
}
