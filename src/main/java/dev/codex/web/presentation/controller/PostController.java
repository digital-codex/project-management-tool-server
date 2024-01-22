package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.PostModelData;
import dev.codex.web.application.service.ForumService;
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

    @Operation(summary = "Create a new Post")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Post successfully created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostModelData.class))}),
            }
    )
    @PostMapping
    public ResponseEntity<PostModelData> create(@RequestBody PostModelData request) {
        return new ResponseEntity<>(this.forumService.checkAndSave(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Read all Posts")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostModelData.class)))}),
            }
    )
    @GetMapping
    public ResponseEntity<List<PostModelData>> read() {
        return new ResponseEntity<>(this.service.loadAll(), HttpStatus.OK);
    }

    @Operation(summary = "Read a Post (readById)", description = "Read a post by provided {id}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Post found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostModelData.class))}),
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostModelData> readById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.loadById(id), HttpStatus.OK);
    }

    @Operation(summary = "Read all Posts (readByForumId)", description = "Read a post by provided Forum {id}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostModelData.class)))}),
            }
    )
    @GetMapping("/by-forum/{id}")
    public ResponseEntity<List<PostModelData>> readByForumId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.loadAllByForumId(id), HttpStatus.OK);
    }

    @Operation(summary = "Read all Posts (readByUsername)", description = "Read a post by provided User {username}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostModelData.class)))}),
            }
    )
    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<PostModelData>> readByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(this.service.loadAllByUserEntityUsername(username), HttpStatus.OK);
    }
}