package com.example.megdan.ui.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.megdan.R;
import com.example.megdan.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Landing extends AppCompatActivity {

    // Code from Manifest to put back after testing my little piece
    /*
    <activity
            android:name=".ui.login.LoginActivity"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
     */

    private FirebaseAuth mAuth;
    Double lat;
    Double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //gets the lat and lng values from sign in page
        Intent myIntent = getIntent();
        lat = myIntent.getDoubleExtra("lat", 0);
        lng = myIntent.getDoubleExtra("lng", 0);

        // Button action to go to weather page
        Button viewWeatherButton = findViewById(R.id.weather_button);
        viewWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, manual_weather.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                startActivity(intent);
            }
        });

        Button viewLogOutButton = findViewById(R.id.logout);
        viewLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent = new Intent(Landing.this, LoginActivity.class);
                startActivity(intent);
            }
        });


//        // Button action to go to Recipes page
//        Button viewRecipesButton = findViewById(R.id.recipes_button);
//        viewRecipesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Landing.this, recipes.class);
//                startActivity(intent);
//            }
//        });
    }
}
