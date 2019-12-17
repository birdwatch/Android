package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    // 緯度
    private double lat = 35.69;
    // 経度
    private double lon = 139.69;

    /**
     * 画面を表示する.
     *  note:デフォルトで実装されている
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                changeTextView(v);
            }

        });
    }

    /**
     * テキストラベルを変更する.
     * @param view view
     */
    public void changeTextView(View view) {
        // 非同期処理(AsyncHttpRequest#doInBackground())を呼び出す
        try {
            // cntは情報数
            // cntが多ければ多いほど、指定した座標から近い順に地点情報を取得
            // AsyncHttpRequestのインスタンスを作成し、execute()でAsyncTaskを開始する
            new AsyncHttpRequest(this).execute(new URL("https://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=1"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
