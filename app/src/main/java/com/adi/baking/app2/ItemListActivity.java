package com.adi.baking.app2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.baking.app2.adapters.RecipeRecyclerViewAdapter;
import com.adi.baking.app2.databinding.ActivityItemListBinding;
import com.adi.baking.app2.db.RecipeDatabase;
import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.model.Step;
import com.adi.baking.app2.network.RetroFitInstance;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModel;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModelFactory;

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
//        fragmentListViewModel.getRecipesListLiveData().observe(this, new Observer<List<RecipeName>>() {
//            @Override
//            public void onChanged(List<RecipeName> recipeNames) {
////                fragmentListViewModel.getRecipesListLiveData().removeObserver(this);
////                Log.i(TAG, "onChanged: "+recipeNames.size());
//                Log.i(TAG, "onChanged: ");
//            }
//        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            AsyncTask.execute(this::networkRequest);
        }
        AsyncTask.execute(this::networkRequest);
//        networkRequest();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<RecipeName> body) {
        recyclerView.setAdapter(new RecipeRecyclerViewAdapter(this, body, mTwoPane));
    }


    private void networkRequest() {
        RetroFitInstance.getRetrofitService().getRecipeInfo().enqueue(new Callback<List<RecipeName>>() {
            @Override
            public void onResponse(Call<List<RecipeName>> call, Response<List<RecipeName>> response) {

                View recyclerView = findViewById(R.id.item_list);
                setupRecyclerView((RecyclerView) recyclerView, response.body());


                List<RecipeName> recipesList = response.body();
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
//
//                Log.i(TAG, "onResponse: " + response.body().get(0).getIngredients());
//                Log.i(TAG, "onResponse: 1" + response.body().get(1).getIngredients());
//                Log.i(TAG, "onResponse: 2" + response.body().get(2).getIngredients());
//                Log.i(TAG, "onResponse: 3" + response.body().get(3).getIngredients());
////                Log.i(TAG, "onResponse: "+response.body().get(1).getImage());
//                Log.i(TAG, "onResponse:2 "+response.body().get(2).getImage());
//                Log.i(TAG, "onResponse:3"+response.body().get(3).getImage());
//
//                RecyclerView recyclerView;
//                RecyclerView.LayoutManager layoutManager;
//                recyclerView = activityMainBinding.rlMoviesList;
//                recyclerView.setHasFixedSize(true);
//                // use a linear layout manager
//                layoutManager = new GridLayoutManager(getApplicationContext(), 1);
//                recyclerView.setLayoutManager(layoutManager);
//                RecipeListViewAdapter recipeListViewAdapter = new RecipeListViewAdapter(response.body());
//                recyclerView.setAdapter(recipeListViewAdapter);

            }

            @Override
            public void onFailure(Call<List<RecipeName>> call, Throwable t) {
                Log.i("AA_", "onFailure: " + t);
            }
        });
    }

//    public static class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final ItemListActivity mParentActivity;
//        private final List<DummyContent.DummyItem> mValues;
//        private final boolean mTwoPane;
//        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//                if (mTwoPane) {
//                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
//                    ItemDetailFragment fragment = new ItemDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit();
//                } else {
//                    Context context = view.getContext();
//                    Intent intent = new Intent(context, ItemDetailActivity.class);
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);
//
//                    context.startActivity(intent);
//                }
//            }
//        };
//
//        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
//                                      List<DummyContent.DummyItem> items,
//                                      boolean twoPane) {
//            mValues = items;
//            mParentActivity = parent;
//            mTwoPane = twoPane;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
//
//            holder.itemView.setTag(mValues.get(position));
//            holder.itemView.setOnClickListener(mOnClickListener);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder {
//            final TextView mIdView;
//            final TextView mContentView;
//
//            ViewHolder(View view) {
//                super(view);
//                mIdView = view.findViewById(R.id.id_text);
//                mContentView = view.findViewById(R.id.content);
//            }
//        }
//    }
}
