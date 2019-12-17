package com.example.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncHttpRequest extends AsyncTask<URL, Void, String> {
    private int TODAY_FORCAST_INDEX = 0;
    private Activity mainActivity;
    final static String  API_KEY = "599ac72b6f896296780a27cf610b3bed";

    Bitmap icon;

    public AsyncHttpRequest(Activity activity) {
        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }

    /**
     * 非同期処理で天気情報を取得する.
     * @param urls 接続先のURL
     * @return 取得した天気情報
     */
    @Override
    protected String doInBackground(URL... urls) {
        String data;

        // 情報取得
        try {
            // OpenWeatherMAPにアクセスするためのURL
            // 緯度経度を使って場所を指定
            String requestURL = urls[0] +"&APPID=" + API_KEY;
            URL url = new URL(requestURL);

            // アクセスして情報を取得
            InputStream is = url.openConnection().getInputStream();

            // JSON形式で結果が返るためパースのためにStringに変換する
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = reader.readLine())) {
                sb.append(line);
            }
            data = sb.toString();

            // ログ出してみる
            Log.d("debug",data);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // 情報を見やすく加工する
        try {
            JSONObject rootObj = new JSONObject(data);
            JSONArray listArray = rootObj.getJSONArray("list");

            JSONObject obj = listArray.getJSONObject(0);

            // 地点ID
            int id = obj.getInt("id");

            // 地点名
            String cityName = obj.getString("name");

            // 気温(Kから℃に変換)
            JSONObject mainObj = obj.getJSONObject("main");
            float currentTemp = (float) (mainObj.getDouble("temp") - 273.15f);

            float minTemp = (float) (mainObj.getDouble("temp_min") - 273.15f);

            float maxTemp = (float) (mainObj.getDouble("temp_max") - 273.15f);

            // 湿度
            if (mainObj.has("humidity")) {
                int humidity = mainObj.getInt("humidity");
            }

            // 取得時間
            long time = obj.getLong("dt");

            // 天気
            JSONArray weatherArray = obj.getJSONArray("weather");
            JSONObject weatherObj = weatherArray.getJSONObject(0);
            String weather = weatherObj.getString("main");
            String iconId = weatherObj.getString("icon");

            String result = cityName + "の天気は" + weather  + "\n最高気温は" + maxTemp + "℃\n最低気温は" + minTemp + "℃";

            try {
                String requestUrl = "https://openweathermap.org/img/w/" + iconId + ".png";
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();
                BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
                icon = BitmapFactory.decodeStream(in);
                in.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  result;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 非同期処理が終わった後の処理.
     * @param result 非同期処理の結果得られる文字列
     *               doInBackGround()の返り値が引数
     */
    @Override
    protected void onPostExecute(String result) {
        TextView tv = mainActivity.findViewById(R.id.textView);
        tv.setText(result);

        ImageView iv = mainActivity.findViewById(R.id.imageView);
        iv.setImageBitmap(icon);
    }
}
