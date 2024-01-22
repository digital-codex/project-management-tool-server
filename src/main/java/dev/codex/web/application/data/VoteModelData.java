package dev.codex.web.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public final class VoteModelData {
    @Schema(name = "id", description = "The identifier of this Vote", example = "1")
    private Long id;
    @Schema(name = "postId", description = "The identifier of the Post this Vote belongs to", example = "1")
    private Long postId;
    @Schema(name = "vote", description = "The scalar value of this Vote", allowableValues = {"-1", "1"})
    private int vote;

    public VoteModelData() {
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

    public int getVote() {
        return this.vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof VoteModelData that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.postId, that.postId )
                && this.vote == that.vote;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) (this.id ^ (this.id >>> 32)) : 0;
        result = PRIME * result + (this.postId != null ? (int) (this.postId ^ (this.postId >>> 32)) : 0);
        result = PRIME * result + this.vote;
        return result;
    }

    @Override
    public String toString() {
        return "VoteModelData[" +
                "id=" + this.id +
                ", postId=" + this.postId +
                ", vote=" + this.vote +
                "]";
    }
}