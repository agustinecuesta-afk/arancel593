package com.importacion.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.importacion.backend.security.FirebaseIdTokenVerifier;
import com.importacion.backend.security.FirebaseTokenAuthenticationFilter;
import com.importacion.backend.security.VerifiedFirebaseUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@ConditionalOnProperty(name = "app.security.firebase-enabled", havingValue = "true")
public class FirebaseAuthConfiguration {

    @Bean
    public FirebaseAuth firebaseAuth(
            @Value("${app.firebase.project-id}") String projectId,
            @Value("${app.firebase.service-account-json:}") String serviceAccountJson) throws IOException {
        if (serviceAccountJson.trim().isEmpty()) {
            throw new IllegalStateException("FIREBASE_SERVICE_ACCOUNT_JSON debe configurarse en producción");
        }
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(
                        serviceAccountJson.getBytes(StandardCharsets.UTF_8))))
                .setProjectId(projectId)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(options, "importacion-backend");
        return FirebaseAuth.getInstance(app);
    }

    @Bean
    public FilterRegistrationBean<FirebaseTokenAuthenticationFilter> firebaseTokenFilter(
            FirebaseAuth firebaseAuth,
            @Value("${app.security.allowed-emails:}") String allowedEmails) {
        FilterRegistrationBean<FirebaseTokenAuthenticationFilter> registration = new FilterRegistrationBean<>();
        FirebaseIdTokenVerifier verifier = idToken -> {
            com.google.firebase.auth.FirebaseToken token = firebaseAuth.verifyIdToken(idToken);
            return new VerifiedFirebaseUser(token.getEmail(), token.isEmailVerified());
        };
        registration.setFilter(new FirebaseTokenAuthenticationFilter(verifier, allowedEmails));
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        return registration;
    }
}
