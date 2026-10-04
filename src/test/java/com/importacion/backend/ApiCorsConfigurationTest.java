package com.importacion.backend;

import com.importacion.backend.config.ApiCorsConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.filter.CorsFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiCorsConfigurationTest {

    private static final String HOSTING_ORIGIN = "https://arancel593.web.app";

    @Test
    void allowsPublicGetRequestsFromTheFirebaseHostingOrigin() throws Exception {
        CorsFilter filter = new ApiCorsConfiguration()
                .apiCorsFilter(HOSTING_ORIGIN)
                .getFilter();
        MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/api/costos/resumen");
        request.addHeader("Origin", HOSTING_ORIGIN);
        request.addHeader("Access-Control-Request-Method", "GET");
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, (req, res) -> {
            throw new AssertionError("CORS preflight should be handled before the API filter chain");
        });

        assertEquals(200, response.getStatus());
        assertEquals(HOSTING_ORIGIN, response.getHeader("Access-Control-Allow-Origin"));
        assertTrue(response.getHeader("Access-Control-Allow-Methods").contains("GET"));
        assertFalse(response.getHeader("Access-Control-Allow-Methods").contains("POST"));
    }

    @Test
    void doesNotAllowAnUnconfiguredOrigin() throws Exception {
        CorsFilter filter = new ApiCorsConfiguration()
                .apiCorsFilter(HOSTING_ORIGIN)
                .getFilter();
        MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/api/costos/resumen");
        request.addHeader("Origin", "https://not-authorized.example");
        request.addHeader("Access-Control-Request-Method", "GET");
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, (req, res) -> {
            throw new AssertionError("CORS preflight should be handled before the API filter chain");
        });

        assertEquals(null, response.getHeader("Access-Control-Allow-Origin"));
    }
}
