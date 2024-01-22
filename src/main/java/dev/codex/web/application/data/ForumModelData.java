package dev.codex.web.application.data;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public final class ForumModelData {
    @Schema(name = "id", description = "The identifier of this Forum", example = "1")
    private Long id;
    @Schema(name = "name", description = "The name of this Forum", example = "/test-forum")
    private String name;
    @Schema(name = "description", description = "The description of this Forum", example = "Test Description")
    private String description;
    @Schema(name = "postCount", description = "The number of posts this Forum currently has", example = "0")
    private int postCount;

    public ForumModelData() {
        super();
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
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public int getPostCount() {
        return this.postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this ) return true;
        if ( !( o instanceof ForumModelData that ) ) return false;
        return Objects.equals( this.id, that.id )
                && Objects.equals( this.name, that.name )
                && Objects.equals( this.description, that.description )
                && this.postCount == that.postCount;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = this.id != null ? (int) (this.id ^ (this.id >>> 32)) : 0;
        result = PRIME * result + (this.name != null ? this.name.hashCode() : 0);
        result = PRIME * result + (this.description != null ? this.description.hashCode() : 0);
        result = PRIME * result + this.postCount;
        return result;
    }

    @Override
    public String toString() {
        return "ForumModelData[" +
                "id=" + this.id +
                ", name='" + this.name + "'" +
                ", description='" + this.description + "'" +
                ", postCount=" + this.postCount +
                "]";
    }
}