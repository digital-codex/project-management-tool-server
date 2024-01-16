package dev.codex.web.application.data;

import java.util.Objects;

public final class PostModelData {
    private Long id;
    private Long forumId;
    private String title;
    private String url;
    private String description;
    private int voteCount;
    private int commentCount;

    public PostModelData() {
        super();
    }

    public PostModelData(Long id) {
        this.id = id;
    }

    public PostModelData(Long forumId, String title, String url, String description, int voteCount, int commentCount) {
        this.forumId = forumId;
        this.title = title;
        this.url = url;
        this.description = description;
        this.voteCount = voteCount;
        this.commentCount = commentCount;
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

    public int getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

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