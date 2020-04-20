package com.adi.baking.app2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.adi.baking.app2.model.RecipeName;

@Database(entities = RecipeName.class, version = 1, exportSchema = false)
@TypeConverters(ListConverter.class)
public abstract class RecipeDatabase extends RoomDatabase {

    private static final String DB_NAME = "recipes_db";
    private static RecipeDatabase instance;

    public static synchronized RecipeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, RecipeDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract RecipesDao recipesDao();

}
