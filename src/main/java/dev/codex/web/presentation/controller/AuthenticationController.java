package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.service.AuthenticationService;
import dev.codex.web.presentation.PresentationConstants;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController(PresentationConstants.AUTHENTICATION_CONTROLLER_BEAN_NAME)
@RequestMapping(PresentationConstants.AUTHENTICATION_REQUEST_PATH)
public class AuthenticationController {
    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @Operation(summary = "Register a new User")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User successfully registered", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserModelData.class))}),
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UserModelData> register(@RequestBody UserModelData request) {
        return new ResponseEntity<>(this.service.register(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Verify a new User", description = "Verify with the provided VerificationToken {token}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User successfully verify"),
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/verify/{token}")
    public void verify(@PathVariable("token") String token) {
        this.service.verify(token);
    }

    @Operation(summary = "Login a User", description = "Generates a JWT for the user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User successfully logged in", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserModelData.class))}),
            }
    )
    @PostMapping("/login")
    public ResponseEntity<UserModelData> login(@RequestBody UserModelData request) {
        return new ResponseEntity<>(this.service.login(request), HttpStatus.OK);
    }

    @Operation(summary = "Refresh a User's RefreshToken", description = "Generates a new JWT for the user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "RefreshToken successfully created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserModelData.class))}),
            }
    )
    @PostMapping("/refresh")
    public ResponseEntity<UserModelData> refresh(@RequestBody UserModelData request) {
        return new ResponseEntity<>(this.service.refresh(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Logout a User", description = "Destroy the provided RefreshToken")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User successfully logged out"),
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/logout")
    public void logout(@RequestBody UserModelData request) {
        this.service.logout(request);
    }
}