package dev.codex.java.tool.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.codex.java.tool.presentation.request.ProjectRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProjectResponse {
    @JsonProperty(value = "name", required = true, index = 0, access = JsonProperty.Access.READ_ONLY)
    private String name;
    @JsonProperty(value = "key", required = true, index = 1, access = JsonProperty.Access.READ_ONLY)
    private String key;
    @JsonProperty(value = "category", index = 2, access = JsonProperty.Access.READ_ONLY)
    private String category;

    @JsonProperty(value = "errors", index = 3, access = JsonProperty.Access.READ_ONLY)
    private List<String> errors;

    public ProjectResponse() {
        super();
    }

    public ProjectResponse(String name, String key, String category) {
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

    public List<String> getErrors() {
        return this.errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addErrors(String error) {
        if (this.errors == null) {
            this.setErrors(new ArrayList<>());
        }

        this.errors.add(error);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectResponse that)) return false;
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
        return "ProjectResponse{" +
                "name='" + this.getName() + "'" +
                ", key='" + this.getKey() + "'" +
                ", category='" + this.getCategory().orElse("null") + "'" +
                "}";
    }
}