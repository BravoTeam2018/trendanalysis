package com.cit.trendanalysis.utility;

import com.cit.trendanalysis.model.Alert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtility {

    private static final Logger log = LoggerFactory.getLogger(JsonUtility.class);

    private JsonUtility() {
        throw new IllegalStateException("Utility class : call static methods only");
    }


    public static Alert fromJsonStringToAlert(String json) throws IOException {

        Alert result = null;

        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(json,Alert.class);

        return result;
    }

}
