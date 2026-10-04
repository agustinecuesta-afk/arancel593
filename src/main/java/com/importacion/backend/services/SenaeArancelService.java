package com.importacion.backend.services;

import com.importacion.backend.dto.SubpartidaAduana;
import com.importacion.backend.dto.SubpartidasAduanaResponse;
import com.importacion.backend.exceptions.ExternalServiceException;
import com.importacion.backend.exceptions.InvalidArancelQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class SenaeArancelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenaeArancelService.class);
    private static final Pattern SUBPARTIDA_PATTERN = Pattern.compile("\\d{4,10}");
    private static final String SOURCE_UNAVAILABLE =
            "No fue posible consultar el Arancel Nacional del SENAE en este momento";

    private final RestTemplate restTemplate;
    private final String subpartidasUrl;

    public SenaeArancelService(
            RestTemplate restTemplate,
            @Value("${arancel.senae.subpartidas-url:https://mesadeservicios.aduana.gob.ec/arancel/rest/data/subpartidas}")
                    String subpartidasUrl) {
        this.restTemplate = restTemplate;
        this.subpartidasUrl = subpartidasUrl;
    }

    public List<SubpartidaAduana> buscar(String codigo, String descripcion) {
        String codigoNormalizado = codigo == null ? "" : codigo.trim();
        String descripcionNormalizada = descripcion == null ? "" : descripcion.trim();

        if (codigoNormalizado.isEmpty() && descripcionNormalizada.isEmpty()) {
            throw new InvalidArancelQueryException("Indica un código de subpartida o una descripción para buscar");
        }

        if (!codigoNormalizado.isEmpty() && !SUBPARTIDA_PATTERN.matcher(codigoNormalizado).matches()) {
            throw new InvalidArancelQueryException("El código debe tener entre 4 y 10 dígitos");
        }

        if (!descripcionNormalizada.isEmpty()
                && (descripcionNormalizada.length() < 3 || descripcionNormalizada.length() > 100)) {
            throw new InvalidArancelQueryException("La descripción debe tener entre 3 y 100 caracteres");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        SubpartidaQuery query = new SubpartidaQuery(
                codigoNormalizado.isEmpty() ? null : codigoNormalizado,
                descripcionNormalizada.isEmpty() ? null : descripcionNormalizada);

        try {
            SubpartidasAduanaResponse response = restTemplate.postForObject(
                    subpartidasUrl,
                    new HttpEntity<>(query, headers),
                    SubpartidasAduanaResponse.class);

            if (response == null || response.getData() == null) {
                throw new ExternalServiceException(SOURCE_UNAVAILABLE, null);
            }

            return response.getData();
        } catch (RestClientException ex) {
            LOGGER.error("Falló la consulta al Arancel Nacional del SENAE", ex);
            throw new ExternalServiceException(SOURCE_UNAVAILABLE, ex);
        }
    }

    private static class SubpartidaQuery {

        private final String codigo_subpartida;
        private final String descripcion_elemento;

        private SubpartidaQuery(String codigoSubpartida, String descripcionElemento) {
            this.codigo_subpartida = codigoSubpartida;
            this.descripcion_elemento = descripcionElemento;
        }

        public String getCodigo_subpartida() {
            return codigo_subpartida;
        }

        public String getDescripcion_elemento() {
            return descripcion_elemento;
        }
    }
}
