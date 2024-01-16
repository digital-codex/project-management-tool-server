package dev.codex.web.application.data;

import java.util.Objects;

public final class ForumModelData {
    private Long id;
    private String name;
    private String description;
    private int postCount;

    public ForumModelData() {
        super();
    }

    public ForumModelData(Long id) {
        this.id = id;
    }

    public ForumModelData(String name, String description, int postCount) {
        this.name = name;
        this.description = description;
        this.postCount = postCount;
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