package com.importacion.backend;

import com.importacion.backend.dto.SubpartidaAduana;
import com.importacion.backend.exceptions.InvalidArancelQueryException;
import com.importacion.backend.services.SenaeArancelService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.http.HttpMethod.POST;

class SenaeArancelServiceTest {

    private static final String SENAE_URL =
            "https://mesadeservicios.aduana.gob.ec/arancel/rest/data/subpartidas";

    @Test
    void searchesOfficialCatalogBySubpartida() {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
        server.expect(requestTo(SENAE_URL))
                .andExpect(method(POST))
                .andExpect(content().json(
                        "{\"codigo_subpartida\":\"6402\",\"descripcion_elemento\":null}",
                        false))
                .andRespond(withSuccess(
                        "{\"data\":[{\"identif\":\"640200000000000000\","
                                + "\"codigo_subpartida\":\"6402000000\","
                                + "\"codigo_complementario\":\"0000\","
                                + "\"codigo_suplementario\":\"0000\","
                                + "\"descripcion_elemento\":\"Calzado\","
                                + "\"fecha_inicio_vigencia\":\"01/09/2023\"}]}",
                        MediaType.APPLICATION_JSON));

        SenaeArancelService service = new SenaeArancelService(restTemplate, SENAE_URL);
        List<SubpartidaAduana> results = service.buscar("6402", null);

        assertEquals(1, results.size());
        assertEquals("6402000000", results.get(0).getCodigoSubpartida());
        assertEquals("Calzado", results.get(0).getDescripcion());
        server.verify();
    }

    @Test
    void rejectsAnEmptySearchWithoutCallingSenae() {
        SenaeArancelService service = new SenaeArancelService(new RestTemplate(), SENAE_URL);

        assertThrows(InvalidArancelQueryException.class, () -> service.buscar(" ", null));
    }
}
