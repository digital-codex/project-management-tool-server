package dev.digitalcodex.web.persistence.repository;

import dev.digitalcodex.web.persistence.PersistenceConstants;
import dev.digitalcodex.web.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.COMMENT_REPOSITORY_BEAN_NAME)
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
