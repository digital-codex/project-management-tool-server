package dev.codex.java.tool.persistence.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Optional;

@Entity(name = "ProjectEntity")
@Table(name = "t_project", catalog = "postgres", schema = "public")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project-sequence")
    @SequenceGenerator(name = "project-sequence", sequenceName = "s_project_id", catalog = "postgres", schema = "public", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false, updatable = false, table = "t_project")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, table = "t_project", length = 80)
    private String name;

    @Column(name = "key", unique = true, nullable = false, table = "t_project", length = 10)
    private String key;

    @ManyToOne(targetEntity = ProjectCategoryEntity.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "project_category_id", referencedColumnName = "id", table = "t_project")
    private ProjectCategoryEntity category;

    public ProjectEntity() {
        super();
    }

    public ProjectEntity(Long id, String name, String key, ProjectCategoryEntity category) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.category = category;
    }

    public static ProjectEntityBuilder builder() {
        return new ProjectEntityBuilder();
    }

    public static class ProjectEntityBuilder {
        private final ProjectEntity entity;

        public ProjectEntityBuilder() {
            this.entity = new ProjectEntity();
        }

        public ProjectEntityBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public ProjectEntityBuilder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public ProjectEntityBuilder key(String key) {
            this.entity.setKey(key);
            return this;
        }

        public ProjectEntityBuilder category(ProjectCategoryEntity category) {
            this.entity.setCategory(category);
            return this;
        }

        public ProjectEntity build() {
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Optional<ProjectCategoryEntity> getCategory() {
        return this.category == null ? Optional.empty() : Optional.of(this.category);
    }

    public void setCategory(ProjectCategoryEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectEntity that)) return false;
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
        return "ProjectEntity{" +
                "id=" + this.getId().toString() +
                ", name='" + this.getName() + "'" +
                ", key='" + this.getKey() + "'" +
                ", category=" + this.getCategory().map(ProjectCategoryEntity::toString).orElse("null") +
                "}";
    }
}