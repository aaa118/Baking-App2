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

//    @Query("SELECT * FROM MovieInfo ORDER BY popularity DESC LIMIT 20")
//    LiveData<List<MovieInfo>> getPopularMoviesLimitTo20();
//
//    @Query("SELECT * FROM MovieInfo ORDER BY voteAverage DESC LIMIT 20")
//    LiveData<List<MovieInfo>> getTopRatedFromDB();
//
//    @Query("SELECT ingredients FROM RecipeName where id =:id")
//    LiveData<List<Ingredient>> getAllIngredientsListById(int id);
//
//    @Query("SELECT steps FROM RecipeName where id =:id")
//    LiveData<List<Step>> getAllStepsListById(int id);

//    @Query("SELECT ingredients FROM RecipeName where id =:id")
//    List<Ingredient> getAllIngredientsListById(int id);
////    List<Ingredient> getAllIngredientsListById();
//
//    @Query("SELECT steps FROM RecipeName where id =:id")
//    List<Step> getAllStepsListById(int id);

    @Query("SELECT * FROM movieinfo")
    LiveData<List<RecipeName>> getAllRecipes();

    @Query("SELECT * FROM movieinfo")
    List<RecipeName> getAllRecipesNoLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(RecipeName movies);
}
