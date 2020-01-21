package com.example.megdan.ui.other;

import com.example.megdan.HttpRequest;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.megdan.R;

// Tutorial imports
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class manual_weather extends AppCompatActivity {

    String CITY = "Anchorage";
    //Tutorial API
    //String API = "8118ed6ee68db2debfaaa5a44c832918";
    String API = "29e7997d4723758edde7d77349492211";

    TextView actual_temp, condition, title;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_weather);

        actual_temp = findViewById(R.id.actual_temp_textView);
        condition = findViewById(R.id.condition_textView);
        title = findViewById(R.id.manual_weather_title_textView);
        title.setText("Welcome to " + CITY);

        new weatherTask().execute();


    }

    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            String response = HttpRequest.executeGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=imperial&appid=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {


            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);


                String temp = main.getString("temp") + "°F";

                String weatherDescription = weather.getString("description");



                /* Populating extracted data into our views */
                actual_temp.setText(temp);
                condition.setText(weatherDescription);


                // Updating the ListView then
                list = (ListView)findViewById(R.id.listView);
                final ArrayList<String> arrayList = new ArrayList<>();

                String[] arrayOfStrings = (actual_temp.getText().toString()).split("°");
                Double temperature = Double.parseDouble(arrayOfStrings[0]);
                String clothing = actual_temp.getText().toString();
                String rainOrNot = condition.getText().toString();

                // Type of pants
                if (temperature > 75) {
                    arrayList.add("shorts");
                } else {
                    arrayList.add("long pants");
                }

                // Type of jacket
                if (temperature < 65 && temperature >= 40) {
                    arrayList.add("light jacket");
                } else if (temperature < 40 && temperature >= 20) {
                    arrayList.add("heavy jacket");
                } else if (temperature < 20) {
                    arrayList.add("Everything that an eskimo would wear");
                }

                // Umbrella or not
                if (rainOrNot.contains("rain") || rainOrNot.contains("thunder") || rainOrNot.contains("drizzle")) {
                    arrayList.add("rain boots");
                    arrayList.add("umbrella");
                }
                

                // Set the listview adapter
                ArrayAdapter arrayAdapter = new ArrayAdapter(manual_weather.this, android.R.layout.simple_list_item_1, arrayList);
                list.setAdapter(arrayAdapter);

                // Make a toast when an item is clicked
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(manual_weather.this, "You don't have a/an " + arrayList.get(position), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (JSONException e) {

            }

        }
    }
}

