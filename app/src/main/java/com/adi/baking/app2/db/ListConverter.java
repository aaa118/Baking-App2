package com.adi.baking.app2.db;

import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListConverter {

    static Gson gson = new Gson();

    @androidx.room.TypeConverter
    public static List<Ingredient> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Ingredient>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String someObjectListToString(List<Ingredient> someObjects) {
        return gson.toJson(someObjects);
    }
    @androidx.room.TypeConverter
    public static List<Step> stringToStepObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Ingredient>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String stepListToString(List<Step> someObjects) {
        return gson.toJson(someObjects);
    }
}
