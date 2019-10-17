package edu.cudenver.exception;

public class PublisherNotFoundException extends Exception{
    public PublisherNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
