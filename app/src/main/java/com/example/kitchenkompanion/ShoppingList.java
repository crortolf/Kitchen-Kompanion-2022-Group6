package com.example.kitchenkompanion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShoppingList extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newItemPopup;
    private Button popupCancel, popupSave;

    private List<String> shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

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