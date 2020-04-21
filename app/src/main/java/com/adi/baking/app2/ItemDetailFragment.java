package com.adi.baking.app2;

import android.app.Activity;
import android.os.Bundle;

import com.adi.baking.app2.adapters.ListRecyclerViewAdapter;
import com.adi.baking.app2.adapters.StepsViewAdapter;
import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.model.Step;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModel;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModelFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.adi.baking.app2.dummy.DummyContent;

import java.util.List;

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

    /**
     * The dummy content this fragment is presenting.
     */
//    private DummyContent.DummyItem mItem;
    private RecipeName mItem;
    //    private List<RecipeName>mItem;
    RecipeDetailViewModel fragmentListViewModel;
    private static final String TAG = "AA_";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getParcelable(ARG_ITEM_ID);



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

        // Show the dummy content as text in a TextView.
        Log.i(TAG, "onCreateView: m" + mItem);
        RecipeDetailViewModelFactory factory =  RecipeDetailViewModelFactory.getInstance(getContext(), mItem.getId());
        fragmentListViewModel = ViewModelProviders.of(this, factory).get(RecipeDetailViewModel.class);

        fragmentListViewModel.getRecipesListLiveData().observe(getViewLifecycleOwner(), recipeNames -> {
//                fragmentListViewModel.getRecipesListLiveData().removeObserver(this);
//                Log.i(TAG, "onChanged: "+recipeNames.size());
            RecipeName recipeName = recipeNames.get(mItem.getId()-1);
            Log.i(TAG, "onChanged: "+recipeName);

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
            StepsViewAdapter listAdapter2 = new StepsViewAdapter(recipeName.getSteps());
            recyclerView2.setAdapter(listAdapter2);


        });
        Log.i(TAG, "onCreateView:LiveData" + fragmentListViewModel.getRecipesListLiveData().getValue());


//        Log.i(TAG, "onCreateView: "+fragmentListViewModel.getRecipesListLiveData().getValue());
        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(fragmentListViewModel.getRecipesListLiveData().getValue().get(mItem.getId()).getName());
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.getName());
        }

//        if (mItem != null) {
//            for (Ingredient ingredient : mItem.getIngredients()) {
//                Log.i(TAG, "onCreateView: "+ingredient);
//            }
//            for (Step ingredient : mItem.getSteps()) {
//                Log.i(TAG, "onCreateView: "+ingredient);
//            }
//        }

        return rootView;
    }
}
