package com.pedro.blogAPI.layers.control.controller;

import com.pedro.blogAPI.miscelaneous.exceptions.BlogPostNotFoundException;
import com.pedro.blogAPI.miscelaneous.exceptions.EmptyDatabaseException;
import com.pedro.blogAPI.miscelaneous.exceptions.NoBlogPostsFoundException;
import jakarta.annotation.Priority;
import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BlogPostNotFoundException.class)
    public ProblemDetail handleBlogPostNotFoundException(BlogPostNotFoundException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("Blogpost not found.");
        detail.setType(URI.create("none"));
        return detail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotFoundException(MethodArgumentNotValidException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("none"));
        return detail;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("none"));
        return detail;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        detail.setTitle("Invalid request.");
        detail.setType(URI.create("none"));
        return detail;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyDatabaseException.class)
    public ProblemDetail handleEmptyDatabaseException(EmptyDatabaseException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("Empty DB.");
        detail.setType(URI.create("none"));
        return detail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoBlogPostsFoundException.class)
    public ProblemDetail handleNoBlogPostsFoundException(NoBlogPostsFoundException exception){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        detail.setTitle("Term does not correspond to any item.");
        detail.setType(URI.create("none"));
        return detail;
    }
}
