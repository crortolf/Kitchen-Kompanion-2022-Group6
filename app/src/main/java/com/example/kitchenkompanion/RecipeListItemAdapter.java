package com.example.kitchenkompanion;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeListItemAdapter extends RecyclerView.Adapter<RecipeListItemAdapter.ViewHolder> {

    // viewholder used to cache list items for speed
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.recipe_list_name);

        }
    }

    private ArrayList<String> item_list_members;
    public RecipeListItemAdapter(ArrayList<String> dataset) {
        item_list_members=dataset;
    }

    @Override
    public RecipeListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recipe_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListItemAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        String item = item_list_members.get(position);

        // Set item views based on your views and data model
        // String newText = holder.nameTextView.getText().toString();
        TextView textView = holder.nameTextView;
        textView.setText((position+1) + ". " + item);

    }

    @Override
    public int getItemCount() {
        return item_list_members.size();
    }

}
