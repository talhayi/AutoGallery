package com.example.otogaleri.Adepters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.otogaleri.DetailAdvertise;
import com.example.otogaleri.Models.FavoriteAdvertiseSliderPojo;
import com.example.otogaleri.Models.SliderPojo;
import com.example.otogaleri.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class FavoriteSliderAdapter extends PagerAdapter {

    List<FavoriteAdvertiseSliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;
    Activity activity;

    public FavoriteSliderAdapter(List<FavoriteAdvertiseSliderPojo> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.from(context).inflate(R.layout.sliderlayout,container,false);

        ImageView imageView = view.findViewById(R.id.sliderImageView);

        Picasso.with(context).load("http://yazilimgunlukleri.com/autogallery/"+ list.get(position).getResimyolu()).resize(1050,550).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.get(position).getIlanid()!=null){
                    Intent intent = new Intent(activity, DetailAdvertise.class);
                    intent.putExtra("ilanid",list.get(position).getIlanid());
                    activity.startActivity(intent);
                }

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
