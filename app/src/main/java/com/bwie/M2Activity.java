package com.bwie;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bwie.com.my.adapter.MyAdapter;
import com.bwie.com.my.bean.CookList;
import com.bwie.com.my.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class M2Activity extends AppCompatActivity {
    private int id;
    private List<CookList.TngouBean> list;
    private List<CookList.TngouBean> myList=new ArrayList<>();
    private MyAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String json = (String) msg.obj;
                CookList cookList = new Gson().fromJson(json, CookList.class);
                list=cookList.getTngou();
                int tag=msg.arg1;
                switch (tag){
                    //添加数据
                    case 1:
                        myList.addAll(list);
                        adapter = new MyAdapter(M2Activity.this,myList);
                        lv.setAdapter(adapter);
                        break;
                    //刷新
                    case 2:
                        myList.clear();
                        myList.addAll(list);
                        adapter.notifyDataSetChanged();
                        break;
                    //刷新
                    case 3:
                        myList.addAll(list);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    };
    private XListView  lv;
    private int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);
        initUI();
        getData("http://www.tngou.net/api/cook/list?page="+index+"&rows=20&id="+id,1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(M2Activity.this,M3Activity.class);
                in.putExtra("id",list.get(i).getId());
                startActivity(in);
            }
        });
        lv.setPullLoadEnable(true);
        lv.setPullRefreshEnable(true);
        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                index++;
                getData("http://www.tngou.net/api/cook/list?page="+index+"&rows=20&id="+id,2);
                lv.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                index++;
                getData("http://www.tngou.net/api/cook/list?page="+index+"&rows=20&id="+id,3);
                lv.stopLoadMore();
            }
        });

    }

    private void initUI() {
        TextView title= (TextView) findViewById(R.id.title);
        lv = (XListView ) findViewById(R.id.lv);
        Intent in=getIntent();
        String title1=in.getStringExtra("title");
        id = in.getIntExtra("id",0);
        title.setText(title1);
    }


    public void getData(final String path, final int tag) {
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

                                handler.obtainMessage(1,tag,1,json).sendToTarget();
                            }
                        });
                    }
                }
        ).start();
    }
}
