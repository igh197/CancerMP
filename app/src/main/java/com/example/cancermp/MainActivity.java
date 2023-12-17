 package com.example.cancermp;
 import android.os.AsyncTask;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.TextView;

 import androidx.appcompat.app.AppCompatActivity;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;

 public class MainActivity extends AppCompatActivity {

     private static final String OPEN_WEATHER_MAP_API_KEY = "11d448bfa09e351cf871884473ad8f75";
     private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=" + OPEN_WEATHER_MAP_API_KEY;


     private TextView weatherTextView;
     private Button updateWeatherButton;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         weatherTextView = findViewById(R.id.weather_text);
         updateWeatherButton = findViewById(R.id.update_weather_button);

         // 버튼 클릭 리스너 등록
         updateWeatherButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 // API 호출 AsyncTask 실행

                 WeatherTask task = new WeatherTask();
                 task.execute(API_URL);
             }
         });
     }
     private class WeatherTask extends AsyncTask<String, Void, String> {

         @Override
         protected String doInBackground(String... urls) {
             String result = "";
             HttpURLConnection urlConnection = null;

             try {
                 URL url = new URL(urls[0]);
                 urlConnection = (HttpURLConnection) url.openConnection();

                 InputStream inputStream = urlConnection.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 StringBuilder builder = new StringBuilder();

                 String line;
                 while ((line = reader.readLine()) != null) {
                     builder.append(line);
                 }

                 result = builder.toString();

             } catch (IOException e) {
                 e.printStackTrace();
             } finally {
                 if (urlConnection != null) {
                     urlConnection.disconnect();
                 }
             }

             return result;
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);

             try {
                 JSONObject jsonObject = new JSONObject(s);
                 JSONArray weatherArray = jsonObject.getJSONArray("weather");
                 JSONObject weatherObject = weatherArray.getJSONObject(0);

                 String description = weatherObject.getString("description");
                 weatherTextView.setText(description);

             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     }
 }