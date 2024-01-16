package dev.codex.web.application.data;

import java.util.Objects;

public final class CommentModelData {
    private Long id;
    private Long postId;
    private String description;

    public CommentModelData() {
        super();
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
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof CommentModelData that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.postId, that.postId )
                && Objects.equals( this.description, that.description );
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) (this.id ^ (this.id >>> 32)) : 0;
        result = PRIME * result + (this.postId != null ? (int) (this.postId ^ (this.postId >>> 32)) : 0);
        result = PRIME * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentModelData[" +
                "id=" + this.id +
                ", postId=" + this.postId +
                ", description='" + this.description + "'" +
                "]";
    }
}