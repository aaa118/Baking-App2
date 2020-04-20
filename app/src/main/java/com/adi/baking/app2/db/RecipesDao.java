package com.adi.baking.app2.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.adi.baking.app2.model.RecipeName;

@Dao
public interface RecipesDao {

//    @Query("SELECT * FROM MovieInfo ORDER BY popularity DESC LIMIT 20")
//    LiveData<List<MovieInfo>> getPopularMoviesLimitTo20();
//
//    @Query("SELECT * FROM MovieInfo ORDER BY voteAverage DESC LIMIT 20")
//    LiveData<List<MovieInfo>> getTopRatedFromDB();
//
//    @Query("SELECT * FROM MovieInfo where isFavorite == 1")
//    LiveData<List<MovieInfo>> getAllFavMovieList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(RecipeName movies);
}
