package edu.cudenver.exception;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
