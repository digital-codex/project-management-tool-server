package dev.codex.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            System.out.printf("Key %s, Value %s\n", entry.getKey(), entry.getValue());
        }
    }
}