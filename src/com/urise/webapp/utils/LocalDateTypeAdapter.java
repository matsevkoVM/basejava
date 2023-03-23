package com.urise.webapp.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        out.beginObject();
        out.name("year").value(value.getYear());
        out.name("month").value(value.getMonthValue());
        out.name("day").value(value.getDayOfMonth());
        out.endObject();
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        int year = 0, month = 0, day = 0;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "year" -> year = in.nextInt();
                case "month" -> month = in.nextInt();
                case "day" -> day = in.nextInt();
                default -> in.skipValue();
            }
        }
        in.endObject();

        return LocalDate.of(year, month, day);
    }
}