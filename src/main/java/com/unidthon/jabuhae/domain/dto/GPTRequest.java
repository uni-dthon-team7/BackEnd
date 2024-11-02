package com.unidthon.jabuhae.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GPTRequest {
    @JsonProperty("model")
    private String model;

    @JsonProperty("messages")
    private List<Message> messages;

    public GPTRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
}
