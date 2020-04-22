package com.adi.baking.app2.viewmodels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RecipeDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static Context context;
    private static RecipeDetailViewModelFactory instance;
    private static int id;

    private RecipeDetailViewModelFactory(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    public static RecipeDetailViewModelFactory getInstance(Context context, int id) {
        if (instance == null) {
            return instance = new RecipeDetailViewModelFactory(context, id);
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RecipeDetailViewModel(context, id);
    }


}
