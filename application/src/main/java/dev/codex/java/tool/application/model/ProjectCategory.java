package dev.codex.java.tool.application.model;

import java.util.Objects;
import java.util.Optional;

public class ProjectCategory {
    private String name;
    private String description;

    public ProjectCategory() {
        super();
    }

    public ProjectCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ProjectCategoryBuilder builder() {
        return new ProjectCategoryBuilder();
    }

    public static class ProjectCategoryBuilder {
        private final ProjectCategory object;

        public ProjectCategoryBuilder() {
            this.object = new ProjectCategory();
        }

        public ProjectCategoryBuilder name(String name) {
            this.object.setName(name);
            return this;
        }

        public ProjectCategoryBuilder description(String description) {
            this.object.setDescription(description);
            return this;
        }

        public ProjectCategory build() {
            return this.object;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getDescription() {
        return this.description == null ? Optional.empty() : Optional.of(this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectCategory that)) return false;
        return Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getDescription());
    }

    @Override
    public String toString() {
        return "ProjectCategory{" +
                "name='" + this.getName() + "'" +
                ", description='" + this.getDescription().orElse("null") + "'" +
                "}";
    }
}