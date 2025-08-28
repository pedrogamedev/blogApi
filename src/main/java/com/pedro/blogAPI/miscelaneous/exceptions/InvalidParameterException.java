package com.pedro.blogAPI.miscelaneous.exceptions;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String parameter) {
        super("Parameter " + parameter+ " contains invalid value, either empty, blank or null.");
    }
}
