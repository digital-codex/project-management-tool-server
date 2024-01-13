package dev.digitalcodex.web.persistence;

public class PersistenceConstants {
    public static final String USER_ENTITY_NAME = "UserEntity";
    public static final String VERIFICATION_TOKEN_ENTITY_NAME = "VerificationTokenEntity";
    public static final String FORUM_ENTITY_NAME = "ForumEntity";
    public static final String POST_ENTITY_NAME = "PostEntity";
    public static final String POST_DESCRIPTION_ENTITY_NAME = "PostDescriptionEntity";
    public static final String VOTE_ENTITY_NAME = "VoteEntity";
    public static final String COMMENT_ENTITY_NAME = "CommentEntity";

    public static final String ID_ATTRIBUTE_NAME = "id";
    public static final String SHARED_ID_ATTRIBUTE_NAME = "shared_id";

    public static final String INSERTED_BY_ATTRIBUTE_NAME = "inserted_by";
    public static final String INSERTED_AT_ATTRIBUTE_NAME = "inserted_at";

    public static final String USER_TABLE_NAME = "t_user";
    public static final String VERIFICATION_TOKEN_TABLE_NAME = "t_verification_token";
    public static final String FORUM_TABLE_NAME = "t_forum";
    public static final String POST_TABLE_NAME = "t_post";
    public static final String POST_DESCRIPTION_TABLE_NAME = "t_post_description";
    public static final String VOTE_TABLE_NAME = "t_vote";
    public static final String COMMENT_TABLE_NAME = "t_comment";

    public static final String USER_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.UserRepository";
    public static final String VERIFICATION_TOKEN_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.VerificationTokenRepository";
    public static final String FORUM_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.ForumRepository";
    public static final String POST_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.PostRepository";
    public static final String POST_DESCRIPTION_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.PostDescriptionRepository";
    public static final String VOTE_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.VoteRepository";
    public static final String COMMENT_REPOSITORY_BEAN_NAME = "dev.digitalcodex.web.persistence.repository.CommentRepository";
}
