package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.ForumModelData;
import dev.codex.web.application.service.ForumService;
import dev.codex.web.presentation.PresentationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(PresentationConstants.FORUM_CONTROLLER_BEAN_NAME)
@RequestMapping(PresentationConstants.FORUM_REQUEST_PATH)
public class ForumController {
    private final ForumService service;

    @Autowired
    public ForumController(ForumService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ForumModelData> create(@RequestBody ForumModelData request) {
        return new ResponseEntity<>(this.service.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ForumModelData>> read() {
        return new ResponseEntity<>(this.service.loadAll(), HttpStatus.OK);
    }
}