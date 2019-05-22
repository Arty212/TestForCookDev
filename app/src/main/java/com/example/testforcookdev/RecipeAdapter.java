package com.example.testforcookdev;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testforcookdev.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    public interface RecipeAdapterListener {
        void onClick(Recipe recipe);
    }

    private List<Recipe> data;
    private RecipeAdapterListener recipeAdapterListener;

    public RecipeAdapter() {

    }

    public RecipeAdapter(List<Recipe> data) {
        this.data = data;
    }

    public void setData(List<Recipe> data) {
        this.data = data;
    }

    public void setRecipeAdapterListener(RecipeAdapterListener recipeAdapterListener) {
        this.recipeAdapterListener = recipeAdapterListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cook, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(data.get(i).name);
        Glide
                .with(viewHolder.view.getContext())
                .load(data.get(i).img)
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else
            return 0;

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView name;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.name);
            img = view.findViewById(R.id.img);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recipeAdapterListener != null)
                        recipeAdapterListener.onClick(data.get(getAdapterPosition()));
                }
            });
        }
    }
}
