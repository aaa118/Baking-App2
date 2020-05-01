package com.adi.baking.app2.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.baking.app2.views.ItemDetailActivity;
import com.adi.baking.app2.views.ItemDetailFragment;
import com.adi.baking.app2.views.ItemListActivity;
import com.adi.baking.app2.R;
import com.adi.baking.app2.views.widget.WidgetService;
import com.adi.baking.app2.model.RecipeName;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {
    public static final String ITEM_ID_PREFS = "itemIdPrefs";
    public static final String ITEM_ID = "itemId";
    private final ItemListActivity mParentActivity;
    private final List<RecipeName> recipeNameList;
    private final boolean mTwoPane;
    private Context context;
    private static final String TAG = "AA_Recycle";
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecipeName recipe = (RecipeName) view.getTag();
            SharedPreferences sharedPref = context.getSharedPreferences(ITEM_ID_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            Log.i(TAG, "onClick: "+recipe);
            editor.putInt(ITEM_ID, recipe.getId());
            editor.apply();

            if (mTwoPane) {
                Bundle arguments = new Bundle();
                Log.i("AA_", "onClick: "+mTwoPane);
//                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));
                arguments.putParcelable(ItemDetailFragment.ARG_ITEM_ID,  recipe);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, recipe.getId());
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, recipe);
                context.startActivity(intent);
            }
        }
    };

    public RecipeRecyclerViewAdapter(ItemListActivity parent, List<RecipeName> items, boolean twoPane, Context context) {
        recipeNameList = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mIdView.setText(recipeNameList.get(position).getName());
        holder.mContentView.setText(recipeNameList.get(position).getServings().toString());

        WidgetService.startActionUpdateWidgetsWithId(context, position);


        holder.itemView.setTag(recipeNameList.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return recipeNameList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
        }
    }
}
