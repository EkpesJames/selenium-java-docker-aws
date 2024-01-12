package org.example.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.test.vendorportal.model.VendorPortalTestDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    /**
     * Generic class is use to read the test data
     */

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type) {
        try (InputStream stream = ResourceLoader.getResources(path)) {
            return mapper.readValue(stream, type);
        } catch (Exception e) {
            log.error("Unable to read test data {}", path, e);
        }
        return null;
    }

}