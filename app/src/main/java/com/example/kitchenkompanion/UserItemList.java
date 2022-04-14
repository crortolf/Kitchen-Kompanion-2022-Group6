package com.example.kitchenkompanion;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserItemList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView listView;
    ArrayList<GroceryItem> items;
    Spinner spinner;
//    TextView itemCount;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_item_list);

        String[] users = getIntent().getStringArrayExtra("users");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(getIntent().getIntExtra("currentUser", 0));

//        itemCount = (TextView) findViewById()

        listView = findViewById(R.id.listview);
        items = new ArrayList<GroceryItem>();

        items.add(new GroceryItem("Milk", "Cartons", 0.5f));
        items.add(new GroceryItem("Apples", "Apples", 4));
        items.add(new GroceryItem("Flour", "lbs", 3.5f));
        items.add(new GroceryItem("Ramen", "Cups", 16));


        UserItemAdapter adapter = new UserItemAdapter(getApplicationContext(), R.layout.adapter_view_user_items, items);
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
                getIntent().putExtra("currentUser", spinner.getSelectedItemPosition());
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
                getIntent().putExtra("currentUser", spinner.getSelectedItemPosition());
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        View.OnClickListener homePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIntent().putExtra("nextPage", 0);
                getIntent().putExtra("currentUser", spinner.getSelectedItemPosition());
                setResult(RESULT_OK, getIntent());
                finish();
            }
        };

        //kitchen.setOnClickListener(pantryPage);
        main.setOnClickListener(homePage);
        recipe.setOnClickListener(recipePage);
        shopping.setOnClickListener(shoppingPage);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
