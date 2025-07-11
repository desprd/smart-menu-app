package com.ilyaproject.smart_menu_server.utils;

import org.springframework.stereotype.Service;

@Service
public class Cleaner {
    public String cleanEmail(String email) {
        if (email == null) return null;
        return email
                .replaceAll("[\\u200B-\\u200F\\uFEFF\\u00A0\\u2060\\n\\r\\t ]", "")
                .trim();
    }
}
