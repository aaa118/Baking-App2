package com.adi.baking.app2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.baking.app2.R;
import com.adi.baking.app2.model.Ingredient;

import java.util.List;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> {

    private List<Ingredient> recipeNameList;

    public ListRecyclerViewAdapter(List<Ingredient> recipeNameList) {
        this.recipeNameList = recipeNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, padding and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Ingredient recipeName = recipeNameList.get(position);
            holder.textViewTitle.setText(recipeName.getIngredient());
    }

    @Override
    public int getItemCount() {
        return recipeNameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView ivPoster;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_movie_name);
//            ivPoster = itemView.findViewById(R.id.iv_image);

        }
    }
}
