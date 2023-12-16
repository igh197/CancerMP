 package com.example.cancermp;
 import android.os.AsyncTask;
 import android.os.Bundle;
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

     private static final String OPEN_WEATHER_MAP_API_KEY = "obsF28ubfHiPWOEvt3zqYV5z85juLszlW5EfSEiIqsuRfwusEqsDiwuqdKnq2TadJCAhTg2DlsycxhbYHRtZYg%3D%3D";
     private static final String API_URL = "obsF28ubfHiPWOEvt3zqYV5z85juLszlW5EfSEiIqsuRfwusEqsDiwuqdKnq2TadJCAhTg2DlsycxhbYHRtZYg%3D%3D" + OPEN_WEATHER_MAP_API_KEY;

     private TextView weatherTextView;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         weatherTextView = findViewById(R.id.weather_text);

         // API 호출 AsyncTask 실행
         WeatherTask task = new WeatherTask();
         task.execute(API_URL);
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
                 weatherTextView.setText("날씨: " + description);

             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     }
 }
