package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.VoteModelData;
import dev.codex.web.application.service.PostService;
import dev.codex.web.presentation.PresentationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(PresentationConstants.VOTE_CONTROLLER_BEAN_NAME)
@RequestMapping(PresentationConstants.VOTE_REQUEST_PATH)
public class VoteController {
    private final PostService postService;

    @Autowired
    public VoteController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create a new Vote")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Vote successfully created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VoteModelData.class))}),
            }
    )
    @PostMapping
    public ResponseEntity<VoteModelData> create(@RequestBody VoteModelData request) {
        return new ResponseEntity<>(this.postService.checkAndSaveVote(request), HttpStatus.CREATED);
    }
}