package com.example.kitchenkompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserItemList extends AppCompatActivity {
    ListView listView;
    // needs to eventually be a list of GroceryItem elements
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_item_list);

        listView = findViewById(R.id.listview);
        items = new ArrayList<>();
        items.add("Apple");
        items.add("Banana");
        items.add("Orange");
        items.add("Watermelon");

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        Button recipe = findViewById(R.id.recipesButton);
        Button kitchen = findViewById(R.id.kitchenOverview);
        Button shopping = findViewById(R.id.shoppingButton);
        Button main = findViewById(R.id.homeButton);


        //page codes: 0 is main, 1 is shopping, 2 is pantry, 3 is recipes
        View.OnClickListener recipePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 3);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        /* already on this page
        View.OnClickListener pantryPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserItemList.class);
                startActivity(intent);
            }
        }; */

        View.OnClickListener shoppingPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 1);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        View.OnClickListener homePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 0);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        //kitchen.setOnClickListener(pantryPage);
        main.setOnClickListener(homePage);
        recipe.setOnClickListener(recipePage);
        shopping.setOnClickListener(shoppingPage);
    }
}