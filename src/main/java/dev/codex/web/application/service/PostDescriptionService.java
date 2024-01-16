package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.entity.PostDescriptionEntity;
import dev.codex.web.persistence.repository.PostDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service(ApplicationConstants.POST_DESCRIPTION_SERVICE_BEAN_NAME)
public class PostDescriptionService {
    private final PostDescriptionRepository repository;

    @Autowired
    public PostDescriptionService(PostDescriptionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String save(Long postId, String data, Long userId) {
        if (postId == null)
            throw new ProcessingException(ProcessingException.NULL_POINTER_EXCEPTION_MSG_FORMAT.formatted("postId"));

        return this.map(
                this.repository.save(
                        PostDescriptionEntity.builder()
                                .sharedId(postId)
                                .description(data)
                                .insertedBy(userId)
                                .insertedAt(Instant.now())
                                .build()
                )
        );
    }

    @Transactional(readOnly = true)
    public String loadBySharedId(Long sharedId) {
        return this.repository.findBySharedIdAndDescriptionNotNull(sharedId)
                .map(
                        entity -> entity.getDescription()
                                .orElseThrow(() -> new ProcessingException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(PostDescriptionEntity.class.getSimpleName())))
                )
                .orElse(null);
    }

    private String map(PostDescriptionEntity entity) {
        return entity.getDescription().orElse(null);
    }
}