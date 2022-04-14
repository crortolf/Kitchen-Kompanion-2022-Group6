package com.example.kitchenkompanion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    float scale;
    Button currentUser = null;
    String users[];
    Button[] userButtons;

    ActivityResultLauncher<Intent> myActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        int cu = data.getExtras().getInt("currentUser", -1);
                        Log.i("myInfo", "CU: " + cu);
                        if (cu > -1) {
                            for (Button b : userButtons) Log.i("myInfo", b.getText().toString());
                            if (userButtons[cu] != currentUser) userButtons[cu].performClick();
                        } else if (currentUser != null) currentUser.performClick();
                        newPage(data.getExtras().getInt("nextPage"));
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scale = getResources().getDisplayMetrics().density;
        MainActivity myContext = this;

        users = new String[]{"Chris", "Andrew", "Ethan", "Jason", "Katy", "Joel", "Brian"};

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

        userButtons = new Button[users.length];

        for (int i = 0; i < users.length; i++) {
            Button b = new Button(this);
            b.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            b.setHeight(toPix(90));
            b.setTypeface(ResourcesCompat.getFont(this, R.font.helvetica));
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.brown));
            b.setTextColor(ContextCompat.getColor(this, R.color.white));
            b.setText(users[i]);
            b.setOnClickListener(userButton);
            b.setAllCaps(false);
            ll.addView(b);
            userButtons[i] = b;
        }

        Button recipe = findViewById(R.id.recipesButton);
        Button kitchen = findViewById(R.id.kitchenOverview);
        Button shopping = findViewById(R.id.shoppingButton);
        Button main = findViewById(R.id.homeButton);

        //page codes: 0 is main, 1 is shopping, 2 is pantry, 3 is recipes

        View.OnClickListener recipePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPage(3);
            }
        };

        View.OnClickListener pantryPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPage(2);
            }
        };

        View.OnClickListener shoppingPage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPage(1);
            }
        };

        /* Not needed when already on homepage
        View.OnClickListener homePage = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };*/

        kitchen.setOnClickListener(pantryPage);
        //main.setOnClickListener(homePage);
        recipe.setOnClickListener(recipePage);
        shopping.setOnClickListener(shoppingPage);
    }

    private int toPix(int dps) {
        return (int) (dps * scale + 0.5f);
    }

    //page codes: 0 is main, 1 is shopping, 2 is pantry, 3 is recipes
    private boolean newPage(int pageCode) {
        Intent intent = null;
        switch(pageCode) {
            case 3: intent = new Intent(this, RecipeList.class);
                break;
            case 2: intent = new Intent(this, UserItemList.class);
                break;
            case 1: intent = new Intent(this, ShoppingList.class);
                break;
            case 0: return true;
            default: return false;
        }
        intent.putExtra("users", users);
        if (currentUser != null) intent.putExtra("currentUser", findCurrentUser(currentUser.getText().toString(), users));
        intent.putExtra("nextPage", -1);
        myActivityResultLauncher.launch(intent);

        return false;
    }

    private int findCurrentUser(String user, String[] users) {
        int i = 0;
        for (String currentUser : users) {
            if (currentUser.equals(user)) return i;
            i++;
        }
        return -1;
    }
}