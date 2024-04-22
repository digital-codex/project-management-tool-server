package dev.codex.java.tool.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;

public class ProjectRequest {
    @JsonProperty(value = "name", required = true, index = 0, access = JsonProperty.Access.READ_ONLY)
    private String name;
    @JsonProperty(value = "key", required = true, index = 1, access = JsonProperty.Access.READ_ONLY)
    private String key;
    @JsonProperty(value = "category", index = 2, access = JsonProperty.Access.READ_ONLY)
    private String category;

    public ProjectRequest() {
        super();
    }

    public ProjectRequest(String name, String key, String category) {
        this.name = name;
        this.key = key;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
         return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Optional<String> getCategory() {
        return this.category == null ? Optional.empty() : Optional.of(this.category);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectRequest that)) return false;
        return Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getKey(), that.getKey())
                && Objects.equals(this.getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getKey(), this.getCategory());
    }

    @Override
    public String toString() {
        return "ProjectRequest{" +
                "name='" + this.getName() + "'" +
                ", key='" + this.getKey() + "'" +
                ", category='" + this.getCategory().orElse("null") + "'" +
                "}";
    }
}