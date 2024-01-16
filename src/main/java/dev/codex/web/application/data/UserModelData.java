package dev.codex.web.application.data;

import java.util.Objects;

public final class UserModelData {
    private String username;
    private String password;
    private String email;
    private boolean enabled;

    public UserModelData() {
        super();
    }

    public UserModelData(String username, String password, String email, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof UserModelData that ) ) return false;
        return Objects.equals( this.username, that.username )
                && Objects.equals( this.password, that.password )
                && Objects.equals( this.email, that.email )
                && this.enabled == that.enabled;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.username != null ? this.username.hashCode() : 0;
        result = PRIME * result + (this.password != null ? this.password.hashCode() : 0);
        result = PRIME * result + (this.email != null ? this.email.hashCode() : 0);
        result = PRIME * result + (this.enabled ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return "UserModelData[" +
                "username='" + this.username + "'" +
                ", password='" + this.password + "'" +
                ", email='" + this.email + "'" +
                ", enabled=" + this.enabled +
                "]";
    }
}