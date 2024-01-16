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

@Entity(name = PersistenceConstants.POST_ENTITY_NAME)
@Table(name = PersistenceConstants.POST_TABLE_NAME)
public final class PostEntity {
    @Id
    @GenericGenerator(name = "post-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "post-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "forum_id", nullable = false, updatable = false)
    private Long forumId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", unique = true, nullable = false, length = 2047)
    private String url;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public PostEntity() {
        super();
    }

    public PostEntity(Long id, Long forumId, String title, String url, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.forumId = forumId;
        this.title = title;
        this.url = url;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static PostEntityBuilder builder() {
        return new PostEntityBuilder();
    }

    public static class PostEntityBuilder {
        private final PostEntity entity;

        public PostEntityBuilder() {
            this.entity = new PostEntity();
        }

        public PostEntityBuilder forumId(Long forumId) {
            this.entity.setForumId(forumId);
            return this;
        }

        public PostEntityBuilder title(String title) {
            this.entity.setTitle(title);
            return this;
        }

        public PostEntityBuilder url(String url) {
            this.entity.setUrl(url);
            return this;
        }

        public PostEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public PostEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public PostEntity build() {
            return this.entity;
        }
    }

    public Long id() {
        return this.id;
    }

    public Long getForumId() {
        return this.forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("title", PostEntity.class.getSimpleName()));

        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if ( !( o instanceof PostEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.forumId, that.forumId )
                && Objects.equals( this.title, that.title )
                && Objects.equals( this.url, that.url )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.forumId != null ? (int) ( this.forumId ^ ( this.forumId >>> 32 ) ) : 0);
        result = PRIME * result + (this.title != null ? this.title.hashCode() : 0);
        result = PRIME * result + (this.url != null ? this.url.hashCode() : 0);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostEntity[" +
                "id=" + this.id +
                ", forumId=" + this.forumId +
                ", title='" + this.title + "'" +
                ", url='" + this.url + "'" +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}