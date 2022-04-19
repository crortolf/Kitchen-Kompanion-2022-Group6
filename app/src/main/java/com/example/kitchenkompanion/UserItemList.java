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

        Typeface mt = getResources().getFont(R.font.helvetica_neue);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.user_name_list, android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

//        itemCount = (TextView) findViewById()

        listView = findViewById(R.id.listview);
        items = new ArrayList<GroceryItem>();

        GroceryItem milk = new GroceryItem("Milk", "Cartons", 0.5f);
        GroceryItem apples = new GroceryItem("Apples", "Apples", 4);
        GroceryItem flour = new GroceryItem("Flour", "lbs", 3.5f);
        GroceryItem ramen = new GroceryItem("Ramen", "Cups", 16);

        milk.setExpirationDate("5/18/2022");
        apples.setExpirationDate("4/20/2022");

        items.add(milk);
        items.add(apples);
        items.add(flour);
        items.add(ramen);

        UserItemAdapter adapter = new UserItemAdapter(getApplicationContext(), R.layout.adapter_view_user_items, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                //text.setTypeface(mt);
                return view;
            }
        };
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
