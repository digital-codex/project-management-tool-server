package dev.codex.java.tool.persistence.repository;

import dev.codex.java.tool.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}