package com.ilyaproject.smart_menu_server.exception;

public class ProfileException extends Exception{
    public ProfileException() {
    }

    public ProfileException(String message) {
        super(message);
    }

    public ProfileException(String message, Throwable cause) {
        super(message, cause);
    }
}
