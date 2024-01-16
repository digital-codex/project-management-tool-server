package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.PostModelData;
import dev.codex.web.application.service.ForumService;
import dev.codex.web.application.service.PostService;
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

@RestController(PresentationConstants.POST_CONTROLLER_BEAN_NAME)
@RequestMapping(PresentationConstants.POST_REQUEST_PATH)
public class PostController {
    private final PostService service;
    private final ForumService forumService;

    @Autowired
    public PostController(PostService service, ForumService forumService) {
        this.service = service;
        this.forumService = forumService;
    }

    @PostMapping
    public ResponseEntity<PostModelData> create(@RequestBody PostModelData request) {
        return new ResponseEntity<>(this.forumService.checkAndSave(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostModelData>> read() {
        return new ResponseEntity<>(this.service.loadAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostModelData> readById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.loadById(id), HttpStatus.OK);
    }

    @GetMapping("/by-forum/{id}")
    public ResponseEntity<List<PostModelData>> readByForumId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.loadAllByForumId(id), HttpStatus.OK);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<PostModelData>> readByForumId(@PathVariable("username") String username) {
        return new ResponseEntity<>(this.service.loadAllByUserEntityUsername(username), HttpStatus.OK);
    }
}