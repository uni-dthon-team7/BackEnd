package com.unidthon.jabuhae.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Message {
    @JsonProperty("role")
    private String role;

    @Setter
    @Getter
    @JsonProperty("content")
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

}