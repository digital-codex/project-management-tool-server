package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.PostModelData;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.application.util.Strings;
import dev.codex.web.persistence.entity.PostEntity;
import dev.codex.web.persistence.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service(ApplicationConstants.POST_SERVICE_BEAN_NAME)
public class PostService {
    private final PostRepository repository;
    private final PostDescriptionService descriptionService;
    private final UserService userService;
    private final VoteService voteService;
    private final CommentService commentService;

    @Autowired
    public PostService(PostRepository repository, PostDescriptionService descriptionService, UserService userService, VoteService voteService, CommentService commentService) {
        this.repository = repository;
        this.descriptionService = descriptionService;
        this.userService = userService;
        this.voteService = voteService;
        this.commentService = commentService;
    }

    @Transactional
    public PostModelData save(PostModelData data) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = this.userService.loadIdByUsername(username);
        if (Strings.hasText(data.getDescription())) {
            PostModelData persisted = this.save(data, userId);
            persisted.setDescription(this.descriptionService.save(persisted.getId(), data.getDescription(), userId));
            return persisted;
        }
        return this.save(data, userId);
    }

    @Transactional
    public PostModelData save(PostModelData data, Long userId) {
        if (data.getForumId() == null)
            throw new ProcessingException(ProcessingException.NULL_POINTER_EXCEPTION_MSG_FORMAT.formatted("forumId"));

        return this.map(
                this.repository.save(
                        PostEntity.builder()
                                .forumId(data.getForumId())
                                .title(data.getTitle())
                                .url(data.getUrl())
                                .insertedBy(userId)
                                .insertedAt(Instant.now())
                                .build()
                )
        );
    }

    @Transactional(readOnly = true)
    public List<PostModelData> loadAll() {
        return this.repository.findAll().stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public PostModelData loadById(Long id) {
        return this.map(
                this.repository.findById(id)
                        .orElseThrow(
                                () -> new ProcessingException(ProcessingException.RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT.formatted(PostEntity.class.getSimpleName()))
                        )
        );
    }

    @Transactional(readOnly = true)
    public List<PostModelData> loadAllByForumId(Long forumId) {
        return this.repository.findAllByForumId(forumId).stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public List<PostModelData> loadAllByUserEntityUsername(String username) {
        return this.repository.findAllByUserEntityUsername(username).stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public int loadCountByForumId(Long formId) {
        return this.repository.countByForumId(formId);
    }

    private PostModelData map(PostEntity entity) {
        PostModelData data = new PostModelData();
        data.setId(entity.getId());
        data.setForumId(entity.getForumId());
        data.setTitle(entity.getTitle());
        data.setUrl(entity.getUrl());
        data.setDescription(this.descriptionService.loadBySharedId(entity.getId()));
        data.setVoteCount(this.voteService.loadCountByPostId(entity.getId()));
        data.setCommentCount(this.commentService.loadCountByPostId(entity.getId()));
        return data;
    }
}