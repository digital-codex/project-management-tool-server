package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.PersistenceConstants;
import dev.codex.web.persistence.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.VOTE_REPOSITORY_BEAN_NAME)
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    int countByPostId(Long postId);
}