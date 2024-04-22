package dev.codex.java.tool.application.service;

import dev.codex.java.tool.application.model.Project;
import dev.codex.java.tool.application.model.ProjectCategory;
import dev.codex.java.tool.persistence.entity.ProjectCategoryEntity;
import dev.codex.java.tool.persistence.entity.ProjectEntity;
import dev.codex.java.tool.persistence.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service(value = "projectService")
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectCategoryService projectCategoryService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectCategoryService projectCategoryService) {
        this.projectRepository = projectRepository;
        this.projectCategoryService = projectCategoryService;
    }

    @Transactional
    public Project save(Project project) {
        return this.mapFromEntity(this.projectRepository.save(this.mapToEntity(project)));
    }

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return this.projectRepository.findAll().stream().map(this::mapFromEntity).toList();
    }

    @Transactional(readOnly = true)
    //TODO: add custom exception and handling
    public Project findById(Long id) {
        return this.projectRepository.findById(Objects.requireNonNull(id, "`id` argument must not be null")).map(this::mapFromEntity).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Project update(Long id, Project project) {
        ProjectEntity entity = this.projectRepository.findById(Objects.requireNonNull(id, "`id` argument must not be null")).orElseThrow(IllegalArgumentException::new);
        boolean shouldMerge = false;
        if (project.getName() != null && entity.getName().compareToIgnoreCase(project.getName()) != 0) {
            entity.setName(project.getName());
            shouldMerge = true;
        }

        if ((project.getKey() != null && entity.getKey().compareToIgnoreCase(project.getKey()) != 0) || shouldMerge) {
            entity.setKey(project.getKey());
            shouldMerge = true;
        }

        if ((project.getCategory().isPresent() && Objects.equals(entity.getCategory().map(ProjectCategoryEntity::getName).orElse(null), project.getCategory().get().getName()) || shouldMerge)) {
            entity.setCategory(this.projectCategoryService.mapToEntity(project.getCategory().get()));
            shouldMerge = true;
        }

        if (shouldMerge) {
            entity = this.projectRepository.save(entity);
        }
        return this.mapFromEntity(entity);
    }

    public void deleteById(Long id) {
        this.projectRepository.deleteById(id);
    }

    public ProjectEntity mapToEntity(Project object) {
        return ProjectEntity.builder()
                .name(object.getName())
                .key(object.getKey())
                .category(this.mapCategoryToEntity(object))
                .build();
    }

    public ProjectCategoryEntity mapCategoryToEntity(Project object) {
        return object.getCategory().map(this.projectCategoryService::mapToEntity).orElse(null);
    }

    public Project mapFromEntity(ProjectEntity entity) {
        return Project.builder()
                .id(entity.getId())
                .name(entity.getName())
                .key(entity.getKey())
                .category(this.mapCategoryFromEntity(entity))
                .build();
    }

    public ProjectCategory mapCategoryFromEntity(ProjectEntity entity) {
        return entity.getCategory().map(this.projectCategoryService::mapFromEntity).orElse(null);
    }
}