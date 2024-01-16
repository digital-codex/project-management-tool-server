package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.CommentModelData;
import dev.codex.web.application.service.CommentService;
import dev.codex.web.presentation.PresentationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(PresentationConstants.COMMENT_CONTROLLER_BEAN_NAME)
@RequestMapping(PresentationConstants.COMMENT_REQUEST_PATH)
public class CommentController {
    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CommentModelData> create(@RequestBody CommentModelData request) {
        return new ResponseEntity<>(this.service.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentModelData>> readByPostId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.loadAllByPostId(id), HttpStatus.OK);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentModelData>> readByPostId(@PathVariable("username") String username) {
        return new ResponseEntity<>(this.service.loadAllByUserEntityUsername(username), HttpStatus.OK);
    }
}