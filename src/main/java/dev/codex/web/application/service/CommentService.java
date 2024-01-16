package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.persistence.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ApplicationConstants.COMMENT_SERVICE_BEAN_NAME)
public class CommentService {
    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public int loadCountByPostId(Long postId) {
        return this.repository.countByPostId(postId);
    }
}