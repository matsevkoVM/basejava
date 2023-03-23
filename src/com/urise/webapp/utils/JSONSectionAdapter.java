package com.urise.webapp.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JSONSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = prim.getAsString();
        try {
            Class clazz = Class.forName(className);
            return context.deserialize(jsonObject.get(INSTANCE), clazz);
        }catch (ClassNotFoundException e){
            throw  new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(T section, Type sectionType, JsonSerializationContext context) {
        JsonObject returnValue = new JsonObject();
        returnValue.addProperty(CLASSNAME, section.getClass().getName());
        JsonElement element = context.serialize(section);
        returnValue.add(INSTANCE, element);
        return returnValue;
    }
}
