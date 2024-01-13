package dev.digitalcodex.web.persistence.entity;

import dev.digitalcodex.web.application.exception.ProcessingException;
import dev.digitalcodex.web.persistence.PersistenceConstants;
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

@Entity(name = PersistenceConstants.FORUM_ENTITY_NAME)
@Table(name = PersistenceConstants.FORUM_TABLE_NAME)
public final class ForumEntity {
    @Id
    @GenericGenerator(name = "forum-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "forum-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 31)
    private String name;

    @Column(name = "description", nullable = false, length = 511)
    private String description;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public ForumEntity() {
        super();
    }

    public ForumEntity(Long id, String name, String description, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static ForumEntityBuilder builder() {
        return new ForumEntityBuilder();
    }

    public static class ForumEntityBuilder {
        private final ForumEntity entity;

        public ForumEntityBuilder() {
            this.entity = new ForumEntity();
        }

        public ForumEntityBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public ForumEntityBuilder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public ForumEntityBuilder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public ForumEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public ForumEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public ForumEntity build() {
            return this.entity;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("name", ForumEntity.class.getSimpleName()));

        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("description", ForumEntity.class.getSimpleName()));

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
        if ( !( o instanceof ForumEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.name, that.name )
                && Objects.equals( this.description, that.description )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.name != null ? this.name.hashCode() : 0);
        result = PRIME * result + (this.description != null ? this.description.hashCode() : 0);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ForumEntity[" +
                "id=" + this.id +
                ", name='" + this.name + "'" +
                ", description='" + this.description + "'" +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}
