package dev.codex.java.tool.application;

import dev.codex.java.tool.persistence.entity.PersistenceEntityPackage;
import dev.codex.java.tool.persistence.repository.PersistenceRepositoryPackage;
import dev.codex.java.tool.presentation.controller.PresentationControllerPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = { Main.class, PresentationControllerPackage.class })
@EntityScan(basePackageClasses = PersistenceEntityPackage.class)
@EnableJpaRepositories(basePackageClasses = PersistenceRepositoryPackage.class)
public class Main implements CommandLineRunner {
    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (String name : context.getBeanDefinitionNames()) {
            if (name.contains("board") || name.contains("Board")) System.out.println(name);
        }
    }
}