package dev.codex.java.tool.persistence.entity;

import jakarta.persistence.*;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "ProjectCategoryDescriptionEntity")
@Table(name = "t_project_category_description", catalog = "postgres", schema = "public")
public class ProjectCategoryDescriptionEntity {
    @Id
    @Column(name = "shared_id", unique = true, nullable = false, updatable = false, table = "t_project_category_description")
    private Long id;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", table = "t_project_category_description")
    private byte[] description;

    public ProjectCategoryDescriptionEntity() {
        super();
    }

    public ProjectCategoryDescriptionEntity(Long id, String description) {
        this.id = id;
        this.description = description.getBytes(Charset.defaultCharset());
    }

    public static ProjectCategoryDescriptionEntityBuilder builder() {
        return new ProjectCategoryDescriptionEntityBuilder();
    }

    public static class ProjectCategoryDescriptionEntityBuilder {
        private final ProjectCategoryDescriptionEntity entity;

        public ProjectCategoryDescriptionEntityBuilder() {
            this.entity = new ProjectCategoryDescriptionEntity();
        }

        public ProjectCategoryDescriptionEntityBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public ProjectCategoryDescriptionEntityBuilder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public ProjectCategoryDescriptionEntity build() {
            return this.entity;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<String> getDescription() {
        return this.description == null ? Optional.empty() : Optional.of(new String(this.description, Charset.defaultCharset()));
    }

    public void setDescription(String description) {
        this.description = description.getBytes(Charset.defaultCharset());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectCategoryDescriptionEntity that)) return false;
        return Objects.equals(this.getId(), that.getId())
                && Objects.equals(this.getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getDescription());
    }

    @Override
    public String toString() {
        return "ProjectCategoryDescriptionEntity{" +
                "id=" + this.getId().toString() +
                ", description='" + this.getDescription().orElse("null") + "'" +
                "}";
    }
}