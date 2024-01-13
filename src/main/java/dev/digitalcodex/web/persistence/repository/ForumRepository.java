package dev.digitalcodex.web.persistence.repository;

import dev.digitalcodex.web.persistence.PersistenceConstants;
import dev.digitalcodex.web.persistence.entity.ForumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.FORUM_REPOSITORY_BEAN_NAME)
public interface ForumRepository extends JpaRepository<ForumEntity, Long> {
}
