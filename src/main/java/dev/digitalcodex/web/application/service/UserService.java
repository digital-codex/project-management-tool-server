package dev.digitalcodex.web.application.service;

import dev.digitalcodex.web.application.ApplicationConstants;
import dev.digitalcodex.web.application.data.UserModelData;
import dev.digitalcodex.web.application.exception.ProcessingException;
import dev.digitalcodex.web.persistence.entity.UserEntity;
import dev.digitalcodex.web.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service(ApplicationConstants.USER_SERVICE_BEAN_NAME)
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserEntity create(UserModelData data) {
        return this.repository.save(
                UserEntity.builder()
                .username(data.getUsername())
                .password(data.getPassword())
                .email(data.getEmail())
                .enabled(false)
                .insertedBy(null)
                .insertedAt(Instant.now())
                .build()
        );
    }
}
