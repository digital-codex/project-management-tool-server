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

@Entity(name = PersistenceConstants.VERIFICATION_TOKEN_ENTITY_NAME)
@Table(name = PersistenceConstants.VERIFICATION_TOKEN_TABLE_NAME)
public final class VerificationTokenEntity {
    @Id
    @GenericGenerator(name = "verification-token-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "verification-token-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "token", nullable = false, length = 511)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public VerificationTokenEntity() {
        super();
    }

    public VerificationTokenEntity(Long id, String token, Instant expiresAt, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.token = token;
        this.expiresAt = expiresAt;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static VerificationTokenEntityBuilder builder() {
        return new VerificationTokenEntityBuilder();
    }

    public static class VerificationTokenEntityBuilder {
        private final VerificationTokenEntity entity;

        public VerificationTokenEntityBuilder() {
            this.entity = new VerificationTokenEntity();
        }

        public VerificationTokenEntityBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public VerificationTokenEntityBuilder token(String token) {
            this.entity.setToken(token);
            return this;
        }

        public VerificationTokenEntityBuilder expiresAt(Instant expiresAt) {
            this.entity.setExpiresAt(expiresAt);
            return this;
        }

        public VerificationTokenEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public VerificationTokenEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public VerificationTokenEntity build() {
            return this.entity;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
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
        if ( !( o instanceof VerificationTokenEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.token, that.token )
                && Objects.equals( this.expiresAt, that.expiresAt )
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.token != null ? this.token.hashCode() : 0);
        result = PRIME * result + (this.expiresAt != null ? this.expiresAt.hashCode() : 0);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VerificationTokenEntity[" +
                "id=" + this.id +
                ", token='" + this.token + "'" +
                ", expiresAt=" + this.expiresAt +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}