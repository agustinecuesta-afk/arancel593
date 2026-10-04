package com.importacion.backend;

import com.importacion.backend.config.PublicReadOnlyApiFilter;
import org.junit.jupiter.api.Test;
import org.springframework.core.Ordered;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PublicReadOnlyApiFilterTest {

    @Test
    void allowsPublicGetRequests() throws Exception {
        OncePerRequestFilter filter = new PublicReadOnlyApiFilter()
                .publicReadOnlyFilter()
                .getFilter();
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/costos/resumen");
        MockHttpServletResponse response = new MockHttpServletResponse();
        boolean[] reachedApi = {false};

        filter.doFilter(request, response, (req, res) -> reachedApi[0] = true);

        assertTrue(reachedApi[0]);
        assertEquals(200, response.getStatus());
    }

    @Test
    void rejectsPublicPostRequestsWithoutCallingTheApi() throws Exception {
        OncePerRequestFilter filter = new PublicReadOnlyApiFilter()
                .publicReadOnlyFilter()
                .getFilter();
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/aereo");
        MockHttpServletResponse response = new MockHttpServletResponse();
        boolean[] reachedApi = {false};

        filter.doFilter(request, response, (req, res) -> reachedApi[0] = true);

        assertEquals(405, response.getStatus());
        assertEquals("GET, OPTIONS", response.getHeader("Allow"));
        assertTrue(response.getContentAsString().contains("solo permite consultas"));
        assertEquals(false, reachedApi[0]);
    }

    @Test
    void installsTheReadOnlyFilterForApiRoutesOnly() {
        assertTrue(new PublicReadOnlyApiFilter().publicReadOnlyFilter()
                .getUrlPatterns().contains("/api/*"));
        assertEquals(Ordered.LOWEST_PRECEDENCE,
                new PublicReadOnlyApiFilter().publicReadOnlyFilter().getOrder());
    }
}
