package com.adi.baking.app2.views;

import android.app.Activity;
import android.os.Bundle;

import com.adi.baking.app2.R;
import com.adi.baking.app2.views.widget.WidgetService;
import com.adi.baking.app2.adapters.ListRecyclerViewAdapter;
import com.adi.baking.app2.adapters.StepsViewAdapter;
import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModel;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModelFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.adi.baking.app2.views.widget.ListViewFactory.RECIPE_NAME;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private RecipeName mItem;
    private String recipeName;
    RecipeDetailViewModel fragmentListViewModel;
    private static final String TAG = "AA_";
    ArrayList<Ingredient> ingredientList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeName = getArguments().getString(RECIPE_NAME);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = getArguments().getParcelable(ARG_ITEM_ID);
            if (mItem == null) {
                setIdFromWidget();
            }

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        if (recipeName != null) {
            setIdFromWidget();
        }

        RecipeDetailViewModelFactory factory = RecipeDetailViewModelFactory.getInstance(getContext(), mItem.getId());
        fragmentListViewModel = ViewModelProviders.of(this, factory).get(RecipeDetailViewModel.class);

        fragmentListViewModel.getRecipesListLiveData().observe(getViewLifecycleOwner(), recipeNames -> {
            RecipeName recipeName = recipeNames.get(mItem.getId() - 1);
            ingredientList = (ArrayList<Ingredient>) recipeNames.get(mItem.getId() - 1).getIngredients();
            WidgetService.startActionUpdateWidgets(getContext(), ingredientList);
            RecyclerView recyclerView;
            RecyclerView.LayoutManager layoutManager;
            recyclerView = rootView.findViewById(R.id.rl_item_detail);
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            ListRecyclerViewAdapter listAdapter = new ListRecyclerViewAdapter(recipeName.getIngredients());
            recyclerView.setAdapter(listAdapter);

            RecyclerView recyclerView2;
            RecyclerView.LayoutManager layoutManager2;
            recyclerView2 = rootView.findViewById(R.id.rl_step_item_detail);
            recyclerView2.setHasFixedSize(true);
            // use a linear layout manager
            layoutManager2 = new LinearLayoutManager(getContext());
            recyclerView2.setLayoutManager(layoutManager2);
            StepsViewAdapter listAdapter2 = new StepsViewAdapter(recipeName.getSteps(), getContext());
            recyclerView2.setAdapter(listAdapter2);
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("lastClickedIngredient", ingredientList);
    }

    private void setIdFromWidget() {
        // Show the dummy content as text in a TextView.
        switch (recipeName) {
            case "Nutella Pie":
                mItem = new RecipeName(1);
                break;
            case "Brownies":
                mItem = new RecipeName(2);
                break;
            case "Yellow Cake":
                mItem = new RecipeName(3);
                break;
            default:
                mItem = new RecipeName(4);
                break;
        }
        Log.i(TAG, "onCreateView: m" + mItem);
    }
}
