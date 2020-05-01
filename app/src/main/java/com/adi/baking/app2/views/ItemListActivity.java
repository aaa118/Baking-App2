package com.adi.baking.app2.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.baking.app2.R;
import com.adi.baking.app2.adapters.RecipeRecyclerViewAdapter;
import com.adi.baking.app2.databinding.ActivityItemListBinding;
import com.adi.baking.app2.db.RecipeDatabase;
import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.network.RetroFitInstance;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModel;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModelFactory;
import com.adi.baking.app2.views.ItemDetailActivity;
import com.adi.baking.app2.views.widget.WidgetService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ActivityItemListBinding activityItemListBinding;
    private static final String TAG = "AA_ItemListActivity";
    RecipeDatabase recipeDatabase;
    RecipeDetailViewModel fragmentListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemListBinding = ActivityItemListBinding.inflate(getLayoutInflater());
        setContentView(activityItemListBinding.getRoot());
        setSupportActionBar(activityItemListBinding.toolbar);
        activityItemListBinding.toolbar.setTitle(getTitle());
        recipeDatabase = RecipeDatabase.getInstance(getApplicationContext());

        RecipeDetailViewModelFactory factory =  RecipeDetailViewModelFactory.getInstance(getApplicationContext(), 0);
        fragmentListViewModel = ViewModelProviders.of(this, factory).get(RecipeDetailViewModel.class);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            if (savedInstanceState == null) {
                AsyncTask.execute(this::networkRequest);
            }
        }
        if (savedInstanceState == null) {
            AsyncTask.execute(this::networkRequest);
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<RecipeName> body) {
        recyclerView.setAdapter(new RecipeRecyclerViewAdapter(this, body, mTwoPane, getApplicationContext()));
    }

    private void networkRequest() {
        RetroFitInstance.getRetrofitService().getRecipeInfo().enqueue(new Callback<List<RecipeName>>() {
            @Override
            public void onResponse(Call<List<RecipeName>> call, Response<List<RecipeName>> response) {
                View recyclerView = findViewById(R.id.item_list);
                setupRecyclerView((RecyclerView) recyclerView, response.body());
                List<RecipeName> recipesList = response.body();
                WidgetService.startActionUpdateWidgets(getApplicationContext(), (ArrayList<Ingredient>) recipesList.get(0).getIngredients());
                if (recipesList != null) {
                    for (RecipeName recipe : recipesList) {
                        RecipeName recipeName = new RecipeName(recipe.getId(), recipe.getName(),
                                recipe.getIngredients(), recipe.getSteps(), recipe.getServings());

                        AsyncTask.execute(() -> {
                            recipeDatabase.recipesDao().insertRecipes(recipeName);
                            Log.i(TAG, "onResponse: "+ recipeDatabase.recipesDao().getAllRecipes().getValue());
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<List<RecipeName>> call, Throwable t) {
                Log.i("AA_", "onFailure: " + t);
            }
        });
    }
}
