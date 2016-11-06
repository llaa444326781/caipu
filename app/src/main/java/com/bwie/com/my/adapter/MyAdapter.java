package com.bwie.com.my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.R;
import com.bwie.com.my.bean.CookList;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/3.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<CookList.TngouBean> list;

    public MyAdapter(Context context, List<CookList.TngouBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CookList.TngouBean tb=list.get(i);
        ViewHolder vh;
        if(view==null){
            vh=new ViewHolder();
            view=View.inflate(context, R.layout.cooklist_item,null);
            vh.img= (ImageView) view.findViewById(R.id.img);
            vh.cook_name= (TextView) view.findViewById(R.id.cook_name);
            vh.cook_keywords= (TextView) view.findViewById(R.id.cook_keywords);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        Glide.with(context)
                .load("http://tnfs.tngou.net/image"+tb.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(vh.img);
                vh.cook_name.setText("菜名:"+tb.getName());
                vh.cook_keywords.setText("食材:"+tb.getKeywords());
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView cook_name;
        TextView cook_keywords;

    }
}
