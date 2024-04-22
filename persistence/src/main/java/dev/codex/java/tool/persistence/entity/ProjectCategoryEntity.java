package dev.codex.java.tool.persistence.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Optional;

@Entity(name = "ProjectCategoryEntity")
@Table(name = "t_project_category", catalog = "postgres", schema = "public")
public class ProjectCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project-category-sequence")
    @SequenceGenerator(name = "project-category-sequence", sequenceName = "s_project_category_id", catalog = "postgres", schema = "public", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, updatable = false, table = "t_project_category")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, table = "t_project_category")
    private String name;

    @OneToOne(targetEntity = ProjectCategoryDescriptionEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "shared_id")
    private ProjectCategoryDescriptionEntity description;

    public ProjectCategoryEntity() {
        super();
    }

    public ProjectCategoryEntity(Long id, String name, ProjectCategoryDescriptionEntity description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static ProjectCategoryEntityBuilder builder() {
        return new ProjectCategoryEntityBuilder();
    }

    public static class ProjectCategoryEntityBuilder {
        private final ProjectCategoryEntity entity;

        public ProjectCategoryEntityBuilder() {
            this.entity = new ProjectCategoryEntity();
        }

        public ProjectCategoryEntityBuilder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public ProjectCategoryEntityBuilder description(ProjectCategoryDescriptionEntity description) {
            this.entity.setDescription(description);
            return this;
        }

        public ProjectCategoryEntity build() {
            return this.entity;
        }
    }

    public Long getId() {
        return this.id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<ProjectCategoryDescriptionEntity> getDescription() {
        return this.description == null ? Optional.empty() : Optional.of(this.description);
    }

    public void setDescription(ProjectCategoryDescriptionEntity description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectCategoryEntity that)) return false;
        return Objects.equals(this.getId(), that.getId())
                && Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), this.getDescription());
    }

    @Override
    public String toString() {
        return "ProjectCategoryEntity{" +
                "id=" + this.getId().toString() +
                ", name='" + this.getName() + "'" +
                ", description=" + this.getDescription().map(ProjectCategoryDescriptionEntity::toString).orElse("null") +
                "}";
    }
}