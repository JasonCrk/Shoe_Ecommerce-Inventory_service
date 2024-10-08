package com.shoe_ecommerce.inventory.shared.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;

public final class JsonUtils {

    public static String jsonEncode(HashMap<String, Serializable> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static HashMap jsonDecode(String body) {
        try {
            return new ObjectMapper().readValue(body, HashMap.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
