package dev.codex.web.application.data;

public interface Mail {
    String to();

    String subject();

    String text();
}