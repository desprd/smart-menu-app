package com.ilyaproject.smart_menu_server.dto.general;

import lombok.Data;


@Data
public class GeneralResponse <T>{
    private Boolean success;
    private T content;
    private String errorMessage;

    public GeneralResponse(Boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public GeneralResponse(Boolean success, T data) {
        this.success = success;
        this.content = data;
    }
}
