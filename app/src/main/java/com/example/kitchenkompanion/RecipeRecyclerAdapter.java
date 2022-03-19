package com.example.kitchenkompanion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder>{
    private String[] recipeNames;

    // define what each of the views I'm using are going to look like
    // it's just a text view with the name of the recipe
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recipeName;

        public ViewHolder(View view) {
            super(view);
            recipeName = (TextView) view.findViewById(R.id.text);
        }

        public TextView getRecipeName() {
            return recipeName;
        }
    }

    public RecipeRecyclerAdapter(String[] dataSet) {
        recipeNames = dataSet;
    }

    @Override
    public RecipeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_list_item_recycler, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getRecipeName().setText(recipeNames[position]);
    }

    @Override
    public int getItemCount() {
        return recipeNames.length;
    }
}
