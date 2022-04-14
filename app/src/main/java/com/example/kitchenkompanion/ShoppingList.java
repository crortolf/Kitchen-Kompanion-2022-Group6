package com.example.kitchenkompanion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newItemPopup, quantityName, quantityAmount;
    private Button popupCancel, popupSave;

    private List<GroceryItem> shoppingList;
    private RecyclerView shoppingListRecyclerView;
    private ShoppingListAdapter shoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

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

        Button kitchen = findViewById(R.id.kitchenOverview);
        Button main = findViewById(R.id.homeButton);
        Button recipe = findViewById(R.id.recipesButton);

        //page codes: 0 is main, 1 is shopping, 2 is pantry, 3 is recipes
        View.OnClickListener recipePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 3);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };


        View.OnClickListener pantryPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 2);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        /*already on this page
        View.OnClickListener shoppingPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 1);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };*/

        View.OnClickListener homePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 0);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        kitchen.setOnClickListener(pantryPage);
        main.setOnClickListener(homePage);
        recipe.setOnClickListener(recipePage);
        //shopping.setOnClickListener(shoppingPage);
    }

    public void createNewItemDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View shoppingItemPopupView = getLayoutInflater().inflate(R.layout.shoppingitempopup, null);
        newItemPopup = (EditText) shoppingItemPopupView.findViewById(R.id.newItemPopup);
        quantityName = (EditText) shoppingItemPopupView.findViewById(R.id.quantityName);
        quantityAmount = (EditText) shoppingItemPopupView.findViewById(R.id.quantityAmount);

        popupSave = (Button) shoppingItemPopupView.findViewById(R.id.saveButton);
        popupCancel = (Button) shoppingItemPopupView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(shoppingItemPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        popupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save button
                String item = newItemPopup.getText().toString();
                shoppingList.add(new GroceryItem(item, quantityName.getText().toString(), 1f));
                shoppingListAdapter.setGroceryItem(shoppingList);
                Toast.makeText(getApplicationContext(), "Added " + item, Toast.LENGTH_LONG).show();
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