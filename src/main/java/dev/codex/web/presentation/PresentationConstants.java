package dev.codex.web.presentation;

public class PresentationConstants {
    public static final String AUTHENTICATION_REQUEST_PATH = "/api/auth";
    public static final String FORUM_REQUEST_PATH = "/api/forums";
    public static final String POST_REQUEST_PATH = "/api/posts";

    public static final String JWT_AUTHENTICATION_FILTER_BEAN_NAME = "dev.codex.web.presentation.filter.JWTAuthenticationFilter";

    public static final String AUTHENTICATION_CONTROLLER_BEAN_NAME = "dev.codex.web.presentation.controller.AuthenticationController";
    public static final String FORUM_CONTROLLER_BEAN_NAME = "dev.codex.web.presentation.controller.ForumController";
    public static final String POST_CONTROLLER_BEAN_NAME = "dev.codex.web.presentation.controller.PostController";
}