package com.example.kitchenkompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {

    // list of items for this recipe
    private String[] itemList = {};
    private String name = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context mContext = this;

        setContentView(R.layout.activity_add_recipe);

        EditText itemName = findViewById(R.id.newitem);
        EditText recipeName = findViewById(R.id.newrecipe);
        EditText instructionName = findViewById(R.id.newinstruction);

        Button addItem = findViewById(R.id.additembtn);
        Button addInstruction = findViewById(R.id.addinstructionbtn);
        Button addRecipe = findViewById(R.id.addrecipebtn);


        //RECYCLER VIEW STUFF
        RecyclerView itemListRecycler = (RecyclerView) findViewById(R.id.recipe_item_recycler);

        ArrayList<String> arbitraryList = new ArrayList<String>();

        RecipeListItemAdapter mAdapter = new RecipeListItemAdapter(arbitraryList);
        itemListRecycler.setAdapter(mAdapter);
        itemListRecycler.setLayoutManager(new LinearLayoutManager(this));

        //END RECYCLER VIEW STUFF

        View.OnClickListener addBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!itemName.getText().toString().equals("")) {

                    String newItem = itemName.getText().toString();
                    arbitraryList.add(newItem);
                    mAdapter.notifyDataSetChanged();
                    itemName.setText("");
                    Toast.makeText(getApplicationContext(), "Item added to recipe", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Can't add empty item!", Toast.LENGTH_SHORT).show();
                }

            }
        };

        addItem.setOnClickListener(addBtnClick);


        View.OnClickListener addInstructionClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!instructionName.getText().toString().equals("")) {
                    String newItem = instructionName.getText().toString();
                    arbitraryList.add(newItem);
                    mAdapter.notifyDataSetChanged();
                    instructionName.setText("");
                    Toast.makeText(getApplicationContext(), "Instruction added to recipe", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Can't add empty instruction!", Toast.LENGTH_SHORT).show();
                }

            }
        };

        addInstruction.setOnClickListener(addInstructionClick);

        View.OnClickListener addRecipeClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent recipeInfo = new Intent(mContext, RecipeList.class);
                name = recipeName.getText().toString();
                recipeInfo.putExtra("RECIPE_NAME", name);
                System.out.println("HELLO?");
                //System.out.println(name);
                startActivity(recipeInfo);

                //String newItem = itemName.getText().toString();
                //itemName.setText("");
                //Toast.makeText(getApplicationContext(), "Item added to recipe", Toast.LENGTH_SHORT).show();

            }
        };

        addRecipe.setOnClickListener(addRecipeClick);

    }
}
