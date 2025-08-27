package com.pedro.blogAPI.miscelaneous.exceptions;

public class BlogPostNotFoundException extends RuntimeException {
    public BlogPostNotFoundException(Long id) {
        super("BlogPost not found with id: "+ id +".");
    }
}
