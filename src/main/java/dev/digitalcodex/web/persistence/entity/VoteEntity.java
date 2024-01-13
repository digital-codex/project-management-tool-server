package dev.digitalcodex.web.persistence.entity;

import dev.digitalcodex.web.application.type.VoteType;
import dev.digitalcodex.web.persistence.PersistenceConstants;
import dev.digitalcodex.web.persistence.converter.VoteTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Entity(name = PersistenceConstants.VOTE_ENTITY_NAME)
@Table(name = PersistenceConstants.VOTE_TABLE_NAME)
public final class VoteEntity {
    @Id
    @GenericGenerator(name = "vote-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "vote-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "post_id", nullable = false, updatable = false)
    private Long postId;

    @Convert(converter = VoteTypeConverter.class)
    @Column(name = "vote_type", nullable = false, length = 63)
    private VoteType voteType;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public VoteEntity() {
        super();
    }

    public VoteEntity(Long id, Long postId, VoteType voteType, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.postId = postId;
        this.voteType = voteType;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static VoteEntityBuilder builder() {
        return new VoteEntityBuilder();
    }

    public static class VoteEntityBuilder {
        private final VoteEntity entity;

        public VoteEntityBuilder() {
            this.entity = new VoteEntity();
        }

        public VoteEntityBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public VoteEntityBuilder postId(Long postId) {
            this.entity.setPostId(postId);
            return this;
        }

        public VoteEntityBuilder voteType(VoteType voteType) {
            this.entity.setVoteType(voteType);
            return this;
        }

        public VoteEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public VoteEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public VoteEntity build() {
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

    public VoteType getVoteType() {
        return this.voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
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
        if ( !( o instanceof VoteEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.postId, that.postId )
                && Objects.equals( this.voteType, that.voteType )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.postId != null ? (int) ( this.postId ^ ( this.postId >>> 32 ) ) : 0);
        result = PRIME * result + (this.voteType != null ? this.voteType.hashCode() : 0);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VoteEntity[" +
                "id=" + this.id +
                ", postId=" + this.postId +
                ", voteType=" + this.voteType +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}
