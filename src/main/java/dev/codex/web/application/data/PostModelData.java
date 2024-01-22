package dev.codex.web.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public final class PostModelData {
    @Schema(name = "id", description = "The identifier of this Post", example = "1")
    private Long id;
    @Schema(name = "forumId", description = "The identifier of the Forum this Post belongs to", example = "1")
    private Long forumId;
    @Schema(name = "title", description = "The title of this Post", example = "Test Title")
    private String title;
    @Schema(name = "url", description = "The API endpoint to access this Post", example = "https://localhost:8080/api/posts/420")
    private String url;
    @Schema(name = "description", description = "The body of this Post", example = "Test Description")
    private String description;
    @Schema(name = "voteCount", description = "The current accumulated number of UP_VOTEs and DOWN_VOTEs for this Post", example = "0")
    private int voteCount;
    @Schema(name = "commentCount", description = "The number of comments this Post currently has", example = "0")
    private int commentCount;

    public PostModelData() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public int getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @SuppressWarnings("unused")
    public int getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof PostModelData that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.forumId, that.forumId )
                && Objects.equals( this.title, that.title )
                && Objects.equals( this.url, that.url )
                && Objects.equals( this.description, that.description )
                && this.voteCount == that.voteCount
                && this.commentCount == that.commentCount;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) (this.id ^ (this.id >>> 32)) : 0;
        result = PRIME * result + (this.forumId != null ? (int) (this.forumId ^ (this.forumId >>> 32)) : 0);
        result = PRIME * result + (this.title != null ? this.title.hashCode() : 0);
        result = PRIME * result + (this.url != null ? this.url.hashCode() : 0);
        result = PRIME * result + (this.description != null ? this.description.hashCode() : 0);
        result = PRIME * result + this.voteCount;
        result = PRIME * result + this.commentCount;
        return result;
    }

    @Override
    public String toString() {
        return "PostModelData[" +
                "id=" + this.id +
                ", forumId=" + this.forumId +
                ", title='" + this.title + "'" +
                ", url='" + this.url + "'" +
                ", description='" + this.description + "'" +
                ", voteCount=" + this.voteCount +
                ", commentCount=" + this.commentCount +
                "]";
    }
}