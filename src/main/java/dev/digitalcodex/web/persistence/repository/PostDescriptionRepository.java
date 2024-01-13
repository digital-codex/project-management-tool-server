package dev.digitalcodex.web.persistence.repository;

import dev.digitalcodex.web.persistence.PersistenceConstants;
import dev.digitalcodex.web.persistence.entity.PostDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PersistenceConstants.POST_DESCRIPTION_REPOSITORY_BEAN_NAME)
public interface PostDescriptionRepository extends JpaRepository<PostDescriptionEntity, Long> {
}
