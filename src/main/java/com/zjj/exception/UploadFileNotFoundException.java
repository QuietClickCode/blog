package com.zjj.exception;


public class UploadFileNotFoundException extends RuntimeException {
    
    public UploadFileNotFoundException() {
    }
    
    public UploadFileNotFoundException(String message) {
        super(message);
    }
    
}
