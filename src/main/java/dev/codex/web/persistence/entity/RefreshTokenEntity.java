package dev.codex.web.persistence.entity;

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

@Entity(name = PersistenceConstants.REFRESH_TOKEN_ENTITY_NAME)
@Table(name = PersistenceConstants.REFRESH_TOKEN_TABLE_NAME)
public class RefreshTokenEntity {
    @Id
    @GenericGenerator(name = "refresh-token-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "refresh-token-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "token", nullable = false, length = 511)
    private String token;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public RefreshTokenEntity() {
        super();
    }

    public RefreshTokenEntity(Long id, String token, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.token = token;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static RefreshTokenEntityBuilder builder() {
        return new RefreshTokenEntityBuilder();
    }

    public static class RefreshTokenEntityBuilder {
        private final RefreshTokenEntity entity;

        public RefreshTokenEntityBuilder() {
            this.entity = new RefreshTokenEntity();
        }

        public RefreshTokenEntityBuilder token(String token) {
            this.entity.setToken(token);
            return this;
        }

        public RefreshTokenEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public RefreshTokenEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public RefreshTokenEntity build() {
            return this.entity;
        }
    }

    public Long id() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
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
        if ( !( o instanceof RefreshTokenEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.token, that.token )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.token != null ? this.token.hashCode() : 0);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RefreshTokenEntity[" +
                "id=" + this.id +
                ", token='" + this.token + "'" +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}