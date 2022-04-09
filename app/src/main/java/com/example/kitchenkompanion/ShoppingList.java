package com.example.kitchenkompanion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newItemPopup;
    private Button popupCancel, popupSave;

    private List<GroceryItem> shoppingList;
    private RecyclerView shoppingListRecyclerView;
    private ShoppingListAdapter shoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        getSupportActionBar().hide();

        shoppingList = new ArrayList<>();

        shoppingListRecyclerView = findViewById(R.id.shoppingListRecyclerView);
        shoppingListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingListAdapter = new ShoppingListAdapter(this);
        shoppingListRecyclerView.setAdapter(shoppingListAdapter);

        shoppingList.add(new GroceryItem("Milk", "Cartons", 0.5f));
        shoppingList.add(new GroceryItem("Apples", "Apples", 4));
        shoppingList.add(new GroceryItem("Flour", "lbs", 3.5f));
        shoppingList.add(new GroceryItem("Ramen", "Cups", 16));

        shoppingListAdapter.setGroceryItem(shoppingList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(shoppingListAdapter));
        itemTouchHelper.attachToRecyclerView(shoppingListRecyclerView);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewItemDialog();
            }
        });
    }

    public void createNewItemDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View shoppingItemPopupView = getLayoutInflater().inflate(R.layout.shoppingitempopup, null);
        newItemPopup = (EditText) shoppingItemPopupView.findViewById(R.id.newItemPopup);

        popupSave = (Button) shoppingItemPopupView.findViewById(R.id.saveButton);
        popupCancel = (Button) shoppingItemPopupView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(shoppingItemPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        popupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save button
                //shoppingList.add(newItemPopup.getText().toString());
                dialog.dismiss();
            }
        });

        popupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}