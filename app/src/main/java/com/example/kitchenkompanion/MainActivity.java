package com.example.kitchenkompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    float scale;
    Button currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scale = getResources().getDisplayMetrics().density;
        MainActivity myContext = this;

        LinearLayout ll = findViewById(R.id.userLayout);

        View.OnClickListener userButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser == view) {
                    currentUser.setBackgroundColor(ContextCompat.getColor(myContext, R.color.brown));
                    currentUser.setTextColor(ContextCompat.getColor(myContext, R.color.white));
                    currentUser = null;
                } else {
                    if (currentUser != null) {
                        currentUser.setBackgroundColor(ContextCompat.getColor(myContext, R.color.brown));
                        currentUser.setTextColor(ContextCompat.getColor(myContext, R.color.white));
                    }
                    currentUser = (Button) view;
                    currentUser.setTextColor(ContextCompat.getColor(myContext, R.color.black));
                    currentUser.setBackgroundColor(ContextCompat.getColor(myContext, R.color.green));
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            User user = new User("User " + (i + 1));
            Button b = new Button(this);
            b.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            b.setHeight(toPix(90));
            b.setTypeface(ResourcesCompat.getFont(this, R.font.helvetica));
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.brown));
            b.setTextColor(ContextCompat.getColor(this, R.color.white));
            b.setText(user.name);
            b.setOnClickListener(userButton);
            b.setAllCaps(false);
            ll.addView(b);


        }

        Button recipe = findViewById(R.id.recipesButton);
        Button kitchen = findViewById(R.id.kitchenOverview);
        Button shopping = findViewById(R.id.shoppingButton);

        View.OnClickListener recipePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.recycle_list);
            }
        };

        View.OnClickListener shoppingPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_shopping_list);
            }
        };

        recipe.setOnClickListener(recipePage);
        shopping.setOnClickListener(shoppingPage);
    }

    private int toPix(int dps) {
        return (int) (dps * scale + 0.5f);
    }
}