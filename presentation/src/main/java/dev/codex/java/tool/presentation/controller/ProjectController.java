package dev.codex.java.tool.presentation.controller;

import dev.codex.java.tool.application.model.Project;
import dev.codex.java.tool.application.model.ProjectCategory;
import dev.codex.java.tool.application.service.ProjectCategoryService;
import dev.codex.java.tool.application.service.ProjectService;
import dev.codex.java.tool.presentation.request.ProjectRequest;
import dev.codex.java.tool.presentation.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "projectController")
@RequestMapping(params = "/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectCategoryService projectCategoryService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectCategoryService projectCategoryService) {
        this.projectService = projectService;
        this.projectCategoryService = projectCategoryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponse> save(@RequestBody ProjectRequest request) {
        ProjectResponse response = new ProjectResponse();
        try {
            Project project = this.projectService.save(this.mapToObject(request));
            response.setName(project.getName());
            response.setKey(project.getKey());
            response.setCategory(project.getCategory().map(ProjectCategory::getName).orElse(null));
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(project.getId())).body(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            response.addErrors(exception.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectResponse>> findAll() {
        List<ProjectResponse> responses = new ArrayList<>();
        try {
            List<Project> projects = this.projectService.findAll();
            for (Project project : projects) {
                ProjectResponse response = new ProjectResponse();
                response.setName(project.getName());
                response.setKey(project.getKey());
                response.setCategory(project.getCategory().map(ProjectCategory::getName).orElse(null));
                responses.add(response);
            }
            return ResponseEntity.ok(responses);
        } catch (Exception exception) {
            ProjectResponse response = new ProjectResponse();
            response.addErrors(exception.getMessage());
            responses.add(response);
            return ResponseEntity.badRequest().body(responses);
        }
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponse> findById(@PathVariable(name = "id") Long id) {
        ProjectResponse response = new ProjectResponse();
        try {
            Project project = this.projectService.findById(id);
            response.setName(project.getName());
            response.setKey(project.getKey());
            response.setCategory(project.getCategory().map(ProjectCategory::getName).orElse(null));
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            response.addErrors(exception.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponse> update(@PathVariable(name = "id") Long id, @RequestBody ProjectRequest request) {
        ProjectResponse response = new ProjectResponse();
        try {
            Project project = this.projectService.update(id, this.mapToObject(request));
            response.setName(project.getName());
            response.setKey(project.getKey());
            response.setCategory(project.getCategory().map(ProjectCategory::getName).orElse(null));
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            response.addErrors(exception.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        try {
            this.projectService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    public Project mapToObject(ProjectRequest request) {
        return Project.builder()
                .name(request.getName())
                .key(request.getKey())
                .category(this.mapCategoryToObject(request))
                .build();
    }

    public ProjectCategory mapCategoryToObject(ProjectRequest request) {
        return request.getCategory().map(this.projectCategoryService::findByName).orElse(null);
    }
}