package com.bwie;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.bwie.com.my.bean.Food;
import com.bwie.com.my.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class M3Activity extends AppCompatActivity {
    private int id;
    //private String path="http://www.tngou.net/api/cook/show?id="+id;
    private Handler handler = new Handler() {



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String json = (String) msg.obj;
                Log.d("msg",""+json);
                Food food = new Gson().fromJson(json, Food.class);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient());
                webView.loadUrl(food.getUrl());


            }
        }
    };
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m3);
        initUI();
        getData();
    }

    private void initUI() {
        webView = (WebView) findViewById(R.id.webView);
        Intent in=getIntent();
        id = in.getIntExtra("id",0);
        Log.d("msg","id"+id);

    }

    public void getData() {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils.get("http://www.tngou.net/api/cook/show?id="+id, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String json = response.body().string();
                                handler.obtainMessage(1, json).sendToTarget();
                            }
                        });
                    }
                }
        ).start();
    }
}
