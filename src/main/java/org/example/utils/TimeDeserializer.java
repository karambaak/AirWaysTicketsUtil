package org.example.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeDeserializer implements JsonDeserializer<LocalTime> {
    private final DateTimeFormatter formatter;

    public TimeDeserializer(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String json = jsonElement.getAsString();
        return LocalTime.parse(json, formatter);
    }
}
