package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.data.VerificationMail;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.application.provider.JWTProvider;
import dev.codex.web.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ApplicationConstants.AUTHENTICATION_SERVICE_BEAN_NAME)
public class AuthenticationService {
    private final UserService userService;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Autowired
    public AuthenticationService(UserService userService, MailService mailService, AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.userService = userService;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public UserModelData register(UserModelData data) {
        UserModelData persisted = this.userService.save(data);
        String token = this.userService.newVerificationToken(persisted.getId());

        this.mailService.send(
                new VerificationMail(persisted.getEmail(), MailService.VERIFICATION_MAIL_TEXT_MSG_FORMAT.formatted(token))
        );

        return persisted;
    }

    @Transactional
    public void verify(String token) {
        this.userService.enable(token);
    }

    @Transactional
    public UserModelData login(UserModelData data) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        UserModelData current = this.userService.loadCurrentUser(user.getUsername());
        current.setAuthenticationToken(this.jwtProvider.generateJWT(user.getUsername()));
        current.setRefreshToken(this.userService.newRefreshToken(current.getId()));
        return current;
    }

    @Transactional
    public UserModelData refresh(UserModelData data) {
        if (this.userService.checkRefreshToken(data.getRefreshToken())) {
            UserModelData current = this.userService.loadCurrentUser(data.getUsername());
            current.setAuthenticationToken(this.jwtProvider.generateJWT(data.getUsername()));
            current.setRefreshToken(data.getRefreshToken());
            return current;
        }

        throw new ProcessingException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(UserEntity.class.getSimpleName()));
    }

    @Transactional
    public void logout(UserModelData data) {
        this.userService.deleteRefreshToken(data.getRefreshToken());
    }
}