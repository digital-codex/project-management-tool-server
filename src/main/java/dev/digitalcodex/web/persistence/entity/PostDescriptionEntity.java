package dev.digitalcodex.web.persistence.entity;

import dev.digitalcodex.web.persistence.PersistenceConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Entity(name = PersistenceConstants.POST_DESCRIPTION_ENTITY_NAME)
@Table(name = PersistenceConstants.POST_DESCRIPTION_TABLE_NAME)
public final class PostDescriptionEntity {
    @Id
    @Column(name = PersistenceConstants.SHARED_ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long sharedId;

    @Column(name = "description")
    private char[] description;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public PostDescriptionEntity() {
        super();
    }

    public PostDescriptionEntity(Long sharedId, String description, Long insertedBy, Instant insertedAt) {
        this.sharedId = sharedId;
        this.description = description.toCharArray();
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static PostDescriptionEntityBuilder builder() {
        return new PostDescriptionEntityBuilder();
    }

    public static class PostDescriptionEntityBuilder {
        private final PostDescriptionEntity entity;

        public PostDescriptionEntityBuilder() {
            this.entity = new PostDescriptionEntity();
        }

        public PostDescriptionEntityBuilder sharedId(Long sharedId) {
            this.entity.setSharedId(sharedId);
            return this;
        }

        public PostDescriptionEntityBuilder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public PostDescriptionEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public PostDescriptionEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public PostDescriptionEntity build() {
            return this.entity;
        }
    }

    public Long getSharedId() {
        return this.sharedId;
    }

    public void setSharedId(Long sharedId) {
        this.sharedId = sharedId;
    }

    public Optional<String> getDescription() {
        return (this.description != null && this.description.length != 0)
                ? Optional.of(new String(this.description))
                : Optional.empty();
    }

    public void setDescription(String description) {
        this.description = description.toCharArray();
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
        if (o == this ) return true;
        if ( !( o instanceof PostDescriptionEntity that ) ) return false;
        return Objects.equals( this.sharedId, that.sharedId )
                && Arrays.equals( this.description, that.description )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.sharedId != null ? (int) ( this.sharedId ^ ( this.sharedId >>> 32 ) ) : 0;
        result = PRIME * result + Arrays.hashCode( this.description );
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostDescriptionEntity[" +
                "sharedId=" + this.sharedId +
                ", description='" + Arrays.toString( this.description ) +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}
