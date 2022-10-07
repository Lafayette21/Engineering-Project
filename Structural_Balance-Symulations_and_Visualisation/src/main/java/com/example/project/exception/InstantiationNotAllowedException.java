package com.example.project.exception;

public class InstantiationNotAllowedException extends RuntimeException {
    public InstantiationNotAllowedException() {
        super("Class cannot be instantiated");
    }
}
