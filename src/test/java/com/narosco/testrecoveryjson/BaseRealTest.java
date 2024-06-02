package com.narosco.testrecoveryjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narosco.testrecoveryjson.base.PayloadTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public abstract class BaseRealTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRealTest.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected <T> T readJsonResource(PayloadTest resource, Class<T> clazz) {
        try (InputStream is = getClass().getResourceAsStream(resource.getPath())) {
            if (is == null) {
                throw new IOException("Resource not found: " + resource.getPath());
            }
            LOGGER.info("Reading JSON resource: {}", resource.getPath());
            return objectMapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo JSON: " + resource.getPath(), e);
        }
    }

}
