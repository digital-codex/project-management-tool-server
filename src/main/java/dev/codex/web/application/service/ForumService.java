package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.ForumModelData;
import dev.codex.web.application.data.PostModelData;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.entity.ForumEntity;
import dev.codex.web.persistence.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service(ApplicationConstants.FORUM_SERVICE_BEAN_NAME)
public class ForumService {
    private final ForumRepository repository;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public ForumService(ForumRepository repository, UserService userService, PostService postService) {
        this.repository = repository;
        this.userService = userService;
        this.postService = postService;
    }

    @Transactional
    public ForumModelData save(ForumModelData data) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = this.userService.loadIdByUsername(username);
        return this.save(data, userId);
    }

    @Transactional
    public ForumModelData save(ForumModelData data, Long userId) {
        return this.map(
                this.repository.save(
                        ForumEntity.builder()
                                .name(data.getName())
                                .description(data.getDescription())
                                .insertedBy(userId)
                                .insertedAt(Instant.now())
                                .build()
                )
        );
    }

    @Transactional(readOnly = true)
    public List<ForumModelData> loadAll() {
        return this.repository.findAll().stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    @Transactional
    public PostModelData checkAndSave(PostModelData data) {
        if (data.getForumId() == null || !this.existsById(data.getForumId()))
            throw new ProcessingException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(ForumEntity.class.getSimpleName()));

        return this.postService.save(data);
    }

    private ForumModelData map(ForumEntity entity) {
        ForumModelData data = new ForumModelData();
        data.setId(entity.id());
        data.setName(entity.getName());
        data.setDescription(entity.getDescription());
        data.setPostCount(this.postService.loadCountByForumId(entity.id()));
        return data;
    }
}