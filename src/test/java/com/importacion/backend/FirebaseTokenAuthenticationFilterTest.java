package com.importacion.backend;

import com.importacion.backend.security.FirebaseTokenAuthenticationFilter;
import com.importacion.backend.security.VerifiedFirebaseUser;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FirebaseTokenAuthenticationFilterTest {

    private static final String AUTHORIZED_EMAIL = "agustincuesta1975@gmail.com";
    private static final String TEST_TOKEN_PREFIX = "test";

    @Test
    void rejectsRequestsWithoutAnIdToken() throws Exception {
        FirebaseTokenAuthenticationFilter filter = new FirebaseTokenAuthenticationFilter(
                token -> {
                    throw new AssertionError("Token verifier should not be called without a token");
                }, AUTHORIZED_EMAIL);
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(
                new MockHttpServletRequest("GET", "/api/costos/resumen"),
                response,
                new MockFilterChain());

        assertEquals(401, response.getStatus());
    }

    @Test
    void acceptsVerifiedTokenFromAllowlistedEmail() throws Exception {
        FirebaseTokenAuthenticationFilter filter = new FirebaseTokenAuthenticationFilter(
                token -> {
                    assertEquals(TEST_TOKEN_PREFIX + "-token", token);
                    return new VerifiedFirebaseUser(AUTHORIZED_EMAIL.toUpperCase(), true);
                }, "  " + AUTHORIZED_EMAIL + "  ");
        MockHttpServletRequest request = apiRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, new MockFilterChain());

        assertEquals(200, response.getStatus());
    }

    @Test
    void rejectsVerifiedTokenFromEmailOutsideAllowlist() throws Exception {
        FirebaseTokenAuthenticationFilter filter = new FirebaseTokenAuthenticationFilter(
                token -> new VerifiedFirebaseUser("another@example.com", true), AUTHORIZED_EMAIL);
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(apiRequest(), response, new MockFilterChain());

        assertEquals(403, response.getStatus());
    }

    @Test
    void rejectsAllowlistedTokenWhenEmailIsNotVerified() throws Exception {
        FirebaseTokenAuthenticationFilter filter = new FirebaseTokenAuthenticationFilter(
                token -> new VerifiedFirebaseUser(AUTHORIZED_EMAIL, false), AUTHORIZED_EMAIL);
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(apiRequest(), response, new MockFilterChain());

        assertEquals(403, response.getStatus());
    }

    @Test
    void refusesToStartWithoutAnAllowlistedEmail() {
        assertThrows(IllegalStateException.class, () ->
                new FirebaseTokenAuthenticationFilter(token -> null, " , "));
    }

    private MockHttpServletRequest apiRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/costos/resumen");
        request.addHeader("Authorization", "Bearer " + TEST_TOKEN_PREFIX + "-token");
        return request;
    }
}
