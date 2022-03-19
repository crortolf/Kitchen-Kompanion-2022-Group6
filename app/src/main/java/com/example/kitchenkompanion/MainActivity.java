package com.example.kitchenkompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryItem ex = new GroceryItem("Fruit", "cup", 4);
        GroceryItem ex2 = new GroceryItem("Veg", "cup", 10, 1);

        TextView topBox = findViewById(R.id.topBox);
        TextView bottomBox = findViewById(R.id.bottomBox);
        Button recipe = findViewById(R.id.recipeListButton);

        topBox.setText(ex.toString());
        bottomBox.setText(ex2.toString());

        View.OnClickListener recipePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.recipe_list);
            }
        };

        recipe.setOnClickListener(recipePage);
    }
}