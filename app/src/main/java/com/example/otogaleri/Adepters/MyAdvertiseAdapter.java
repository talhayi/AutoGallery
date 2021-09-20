package com.example.otogaleri.Adepters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.otogaleri.Models.MyAdvertisePojo;
import com.example.otogaleri.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyAdvertiseAdapter extends BaseAdapter {

    List<MyAdvertisePojo> list;
    Context context;
    Activity activity;
    String uye_id, ilan_id;


    public MyAdvertiseAdapter(List<MyAdvertisePojo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.myadvertiselayout,parent,false);
        ImageView resim;
        TextView baslik,fiyat;

        resim = convertView.findViewById(R.id.ilanlarimIlanResim);
        baslik = convertView.findViewById(R.id.ilanlarimIlanBaslik);
        fiyat = convertView.findViewById(R.id.ilanlarimIlanFiyat);

        ilan_id = list.get(position).getIlanid();
        uye_id = list.get(position).getUyeid();

        baslik.setText(list.get(position).getBaslik());
        fiyat.setText(list.get(position).getFiyat());

        Picasso.with(context).load("http://yazilimgunlukleri.com/autogallery/"+ list.get(position).getResim()).resize(100,100).into(resim);

        return convertView;
    }
}
