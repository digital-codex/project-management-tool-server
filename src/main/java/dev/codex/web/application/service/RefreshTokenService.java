package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.entity.RefreshTokenEntity;
import dev.codex.web.persistence.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service(ApplicationConstants.REFRESH_TOKEN_SERVICE_BEAN_NAME)
public class RefreshTokenService {
    private final RefreshTokenRepository repository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String generate(Long userId) {
        if (userId == null)
            throw new ProcessingException(ProcessingException.NULL_POINTER_EXCEPTION_MSG_FORMAT.formatted("userId"));

        RefreshTokenEntity persisted = this.repository.save(RefreshTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .insertedBy(userId)
                .insertedAt(Instant.now())
                .build()
        );

        return persisted.getToken();
    }

    @Transactional(readOnly = true)
    public boolean existsByToken(String token) {
        return this.repository.existsByToken(token);
    }

    @Transactional
    public void deleteByToken(String token) {
        this.repository.deleteByToken(token);
    }
}