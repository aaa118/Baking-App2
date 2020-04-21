package com.adi.baking.app2.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adi.baking.app2.db.RecipeDatabase;
import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.model.Step;

import java.util.List;

public class RecipeDetailViewModel extends ViewModel {
    private LiveData<List<Ingredient>> ingredientsListLiveData;
    private LiveData<List<Step>> stepsListLiveData;
    private LiveData<List<RecipeName>> recipesListLiveData;

    public RecipeDetailViewModel(Context context, int id) {
        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(context);
        recipesListLiveData = recipeDatabase.recipesDao().getAllRecipes();
//        ingredientsListLiveData = recipeDatabase.recipesDao().getAllIngredientsListById(id);
//        stepsListLiveData = recipeDatabase.recipesDao().getAllStepsListById(id);
    }

//    public LiveData<List<Ingredient>> getIngredientsListLiveData() {
//        return ingredientsListLiveData;
//    }
//
//    public LiveData<List<Step>> getStepsListLiveData() {
//        return stepsListLiveData;
//    }

    public LiveData<List<RecipeName>> getRecipesListLiveData() {
        return recipesListLiveData;
    }

}
