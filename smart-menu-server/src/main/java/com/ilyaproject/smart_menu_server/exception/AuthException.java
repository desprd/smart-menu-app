package com.ilyaproject.smart_menu_server.exception;

public class AuthException extends Exception{
    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
