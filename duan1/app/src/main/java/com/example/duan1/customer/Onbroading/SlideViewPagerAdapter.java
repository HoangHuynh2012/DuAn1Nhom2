package com.example.duan1.customer.Onbroading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.duan1.R;

import java.util.List;

public class SlideViewPagerAdapter extends PagerAdapter {
    Context context;
    List<ScreenItem> itemList;

    public SlideViewPagerAdapter(Context context, List<ScreenItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.slide_screen,null);

        ImageView img = layoutScreen.findViewById(R.id.introImage);
        TextView tvTitle = layoutScreen.findViewById(R.id.introTitle);
        TextView tvDes = layoutScreen.findViewById(R.id.introDes);

//        TextView tvDescription = layoutScreen.findViewById(R.id.introTitle2);

        tvTitle.setText(itemList.get(position).getTitle());
        tvDes.setText(itemList.get(position).getDescription());
        img.setImageResource(itemList.get(position).getScreenimage());

        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
