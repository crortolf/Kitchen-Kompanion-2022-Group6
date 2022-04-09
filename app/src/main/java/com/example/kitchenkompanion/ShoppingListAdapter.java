package com.example.kitchenkompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private List<GroceryItem> groceryList;
    private ShoppingList activity;

    public ShoppingListAdapter(ShoppingList activity) {
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        GroceryItem item = groceryList.get(position);
        holder.shoppingListItem.setText(item.getName());
        holder.shoppingListItem.setChecked(item.getStatus());
        holder.shoppingListQuantity.setText(item.getAmount() + " " + item.getUnits());
    }

    public int getItemCount() {
        return groceryList.size();
    }

    public void setGroceryItem(List<GroceryItem> groceryList) {
        this.groceryList = groceryList;
        notifyDataSetChanged();
    }

    public Context getContext() { return activity; }

    public void deleteItem(int position) {
        GroceryItem item = groceryList.get(position);
        // database removal
        groceryList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox shoppingListItem;
        TextView shoppingListQuantity;

        ViewHolder(View view) {
            super(view);
            shoppingListItem = view.findViewById(R.id.shoppingListCheckBox);
            shoppingListQuantity = view.findViewById(R.id.shoppingListQuantity);
        }
    }
}
