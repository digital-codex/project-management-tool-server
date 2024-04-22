package dev.codex.java.tool.application.model;

import java.util.Objects;
import java.util.Optional;

public class Project {
    //TODO: still unsure whether or not it is okay to keep id in DTO object
    private Long id;
    private String name;
    private String key;
    private ProjectCategory category;

    public Project() {
        super();
    }

    public Project(Long id, String name, String key, ProjectCategory category) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.category = category;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static class ProjectBuilder {
        private final Project object;

        public ProjectBuilder() {
            this.object = new Project();
        }

        public ProjectBuilder id(Long id) {
            this.object.setId(id);
            return this;
        }

        public ProjectBuilder name(String name) {
            this.object.setName(name);
            return this;
        }

        public ProjectBuilder key(String key) {
            this.object.setKey(key);
            return this;
        }

        public ProjectBuilder category(ProjectCategory category) {
            this.object.setCategory(category);
            return this;
        }

        public Project build() {
            return this.object;
        }
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Optional<ProjectCategory> getCategory() {
        return this.category == null ? Optional.empty() : Optional.of(this.category);
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Project that)) return false;
        return Objects.equals(this.getId(), that.getId())
                && Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getKey(), that.getKey())
                && Objects.equals(this.getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), this.getKey(), this.getCategory());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + this.getId().toString() +
                ", name='" + this.getName() + "'" +
                ", key='" + this.getKey() + "'" +
                ", category=" + this.getCategory().map(ProjectCategory::toString).orElse("null") +
                "}";
    }
}