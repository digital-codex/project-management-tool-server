package dev.digitalcodex.web.application.service;

import dev.digitalcodex.web.application.ApplicationConstants;
import dev.digitalcodex.web.application.data.ForumModelData;
import dev.digitalcodex.web.persistence.entity.ForumEntity;
import dev.digitalcodex.web.persistence.repository.ForumRepository;
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
    public ForumModelData create(ForumModelData data) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.map(
                this.repository.save(
                        ForumEntity.builder()
                                .name(data.getName())
                                .description(data.getDescription())
                                .insertedBy(this.userService.loadIdByUsername(username))
                                .insertedAt(Instant.now())
                                .build()
                )
        );
    }

    @Transactional(readOnly = true)
    public List<ForumModelData> read() {
        return this.repository.findAll().stream().map(this::map).toList();
    }

    private ForumModelData map(ForumEntity entity) {
        ForumModelData data = new ForumModelData();
        data.setName(entity.getName());
        data.setDescription(entity.getDescription());
        data.setPostCount(this.postService.loadCountByForumId(entity.getId()));
        return data;
    }
}
