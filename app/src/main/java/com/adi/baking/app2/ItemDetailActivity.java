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

import java.util.ArrayList;

import static com.adi.baking.app2.IngredientsList.WIDGET_LIST;
import static com.adi.baking.app2.ItemDetailFragment.ARG_ITEM_ID;
import static com.adi.baking.app2.ListViewFactory.RECIPE;
import static com.adi.baking.app2.ListViewFactory.RECIPE_NAME;

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
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ARG_ITEM_ID, getIntent().getParcelableExtra(ARG_ITEM_ID));
            arguments.putString(RECIPE_NAME, getIntent().getStringExtra(RECIPE_NAME));
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
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
