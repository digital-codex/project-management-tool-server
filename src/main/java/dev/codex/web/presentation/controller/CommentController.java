package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.CommentModelData;
import dev.codex.web.application.service.CommentService;
import dev.codex.web.application.service.PostService;
import dev.codex.web.presentation.PresentationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final PostService postService;

    @Autowired
    public CommentController(CommentService service, PostService postService) {
        this.service = service;
        this.postService = postService;
    }

    @Operation(summary = "Create a new Comment")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Comment successfully created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CommentModelData.class))}),
            }
    )
    @PostMapping
    public ResponseEntity<CommentModelData> create(@RequestBody CommentModelData request) {
        return new ResponseEntity<>(this.postService.checkAndSaveComment(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Read all Comments (readByPostId)", description = "Read a comment by provided Post {id}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CommentModelData.class)))}),
            }
    )
    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentModelData>> readByPostId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.loadAllByPostId(id), HttpStatus.OK);
    }

    @Operation(summary = "Read all Comments (readByUsername)", description = "Read a comment by provided User {username}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CommentModelData.class)))}),
            }
    )
    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentModelData>> readByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(this.service.loadAllByUserEntityUsername(username), HttpStatus.OK);
    }
}