package dev.codex.java.tool.persistence.repository;

import dev.codex.java.tool.persistence.entity.ProjectCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "projectCategoryRepository")
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategoryEntity, Long> {
    Optional<ProjectCategoryEntity> findByName(String name);
}