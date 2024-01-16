package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.entity.CommentEntity;
import dev.codex.web.persistence.PersistenceConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(PersistenceConstants.COMMENT_REPOSITORY_BEAN_NAME)
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    int countByPostId(Long postId);

    List<CommentEntity> findAllByPostId(Long postId);

    @Query(
            value = "SELECT c FROM CommentEntity c JOIN UserEntity u ON c.insertedBy = u.id WHERE u.username = :username"
    )
    List<CommentEntity> findAllByUserEntityUsername(@Param("username") String username);

    @Query(
            value = "SELECT p.url FROM CommentEntity c JOIN PostEntity p ON c.postId = p.id WHERE c.postId = :postId"
    )
    Optional<String> findPostEntityUrlByPostId(@Param("postId") Long postId);
}