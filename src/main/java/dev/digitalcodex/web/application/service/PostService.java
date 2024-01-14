package dev.digitalcodex.web.application.service;

import dev.digitalcodex.web.application.ApplicationConstants;
import dev.digitalcodex.web.persistence.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ApplicationConstants.POST_SERVICE_BEAN_NAME)
public class PostService {
    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public int loadCountByForumId(Long formId) {
        return this.repository.countByForumId(formId);
    }
}
