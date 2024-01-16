package dev.codex.web.presentation.controller;

import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.service.AuthenticationService;
import dev.codex.web.presentation.PresentationConstants;
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

    @PostMapping("/register")
    public ResponseEntity<UserModelData> register(@RequestBody UserModelData request) {
        return new ResponseEntity<>(this.service.register(request), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/verify/{token}")
    public void verify(@PathVariable("token") String token) {
        this.service.verify(token);
    }

    @PostMapping("/login")
    public ResponseEntity<UserModelData> login(@RequestBody UserModelData request) {
        return new ResponseEntity<>(this.service.login(request), HttpStatus.OK);
    }
}