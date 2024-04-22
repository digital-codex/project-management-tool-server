package dev.codex.java.tool.persistence.repository;

import dev.codex.java.tool.persistence.entity.ProjectCategoryDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "projectCategoryDescriptionRepository")
public interface ProjectCategoryDescriptionRepository extends JpaRepository<ProjectCategoryDescriptionEntity, Long> {
}