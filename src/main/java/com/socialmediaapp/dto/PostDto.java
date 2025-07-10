package com.socialmediaapp.dto;

import jakarta.validation.constraints.NotBlank;

public class PostDto {
    @NotBlank
    private String content;

    // Getters and setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
