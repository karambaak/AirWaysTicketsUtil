package org.example.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {
    private final SimpleDateFormat simpleDateFormat;

    public DateDeserializer(String pattern) {
        this.simpleDateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String dateString = jsonElement.getAsString();
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new JsonParseException("Error parsing date: " + dateString, e);
        }
    }
}
