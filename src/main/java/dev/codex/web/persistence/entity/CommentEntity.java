package dev.codex.web.persistence.entity;

import dev.codex.web.application.exception.ProcessingException;
import dev.codex.web.persistence.PersistenceConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Entity(name = PersistenceConstants.COMMENT_ENTITY_NAME)
@Table(name = PersistenceConstants.COMMENT_TABLE_NAME)
public final class CommentEntity {
    @Id
    @GenericGenerator(name = "comment-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "comment-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "post_id", nullable = false, updatable = false)
    private Long postId;

    @Column(name = "description", nullable = false, length = 511)
    private String description;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public CommentEntity() {
        super();
    }

    public CommentEntity(Long id, Long postId, String description, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.postId = postId;
        this.description = description;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static CommentEntityBuilder builder() {
        return new CommentEntityBuilder();
    }

    public static class CommentEntityBuilder {
        private final CommentEntity entity;

        public CommentEntityBuilder() {
            this.entity = new CommentEntity();
        }

        public CommentEntityBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public CommentEntityBuilder postId(Long postId) {
            this.entity.setPostId(postId);
            return this;
        }

        public CommentEntityBuilder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public CommentEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public CommentEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public CommentEntity build() {
            return this.entity;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("description", CommentEntity.class.getSimpleName()));

        this.description = description;
    }

    public Optional<Long> getInsertedBy() {
        return Optional.ofNullable(this.insertedBy);
    }

    public void setInsertedBy(Long insertedBy) {
        this.insertedBy = insertedBy;
    }

    public Optional<Instant> getInsertedAt() {
        return Optional.ofNullable(this.insertedAt);
    }

    public void setInsertedAt(Instant insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof CommentEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.postId, that.postId )
                && Objects.equals( this.description, that.description )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.postId != null ? (int) ( this.postId ^ ( this.postId >>> 32 ) ) : 0);
        result = PRIME * result + (this.description != null ? this.description.hashCode() : 0);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentEntity[" +
                "id=" + this.id +
                ", postId=" + this.postId +
                ", description='" + this.description + "'" +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}