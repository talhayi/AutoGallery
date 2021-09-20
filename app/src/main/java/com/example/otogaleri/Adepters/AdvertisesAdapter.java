package com.example.otogaleri.Adepters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.otogaleri.Models.AdvertisesPojo;
import com.example.otogaleri.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdvertisesAdapter extends BaseAdapter {

    List<AdvertisesPojo> advertisesPojoList;
    Context context;

    public AdvertisesAdapter(List<AdvertisesPojo> advertisesPojoList, Context context) {
        this.advertisesPojoList = advertisesPojoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return advertisesPojoList.size();
    }

    @Override
    public Object getItem(int position) {
        return advertisesPojoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(context).inflate(R.layout.advertiseslayout,parent,false);
        ImageView resim;
        TextView baslik,fiyat,adres;

        resim = convertView.findViewById(R.id.ilanlarIlanResim);
        baslik = convertView.findViewById(R.id.ilanlarIlanBaslik);
        fiyat = convertView.findViewById(R.id.ilanlarIlanFiyat);
        adres = convertView.findViewById(R.id.ilanlarIlanAdres);



        baslik.setText(advertisesPojoList.get(position).getBaslik());
        fiyat.setText(advertisesPojoList.get(position).getFiyat());
        adres.setText(advertisesPojoList.get(position).getSehir()+" "+advertisesPojoList.get(position).getIlce());

        Picasso.with(context).load("http://yazilimgunlukleri.com/autogallery/"+ advertisesPojoList.get(position).getResim()).resize(100,100).into(resim);

        return convertView;

    }
}
