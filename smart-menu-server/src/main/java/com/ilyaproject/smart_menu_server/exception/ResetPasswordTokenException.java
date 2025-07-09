package com.ilyaproject.smart_menu_server.exception;

public class ResetPasswordTokenException extends Exception{
    public ResetPasswordTokenException() {
    }

    public ResetPasswordTokenException(String message) {
        super(message);
    }

    public ResetPasswordTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
