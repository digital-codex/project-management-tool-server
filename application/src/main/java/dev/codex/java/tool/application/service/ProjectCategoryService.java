package dev.codex.java.tool.application.service;

import dev.codex.java.tool.application.model.ProjectCategory;
import dev.codex.java.tool.persistence.entity.ProjectCategoryDescriptionEntity;
import dev.codex.java.tool.persistence.entity.ProjectCategoryEntity;
import dev.codex.java.tool.persistence.repository.PersistenceRepositoryPackage;
import dev.codex.java.tool.persistence.repository.ProjectCategoryDescriptionRepository;
import dev.codex.java.tool.persistence.repository.ProjectCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service(value = "projectCategoryService")
public class ProjectCategoryService {
    private final ProjectCategoryRepository projectCategoryRepository;
    private final ProjectCategoryDescriptionRepository projectCategoryDescriptionRepository;

    @Autowired
    public ProjectCategoryService(ProjectCategoryRepository projectCategoryRepository, ProjectCategoryDescriptionRepository projectCategoryDescriptionRepository) {
        this.projectCategoryRepository = projectCategoryRepository;
        this.projectCategoryDescriptionRepository = projectCategoryDescriptionRepository;
    }

    @Transactional(readOnly = true)
    //TODO: add custom exception and handling
    public ProjectCategory findByName(String name) {
        return this.projectCategoryRepository.findByName(Objects.requireNonNull(name, "`name` argument must not be null")).map(this::mapFromEntity).orElseThrow(IllegalArgumentException::new);
    }

    public ProjectCategoryEntity mapToEntity(ProjectCategory object) {
        return ProjectCategoryEntity.builder()
                .name(object.getName())
                .description(this.mapDescriptionToEntity(object))
                .build();
    }

    public ProjectCategoryDescriptionEntity mapDescriptionToEntity(ProjectCategory object) {
        return ProjectCategoryDescriptionEntity.builder()
                .description(object.getDescription().orElse(null))
                .build();
    }

    public ProjectCategory mapFromEntity(ProjectCategoryEntity entity) {
        return ProjectCategory.builder()
                .name(entity.getName())
                .description(this.mapDescriptionFromEntity(entity))
                .build();
    }

    public String mapDescriptionFromEntity(ProjectCategoryEntity entity) {
        return entity.getDescription().flatMap(ProjectCategoryDescriptionEntity::getDescription).orElse(null);
    }
}