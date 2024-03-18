package com.llp.sharedproject.sharedFunc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class SharedFunction {
    public static List<String> convertStringToList(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert the JSON array string to a List of Strings
        return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
    }

    public static String encodeMessage(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    public static String decodeMessage(String encodedMessage) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedMessage);
        return new String(decodedBytes);
    }
}
