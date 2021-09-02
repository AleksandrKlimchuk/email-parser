package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

public class JsonConverterFromMap {

    public static String convert(Map<String, String> mapToConvert) throws JsonProcessingException {

        mapToConvert = Objects.requireNonNull(mapToConvert);
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(mapToConvert);

        return jsonString;
    }
}
