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
    private LiveData<List<RecipeName>> recipesListLiveData;

    public RecipeDetailViewModel(Context context, int id) {
        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(context);
        recipesListLiveData = recipeDatabase.recipesDao().getAllRecipes();
    }


    public LiveData<List<RecipeName>> getRecipesListLiveData() {
        return recipesListLiveData;
    }

}
