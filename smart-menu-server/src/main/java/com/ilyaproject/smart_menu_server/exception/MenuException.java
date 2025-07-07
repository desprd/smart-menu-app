package com.ilyaproject.smart_menu_server.exception;

public class MenuException extends Exception{

    public MenuException() {
    }

    public MenuException(String message) {
        super(message);
    }

    public MenuException(String message, Throwable cause) {
        super(message, cause);
    }
}
