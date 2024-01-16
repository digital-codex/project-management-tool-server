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

@Entity(name = PersistenceConstants.USER_ENTITY_NAME)
@Table(name = PersistenceConstants.USER_TABLE_NAME)
public final class UserEntity {
    @Id
    @GenericGenerator(name = "user-generator", type = IncrementGenerator.class)
    @GeneratedValue(generator = "user-generator")
    @Column(name = PersistenceConstants.ID_ATTRIBUTE_NAME, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 63)
    private String username;

    @Column(name = "password", nullable = false, length = 63)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = PersistenceConstants.INSERTED_BY_ATTRIBUTE_NAME, updatable = false)
    private Long insertedBy;

    @Column(name = PersistenceConstants.INSERTED_AT_ATTRIBUTE_NAME, updatable = false)
    private Instant insertedAt;

    public UserEntity() {
        super();
    }

    public UserEntity(Long id, String username, String password, String email, boolean enabled, Long insertedBy, Instant insertedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.insertedBy = insertedBy;
        this.insertedAt = insertedAt;
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public static class UserEntityBuilder {
        private final UserEntity entity;

        public UserEntityBuilder() {
            this.entity = new UserEntity();
        }

        public UserEntityBuilder username(String username) {
            this.entity.setUsername(username);
            return this;
        }

        public UserEntityBuilder password(String password) {
            this.entity.setPassword(password);
            return this;
        }

        public UserEntityBuilder email(String email) {
            this.entity.setEmail(email);
            return this;
        }

        public UserEntityBuilder enabled(boolean enabled) {
            this.entity.setEnabled(enabled);
            return this;
        }

        public UserEntityBuilder insertedBy(Long insertedBy) {
            this.entity.setInsertedBy(insertedBy);
            return this;
        }

        public UserEntityBuilder insertedAt(Instant insertedAt) {
            this.entity.setInsertedAt(insertedAt);
            return this;
        }

        public UserEntity build() {
            return this.entity;
        }
    }

    public Long id() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        if (username == null || username.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("username", UserEntity.class.getSimpleName()));

        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("password", UserEntity.class.getSimpleName()));

        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank())
            throw new ProcessingException(ProcessingException.BLANK_STRING_EXCEPTION_MSG_FORMAT.formatted("email", UserEntity.class.getSimpleName()));

        this.email = email;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
        if ( !( o instanceof UserEntity that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.username, that.username )
                && Objects.equals( this.password, that.password )
                && Objects.equals( this.email, that.email )
                && this.enabled == that.enabled
                && Objects.equals( this.insertedBy, that.insertedBy )
                && Objects.equals( this.insertedAt, that.insertedAt );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) ( this.id ^ ( this.id >>> 32 ) ) : 0;
        result = PRIME * result + (this.username != null ? this.username.hashCode() : 0);
        result = PRIME * result + (this.password != null ? this.password.hashCode() : 0);
        result = PRIME * result + (this.email != null ? this.email.hashCode() : 0);
        result = PRIME * result + (this.enabled ? 1231 : 1237);
        result = PRIME * result + (this.insertedBy != null ? (int) ( this.insertedBy ^ ( this.insertedBy >>> 32 ) ) : 0);
        result = PRIME * result + (this.insertedAt != null ? this.insertedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity[" +
                "id=" + this.id +
                ", username='" + this.username + "'" +
                ", password='" + this.password + "'" +
                ", email='" + this.email + "'" +
                ", enabled=" + this.enabled +
                ", insertedBy=" + this.insertedBy +
                ", insertedAt=" + this.insertedAt +
                "]";
    }
}