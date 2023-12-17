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
