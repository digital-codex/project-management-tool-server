package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.PersistenceConstants;
import dev.codex.web.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.REFRESH_TOKEN_REPOSITORY_BEAN_NAME)
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    boolean existsByToken(String token);

    void deleteByToken(String token);
}