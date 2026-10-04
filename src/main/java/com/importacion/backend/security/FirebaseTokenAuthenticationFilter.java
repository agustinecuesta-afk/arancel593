package com.importacion.backend.security;

import com.google.firebase.auth.FirebaseAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class FirebaseTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseTokenAuthenticationFilter.class);

    private final FirebaseIdTokenVerifier tokenVerifier;
    private final Set<String> allowedEmails;

    public FirebaseTokenAuthenticationFilter(FirebaseIdTokenVerifier tokenVerifier, String allowedEmails) {
        this.tokenVerifier = tokenVerifier;
        this.allowedEmails = Arrays.stream(allowedEmails.split(","))
                .map(String::trim)
                .filter(email -> !email.isEmpty())
                .map(email -> email.toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());
        if (this.allowedEmails.isEmpty()) {
            throw new IllegalStateException("app.security.allowed-emails debe incluir al menos un correo autorizado");
        }
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")
                || authorization.length() <= "Bearer ".length()) {
            writeUnauthorized(response);
            return;
        }

        VerifiedFirebaseUser token;
        try {
            token = tokenVerifier.verify(authorization.substring("Bearer ".length()));
        } catch (FirebaseAuthException ex) {
            LOGGER.warn("Firebase ID token verification failed", ex);
            writeUnauthorized(response);
            return;
        }

        String email = token.getEmail();
        if (!token.isEmailVerified() || email == null
                || !allowedEmails.contains(email.toLowerCase(Locale.ROOT))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write("{\"status\":403,\"error\":\"Forbidden\","
                    + "\"message\":\"Esta cuenta no está autorizada para el sistema.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void writeUnauthorized(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write("{\"status\":401,\"error\":\"Unauthorized\","
                + "\"message\":\"Inicia sesión con una cuenta autorizada.\"}");
    }
}
