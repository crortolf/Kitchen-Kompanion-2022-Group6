package com.example.kitchenkompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        topBox.setText(ex.toString());
        bottomBox.setText(ex2.toString());
    }
}