package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.entity.UserEntity;
import dev.codex.web.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service(ApplicationConstants.USER_SERVICE_BEAN_NAME)
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserModelData save(UserModelData data) {
        return this.map(
                this.repository.save(
                        UserEntity.builder()
                                .username(data.getUsername())
                                .password(this.passwordEncoder.encode(data.getPassword()))
                                .email(data.getEmail())
                                .enabled(false)
                                .insertedBy(null)
                                .insertedAt(Instant.now())
                                .build()
                )
        );
    }

    @Transactional
    public void enable(Long id) {
        int count = this.repository.updateSetEnabledTrueById(id);

        if (count != 1)
            throw new ProcessingException(ProcessingException.INVALID_RESULT_COUNT_EXCEPTION_MSG_FORMAT.formatted(1, count));
    }

    @Transactional(readOnly = true)
    public Long loadIdByUsername(String username) {
        return this.repository.findIdByUsername(username)
                .orElseThrow(() -> new ProcessingException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(UserEntity.class.getSimpleName())));
    }

    @Transactional(readOnly = true)
    public UserModelData loadById(Long id) {
        return this.map(
                this.repository.findById(id)
                        .orElseThrow(() -> new ProcessingException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(UserEntity.class.getSimpleName())))
        );
    }

    private UserModelData map(UserEntity entity) {
        UserModelData data = new UserModelData();
        data.setId(entity.id());
        data.setUsername(entity.getUsername());
        data.setEmail(entity.getEmail());
        data.setEnabled(entity.isEnabled());
        return data;
    }
}