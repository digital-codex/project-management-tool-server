package dev.codex.web.persistence.repository;

import dev.codex.web.persistence.entity.PostDescriptionEntity;
import dev.codex.web.persistence.PersistenceConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(PersistenceConstants.POST_DESCRIPTION_REPOSITORY_BEAN_NAME)
public interface PostDescriptionRepository extends JpaRepository<PostDescriptionEntity, Long> {
    Optional<PostDescriptionEntity> findBySharedIdAndDescriptionNotNull(Long sharedId);
}