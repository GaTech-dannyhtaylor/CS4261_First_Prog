package com.example.megdan.ui.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.megdan.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Button action to go to weather page
        Button viewWeatherButton = findViewById(R.id.weather_button);
        viewWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, manual_weather.class);
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
