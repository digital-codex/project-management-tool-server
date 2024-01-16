package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.entity.ForumEntity;
import dev.codex.web.persistence.PersistenceConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.FORUM_REPOSITORY_BEAN_NAME)
public interface ForumRepository extends JpaRepository<ForumEntity, Long> {
}