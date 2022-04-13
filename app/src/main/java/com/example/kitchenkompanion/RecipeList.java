package com.example.kitchenkompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;

public class RecipeList  extends AppCompatActivity {

    ArrayList<String> recipeNames = new ArrayList<String>();

    SharedPreferences listPref;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context mContext = getApplicationContext();
        listPref = mContext.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = listPref.edit();

        recipeNames.add("Mystery Meat");
        recipeNames.add("Grandma's Chili");

        setContentView(R.layout.recycle_list);

        // recycler view is used to create scrollable list of touchable stuff
        RecyclerView mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipeRecyclerAdapter r = new RecipeRecyclerAdapter(recipeNames);
        mRecyclerView.setAdapter(new RecipeRecyclerAdapter(recipeNames));

        FloatingActionButton addButton = findViewById(R.id.addrecipebutton);


        if (getIntent().getStringExtra("RECIPE_NAME") != null) {

            Collection<String> prefNames = (Collection<String>) listPref.getAll().values();
            String name = getIntent().getStringExtra("RECIPE_NAME");
            //System.out.println(name);
            for (String i : prefNames) {
                recipeNames.add(i);
            }
            recipeNames.add(name);
            editor.putString(name, name);
            editor.apply();
            //mRecyclerView.setAdapter(new RecipeRecyclerAdapter(recipeNames));
            mRecyclerView.getAdapter().notifyDataSetChanged();

        }

        View.OnClickListener addBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startAddRecipe = new Intent("android.intent.action.ADDRECIPE");

                startActivity(startAddRecipe);
            }
        };

        addButton.setOnClickListener(addBtnClick);

        Button kitchen = findViewById(R.id.kitchenOverview);
        Button main = findViewById(R.id.homeButton);
        Button shopping = findViewById(R.id.shoppingButton);

        //page codes: 0 is main, 1 is shopping, 2 is pantry, 3 is recipes

        /*already here
        View.OnClickListener recipePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 3);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };*/


        View.OnClickListener pantryPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 2);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

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

        kitchen.setOnClickListener(pantryPage);
        main.setOnClickListener(homePage);
        //recipe.setOnClickListener(recipePage);
        shopping.setOnClickListener(shoppingPage);
    }



    /*@Override
    protected void onResume() {
        super.onResume();

        String name = getIntent().getStringExtra("RECIPE_NAME");
        recipeNames.add(name);
        //mRecyclerView.setAdapter(new RecipeRecyclerAdapter(recipeNames));
    }
    */

}
