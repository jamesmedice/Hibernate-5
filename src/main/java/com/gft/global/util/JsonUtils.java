package com.gft.global.util;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    public static String serializeAsJsonString(Object object) throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	mapper.enable(SerializationFeature.INDENT_OUTPUT);
	mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	StringWriter sw = new StringWriter();
	mapper.writeValue(sw, object);
	return sw.toString();
    }

    public static String serializeAsJsonString(Object object, boolean indent) throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	if (indent == true) {
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
	    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}

	StringWriter sw = new StringWriter();
	mapper.writeValue(sw, object);
	return sw.toString();
    }

    public static <T> T jsonStringToObject(String content, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
	T obj = null;
	ObjectMapper mapper = new ObjectMapper();
	obj = mapper.readValue(content, clazz);
	return obj;
    }

}
