package com.xudongting.moocmovie;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.xudongting.moocmovie.entity.Movice;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xudongting on 2018/4/20.
 */

public class DetialActivity extends Activity {
    @BindView(R.id.imageview)
    ImageView imageView;
    @BindView(R.id.text1)
    TextView textView1;
    @BindView(R.id.text2)
    TextView textView2;
    @BindView(R.id.text3)
    TextView textView3;
    @BindView(R.id.text4)
    TextView textView4;
    @BindView(R.id.text5)
    TextView textView5;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private static final String TAG = "ddd";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        ButterKnife.bind(this);
        Movice movices = getIntent().getExtras().getParcelable("message");
        Log.d(TAG, "onCreate: " + movices);
        //解析数据
        String str1=movices.getDirectors().toString();
        String text1=str1.substring(1,str1.length()-1);
        String str2=movices.getCasts().toString();
        String text2=str2.substring(1,str2.length()-1);
        String str3=movices.getTypes().toString();
        String text3=str3.substring(1,str3.length()-1);
        Glide.with(this).load(movices.getImageUrl()).into(imageView);
        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(new Integer(movices.getYear()).toString());
        textView4.setText(text3);
        textView5.setText(movices.getDescription());
        mCollapsingToolbarLayout.setTitle(movices.getTitle());
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
    }
}


