이 앱은 계획과 다르게 날씨를 예보하는 앱을 만들었습니다. android studio를 이용해 java로 제작되었으며 volley를 이용하여 openweather API를 이용하여 만들었습니다.<br>
<img width="128" alt="image" src="https://github.com/igh197/CancerMP/assets/57895194/7cbb5818-4a30-47d9-bd88-f0874ac738e5"><br>
그리고 굉장히 단순하게 제작되어 버튼만 누르면 날씨를 업데이트 할 수 있게 만들었습니다.<br>
또한 예외처리를 다음과 같이 하였습니다.
```
....
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
....
```
또한 RelativeLayout을 이용하여 매우 유동적으로 레이아웃을 표현하도록 만들었습니다.
```
...
 <Button
            android:id="@+id/update_weather_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="50dp"
            android:text="날씨 정보 업데이트" />

        <TextView
            android:id="@+id/weather_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@id/update_weather_button"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="24dp"
            android:textSize="24sp"
            android:textColor="@android:color/black"
            android:text="날씨 정보 표시될 곳" />
...

```
