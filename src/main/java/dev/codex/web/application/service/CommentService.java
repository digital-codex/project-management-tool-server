package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.CommentModelData;
import dev.codex.web.application.data.NotificationMail;
import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.entity.CommentEntity;
import dev.codex.web.persistence.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service(ApplicationConstants.COMMENT_SERVICE_BEAN_NAME)
public class CommentService {
    private final CommentRepository repository;
    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public CommentService(CommentRepository repository, UserService userService, MailService mailService) {
        this.repository = repository;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Transactional
    public CommentModelData save(CommentModelData data, String postUrl) {
        return this.save(data, this.userService.loadCurrentUser(), postUrl);
    }

    @Transactional
    public CommentModelData save(CommentModelData data, UserModelData user, String postUrl) {
        if (data.getPostId() == null)
            throw new ProcessingException(ProcessingException.NULL_POINTER_EXCEPTION_MSG_FORMAT.formatted("postId"));

        CommentModelData persisted = this.map(
                this.repository.save(
                        CommentEntity.builder()
                                .postId(data.getPostId())
                                .description(data.getDescription())
                                .insertedBy(user.getId())
                                .insertedAt(Instant.now())
                                .build()
                )
        );

        this.mailService.send(
                new NotificationMail(user.getEmail(), MailService.COMMENT_ADDED_MAIL_TEXT_MSG_FORMAT.formatted(user.getUsername(), postUrl))
        );

        return persisted;
    }

    @Transactional(readOnly = true)
    public List<CommentModelData> loadAllByPostId(Long postId) {
        return this.repository.findAllByPostId(postId).stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public List<CommentModelData> loadAllByUserEntityUsername(String username) {
        return this.repository.findAllByUserEntityUsername(username).stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public int loadCountByPostId(Long postId) {
        return this.repository.countByPostId(postId);
    }

    private CommentModelData map(CommentEntity entity) {
        CommentModelData data = new CommentModelData();
        data.setId(entity.id());
        data.setPostId(entity.getPostId());
        data.setDescription(entity.getDescription());
        return data;
    }
}