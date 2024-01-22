package dev.codex.web.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public final class UserModelData {
    @Schema(name = "id", description = "The identifier of this User", example = "1")
    private Long id;
    @Schema(name = "username", description = "The username of this User", example = "TestUser")
    private String username;
    @Schema(name = "password", description = "The password of this User", hidden = true)
    private String password;
    @Schema(name = "email", description = "The email of this User", example = "TestEmail@testmail.com")
    private String email;
    @Schema(name = "enabled", description = "The enable status of this User", hidden = true)
    private boolean enabled;
    @Schema(name = "authenticationToken", description = "The current JWT authentication token of this User")
    private String authenticationToken;
    @Schema(name = "refreshToken", description = "The current RefreshToken of this User")
    private String refreshToken;

    public UserModelData() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SuppressWarnings("unused")
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @SuppressWarnings("unused")
    public String getAuthenticationToken() {
        return this.authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof UserModelData that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.username, that.username )
                && Objects.equals( this.password, that.password )
                && Objects.equals( this.email, that.email )
                && this.enabled == that.enabled
                && Objects.equals( this.authenticationToken, that.authenticationToken)
                && Objects.equals( this.refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) (this.id ^ (this.id >>> 32)) : 0;
        result = PRIME * result + (this.username != null ? this.username.hashCode() : 0);
        result = PRIME * result + (this.password != null ? this.password.hashCode() : 0);
        result = PRIME * result + (this.email != null ? this.email.hashCode() : 0);
        result = PRIME * result + (this.enabled ? 1231 : 1237);
        result = PRIME * result + (this.authenticationToken != null ? this.authenticationToken.hashCode() : 0);
        result = PRIME * result + (this.refreshToken != null ? this.refreshToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserModelData[" +
                "id=" + this.id +
                ", username='" + this.username + "'" +
                ", password='" + this.password + "'" +
                ", email='" + this.email + "'" +
                ", enabled=" + this.enabled +
                ", authenticationToken='" + this.authenticationToken + "'" +
                ", refreshToken='" + this.refreshToken + "'" +
                "]";
    }
}