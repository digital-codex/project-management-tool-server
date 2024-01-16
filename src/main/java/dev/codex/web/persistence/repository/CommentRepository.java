package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.entity.CommentEntity;
import dev.codex.web.persistence.PersistenceConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.COMMENT_REPOSITORY_BEAN_NAME)
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    int countByPostId(Long postId);
}