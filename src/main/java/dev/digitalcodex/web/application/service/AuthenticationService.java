package dev.digitalcodex.web.application.service;

import dev.digitalcodex.web.application.ApplicationConstants;
import dev.digitalcodex.web.application.data.NotificationMail;
import dev.digitalcodex.web.application.data.UserModelData;
import dev.digitalcodex.web.application.security.JWTProvider;
import dev.digitalcodex.web.persistence.entity.UserEntity;
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
    private final VerificationTokenService verificationTokenService;
    private final MailService mailService;

    @Autowired
    public AuthenticationService(UserService userService, VerificationTokenService verificationTokenService, MailService mailService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
    }

    @Transactional
    public UserModelData register(UserModelData data) {
        UserEntity persisted = this.userService.create(data);
        String token = this.verificationTokenService.generate(persisted.getId());
        this.mailService.send(
                new NotificationMail(persisted.getEmail(), MailService.NOTIFICATION_MAIL_TEXT_MSG_FORMAT.formatted(token))
        );

        UserModelData result = new UserModelData();
        result.setUsername(persisted.getUsername());
        result.setEmail(persisted.getEmail());
        result.setEnabled(persisted.isEnabled());
        return result;
    }
}
