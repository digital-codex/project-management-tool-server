package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.entity.UserEntity;
import dev.codex.web.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service(ApplicationConstants.USER_SERVICE_BEAN_NAME)
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, VerificationTokenService verificationTokenService, RefreshTokenService refreshTokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenService = verificationTokenService;
        this.refreshTokenService = refreshTokenService;
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
    public String newVerificationToken(Long id) {
        return this.verificationTokenService.generate(id);
    }

    @Transactional
    public String newRefreshToken(Long id) {
        return this.refreshTokenService.generate(id);
    }

    @Transactional
    public void enable(String token) {
        int count = this.repository.updateSetEnabledTrueById(this.verificationTokenService.loadUserEntityIdByToken(token));

        if (count != 1)
            throw new ProcessingException(ProcessingException.INVALID_RESULT_COUNT_EXCEPTION_MSG_FORMAT.formatted(1, count));
    }

    @Transactional(readOnly = true)
    public UserModelData loadCurrentUser(String username) {
        UserEntity user = this.repository.findByUsername(username).orElse(null);
        if (user == null) {
            user =  this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(UserEntity.class.getSimpleName())));
        }
        return this.map(user);
    }

    @Transactional(readOnly = true)
    public UserModelData loadCurrentUser() {
        return this.loadCurrentUser((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Transactional(readOnly = true)
    public boolean checkRefreshToken(String token) {
        return this.refreshTokenService.existsByToken(token);
    }

    @Transactional
    public void deleteRefreshToken(String token) {
        this.refreshTokenService.deleteByToken(token);
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