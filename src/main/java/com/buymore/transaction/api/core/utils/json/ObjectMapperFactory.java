package com.buymore.transaction.api.core.utils.json;

import com.buymore.transaction.api.core.exceptions.BuymoreTransactionApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class ObjectMapperFactory {

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public <T> T stringToObject(final Class<T> type, final String json) {
        try {
            return jsonMapper.readerFor(type).readValue(json);
        } catch (IOException e) {
            throw new BuymoreTransactionApiException(e.getMessage());
        }
    }

    public String objectToString(final Object type) {
        try {
            return jsonMapper.writeValueAsString(type);
        } catch (IOException e) {
            throw new BuymoreTransactionApiException(e.getMessage());
        }
    }

}
