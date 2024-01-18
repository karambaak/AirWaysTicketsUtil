package org.example.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.entities.Ticket;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FileUtil {

    private FileUtil(){
    }

    public static List<Ticket> readFile() {
        File file = new File("data/tickets.json");
        try (FileReader reader = new FileReader(file)) {
            Type itemsMapType = new TypeToken<Map<String, List<Ticket>>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(Date.class, new DateDeserializer("dd.MM.yy"))
                    .registerTypeAdapter(LocalTime.class, new TimeDeserializer("HH:mm"))
                    .registerTypeAdapter(LocalTime.class, new TimeDeserializer("H:mm"))
                    .create();
            Map<String, List<Ticket>> tickets = gson.fromJson(reader, itemsMapType);
            return tickets.get("tickets");
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }
}
