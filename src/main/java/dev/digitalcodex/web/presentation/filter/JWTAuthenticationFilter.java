package dev.digitalcodex.web.presentation.filter;

import dev.digitalcodex.web.application.provider.JWTProvider;
import dev.digitalcodex.web.application.util.Strings;
import dev.digitalcodex.web.presentation.PresentationConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component(PresentationConstants.JWT_AUTHENTICATION_FILTER_BEAN_NAME)
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final RequestMatcher permitted = new AntPathRequestMatcher(PresentationConstants.AUTHENTICATION_REQUEST_PATH + "/**");

    private final JWTProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JWTAuthenticationFilter(JWTProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = this.extractJWT(request);

        String username;
        if (Strings.hasText(jwt) && (username = this.jwtProvider.validateJWT(jwt)) != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        return this.permitted.matches(request);
    }

    private String extractJWT(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");

        if (Strings.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }

        return bearer;
    }
}
