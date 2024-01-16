package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.persistence.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ApplicationConstants.VOTE_SERVICE_BEAN_NAME)
public class VoteService {
    private final VoteRepository repository;

    @Autowired
    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public int loadCountByPostId(Long postId) {
        return this.repository.countByPostId(postId);
    }
}