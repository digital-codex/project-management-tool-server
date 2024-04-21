package dev.codex.java.tool.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello world");
    }
}