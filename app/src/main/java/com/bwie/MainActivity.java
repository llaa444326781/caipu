package com.bwie;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.bwie.com.my.bean.Type;
import com.bwie.com.my.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String path = "http://www.tngou.net/api/cook/classify";
    private List<Type.TngouBean> types = new ArrayList<>();
    private List<String> ts;
    private Handler handler = new Handler() {



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String json = (String) msg.obj;
                Type type = new Gson().fromJson(json, Type.class);
                types = type.getTngou();
                ts = new ArrayList<>();
                for (Type.TngouBean tb:types){
                    ts.add(tb.getName());
                }
                mGridView.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, ts));
            }
        }
    };
    private GridView mGridView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        getData();
    }

    private void initUI() {
        mGridView = (GridView) findViewById(R.id.gridView);
        editText = (EditText) findViewById(R.id.editText);
        Button search= (Button) findViewById(R.id.search);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(MainActivity.this,M2Activity.class);
                in.putExtra("title",ts.get(i));
                in.putExtra("id",types.get(i).getId());
                startActivity(in);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                if(name==null){
                    Toast.makeText(MainActivity.this,"输入为空!请重新输入:",Toast.LENGTH_SHORT).show();
                }else{

                    Intent intent=new Intent(MainActivity.this,M4Activity.class);
                    intent.putExtra("name",name );

                    startActivity(intent);
                }
            }
        });
    }

    public void getData() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils.get(path, new Callback() {
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
