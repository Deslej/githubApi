package com.codibly.githubapi.exception;

public class NotExistingUserException extends RuntimeException{

    public NotExistingUserException(String message) {
        super(message);
    }
}
