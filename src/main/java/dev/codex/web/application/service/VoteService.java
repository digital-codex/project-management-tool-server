package dev.codex.web.application.service;

import dev.codex.web.application.ApplicationConstants;
import dev.codex.web.application.data.NotificationMail;
import dev.codex.web.application.data.UserModelData;
import dev.codex.web.application.data.VoteModelData;
import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.application.type.VoteType;
import dev.codex.web.persistence.entity.VoteEntity;
import dev.codex.web.persistence.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service(ApplicationConstants.VOTE_SERVICE_BEAN_NAME)
public class VoteService {
    private final VoteRepository repository;
    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public VoteService(VoteRepository repository, UserService userService, MailService mailService) {
        this.repository = repository;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Transactional
    public VoteModelData save(VoteModelData data, String postUrl) {
        return this.save(data, this.userService.loadCurrentUser(), postUrl);
    }

    @Transactional
    public VoteModelData save(VoteModelData data, UserModelData user, String postUrl) {
        if (data.getPostId() == null)
            throw new ProcessingException(ProcessingException.NULL_POINTER_EXCEPTION_MSG_FORMAT.formatted("postId"));

        int vote = data.getVote();
        VoteType voteType;
        if (vote == 1)
            voteType = VoteType.UP_VOTE;
        else if (vote == -1)
            voteType = VoteType.DOWN_VOTE;
        else
            throw new ProcessingException(ProcessingException.NULL_POINTER_EXCEPTION_MSG_FORMAT.formatted("voteType"));

        VoteModelData current = this.loadByPostIdAndInsertedBy(data.getPostId(), user.getId());
        if (current != null && current.getVote() == data.getVote())
            throw new ProcessingException(ProcessingException.INVALID_RESULT_COUNT_EXCEPTION_MSG_FORMAT.formatted(0, 1));
        else if (current != null)
            this.repository.deleteById(current.getId());

        VoteModelData persisted = this.map(
                this.repository.save(
                        VoteEntity.builder()
                                .postId(data.getPostId())
                                .voteType(voteType)
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
    public List<VoteModelData> loadAllByPostId(Long postId) {
        return this.repository.findAllByPostId(postId).stream().map(this::map).toList();
    }

    @Transactional(readOnly = true)
    public VoteModelData loadByPostIdAndInsertedBy(Long postId, Long insertedBy) {
        return this.repository.findByPostIdAndInsertedBy(postId, insertedBy)
                .map(this::map)
                .orElse(null);
    }

    private VoteModelData map(VoteEntity entity) {
        VoteModelData data = new VoteModelData();
        data.setId(entity.id());
        data.setPostId(entity.getPostId());
        data.setVote(entity.getVoteType().value());
        return data;
    }
}