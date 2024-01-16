package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.PersistenceConstants;
import dev.codex.web.persistence.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(PersistenceConstants.VOTE_REPOSITORY_BEAN_NAME)
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    Optional<VoteEntity> findByPostIdAndInsertedBy(Long postId, Long insertedBy);

    List<VoteEntity> findAllByPostId(Long postId);
}