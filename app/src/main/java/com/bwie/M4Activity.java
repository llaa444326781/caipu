package com.bwie;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.com.my.bean.FoodNaem;
import com.bwie.com.my.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class M4Activity extends AppCompatActivity {

    private String name;
    private Handler handler = new Handler() {



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String json = (String) msg.obj;
                FoodNaem name = new Gson().fromJson(json, FoodNaem.class);
                foodname.setText(name.getTngou().get(0).getName());
                fooddescription.setText("食材:\n"+name.getTngou().get(0).getDescription());
                foodmessage.setText("步骤:\n"+name.getTngou().get(0).getMessage());
                Glide.with(M4Activity.this)
                        .load("http://tnfs.tngou.net/image"+name.getTngou().get(0).getImg())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(foodimg);
            }
        }
    };
    private TextView foodname;
    private TextView fooddescription;
    private TextView foodmessage;
    private ImageView foodimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m4);
        initUI();
        getData();
    }

    private void initUI() {
        foodname = (TextView) findViewById(R.id.foodname);
        fooddescription = (TextView) findViewById(R.id.fooddescription);
        foodmessage = (TextView) findViewById(R.id.foodmessage);
        foodimg = (ImageView) findViewById(R.id.foodimg);
        Intent in=getIntent();
        name = in.getStringExtra("name");
        Log.d("msg","name"+name);
    }

    public void getData() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils.get("http://www.tngou.net/api/cook/name?name="+name, new Callback() {
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
