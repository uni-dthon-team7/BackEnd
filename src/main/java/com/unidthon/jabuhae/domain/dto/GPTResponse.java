package com.unidthon.jabuhae.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GPTResponse {
    private List<Choice> choices;

    @Setter
    @Getter
    public static class Choice {
        private Message message;
    }
}
