package com.narosco.testrecoveryjson.infrastructure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.List;
import java.util.Map;

public class JacksonUltils {

    private JacksonUltils(){
        throw new UnsupportedOperationException("Classe Utilitaria nao pode ser instanciada");
    }

    public static final ObjectMapper OBJECT_MAPPER = createObjectMapper();
    public static final String DATA = "data";

    public static <T> T convertSimpleObject(String json, Class<T> clazz){
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Falha ao converter de JSON para objeto", e);
        }
    }

    public static <T, S> Map<T, List<S>> convertFromWrapperList(String json, Class<T> keyClass, Class<S> valueClass){
        var typeFactory = OBJECT_MAPPER.getTypeFactory();
        var mapLikeType = typeFactory.constructMapLikeType(Map.class, typeFactory.constructType(keyClass),
                typeFactory.constructParametricType(List.class, valueClass));
        try {
            return OBJECT_MAPPER.readValue(json, mapLikeType);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Falha ao converter de JSON para objeto", e);
        }
    }

    public static <T, S> Map<T, S> convertFromWrapper(String json, Class<T> keyClass, Class<S> valueClass){
        var typeFactory = OBJECT_MAPPER.getTypeFactory();
        var mapLikeType = typeFactory.constructMapLikeType(Map.class, typeFactory.constructType(keyClass),
                typeFactory.constructType(valueClass));
        try {
            return OBJECT_MAPPER.readValue(json, mapLikeType);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Falha ao converter de JSON para objeto", e);
        }
    }

    public static <S> Map<String, S> getMockMapSingleObject(String json, Class<S> valueClass) {
        return convertFromWrapper(json, String.class, valueClass);
    }

    public static <S> Map<String, List<S>> getMockMapListObject(String json, Class<S> valueClass) {
        return convertFromWrapperList(json, String.class, valueClass);
    }

    public static <S> S getSingleObject(String json, Class<S> valueClass) {
        Map<String, S> mockSingleObject = getMockMapSingleObject(json, valueClass);
        return mockSingleObject.get(DATA);
    }

    public static <S> List<S> getListObject(String json, Class<S> valueClass) {
        Map<String, List<S>> mockListObject = getMockMapListObject(json, valueClass);
        return mockListObject.get(DATA);
    }

    public static String fromObject(Object object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e){
            throw new IllegalArgumentException("Nao foi possivel serializar o objeto", e);
        }
    }



    private static ObjectMapper createObjectMapper(){
        return new ObjectMapper()
                .registerModule(new SimpleModule())
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    };
}
