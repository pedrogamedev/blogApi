package com.pedro.blogAPI.miscelaneous.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class EmptyDatabaseException extends RuntimeException {
    public EmptyDatabaseException() {
        super("Could not return items because database is empty.");
    }
}
