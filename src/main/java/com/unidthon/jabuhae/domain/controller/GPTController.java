package com.unidthon.jabuhae.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.unidthon.jabuhae.domain.service.GPTService;
import java.util.List;
import java.util.Map;

@RestController
public class GPTController {

    private final GPTService GPTService;

    @Autowired
    public GPTController(GPTService GPTService) {
        this.GPTService = GPTService;
    }

    @PostMapping("/gpt/recommend")
    public ResponseEntity<Object> recommend(@RequestBody Map<String, List<String>> body) {
        List<String> gradients = body.get("gradients");
        List<String> tools = body.get("tools");

        return new ResponseEntity<>(GPTService.getRecommendResponse(gradients, tools), HttpStatus.OK);
    }

    @PostMapping("/gpt/recipe")
    public ResponseEntity<Object> recipe(@RequestBody Map<String, List<String>> body) {
        List<String> gradients = body.get("gradients");
        List<String> tools = body.get("tools");
        List<String> name = body.get("name");

        return new ResponseEntity<>(GPTService.getRecipeResponse(gradients, tools, name.toString()), HttpStatus.OK);
    }

    @PostMapping("/gpt/change")
    public ResponseEntity<Object> change(@RequestBody Map<String, List<String>> body) {
        List<String> gradients = body.get("gradients");

        return new ResponseEntity<>(GPTService.getChangeResponse(gradients), HttpStatus.OK);
    }
}
