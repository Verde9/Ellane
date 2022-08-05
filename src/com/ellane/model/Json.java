package com.ellane.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Json {

    private  static ObjectMapper mapper = new ObjectMapper();


    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        //--
        return defaultObjectMapper;
    }

    public static JsonNode parse(File src) throws IOException {
        return mapper.readTree(src);

    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return mapper.treeToValue(node,clazz);
    }




}