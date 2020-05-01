package com.adi.baking.app2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.model.Step;

import java.util.List;

@Dao
public interface RecipesDao {
    @Query("SELECT * FROM movieinfo")
    LiveData<List<RecipeName>> getAllRecipes();

    @Query("SELECT * FROM movieinfo")
    List<RecipeName> getAllRecipesNoLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(RecipeName recipeName);
}
