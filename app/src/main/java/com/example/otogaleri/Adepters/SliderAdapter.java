package com.example.otogaleri.Adepters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.otogaleri.Models.SliderPojo;
import com.example.otogaleri.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    List<SliderPojo> list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderPojo> sliderPojoList, Context context) {
        this.list = sliderPojoList;
        this.context = context;
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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.from(context).inflate(R.layout.sliderlayout,container,false);

        ImageView imageView = view.findViewById(R.id.sliderImageView);

        Picasso.with(context).load("http://yazilimgunlukleri.com/autogallery/"+ list.get(position).getResim()).resize(1050,550).into(imageView);

        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
