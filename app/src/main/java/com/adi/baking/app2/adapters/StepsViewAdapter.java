package com.adi.baking.app2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adi.baking.app2.R;
import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.Step;

import java.util.List;

public class StepsViewAdapter extends RecyclerView.Adapter<StepsViewAdapter.ViewHolder> {

    private List<Step> recipeNameList;

    public StepsViewAdapter(List<Step> recipeNameList) {
        this.recipeNameList = recipeNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_layout, parent, false);
        // set the view's size, margins, padding and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step step = recipeNameList.get(position);
//        holder.textViewTitle.setText(step.getShortDescription() +": ");
        holder.textViewDescription.setText(step.getDescription());
        if (step.getVideoURL()!=null && !step.getVideoURL().isEmpty()) {
            holder.button.setVisibility(View.VISIBLE);
        }
//        holder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return recipeNameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textViewTitle;
        TextView textViewDescription;
        Button button;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            textViewTitle = itemView.findViewById(R.id.tv_movie_name);
            textViewDescription = itemView.findViewById(R.id.tv_description);
            button = itemView.findViewById(R.id.bt_video);
//            ivPoster = itemView.findViewById(R.id.iv_image);

        }
    }
}
