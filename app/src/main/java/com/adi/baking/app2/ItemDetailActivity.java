package com.adi.baking.app2;

import android.content.Intent;
import android.os.Bundle;

import com.adi.baking.app2.model.RecipeName;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModel;
import com.adi.baking.app2.viewmodels.RecipeDetailViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProviders;

import android.view.MenuItem;

import static com.adi.baking.app2.ItemDetailFragment.ARG_ITEM_ID;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        RecipeName recipeName = getIntent().getParcelableExtra(ItemDetailFragment.ARG_ITEM_ID);
        Log.i("AA_", "onCreate: "+getIntent().getParcelableExtra(ItemDetailFragment.ARG_ITEM_ID));
//        RecipeDetailViewModelFactory factory = null;
//        if (recipeName != null) {
//            factory = RecipeDetailViewModelFactory.getInstance(getApplicationContext(), recipeName.getId());
//            RecipeDetailViewModel fragmentListViewModel = ViewModelProviders.of(this, factory).get(RecipeDetailViewModel.class);
//        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
//            Log.i("AA_", "onCreate: "+getIntent().getIntExtra(ARG_ITEM_ID,0));

//            getIntent().getParcelableExtra(ARG_ITEM_ID);
//            Log.i("AA_", "onCreate: "+getIntent().getParcelableExtra(ItemDetailFragment.ARG_ITEM_ID));
            Bundle arguments = new Bundle();
            arguments.putParcelable(ARG_ITEM_ID,
                    getIntent().getParcelableExtra(ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
