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