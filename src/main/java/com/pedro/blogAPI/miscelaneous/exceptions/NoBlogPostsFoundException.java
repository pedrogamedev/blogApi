package com.pedro.blogAPI.miscelaneous.exceptions;

public class NoBlogPostsFoundException extends RuntimeException {
    public NoBlogPostsFoundException(String term) {
        super("No blogposts found with " + term + ".");
    }
}
