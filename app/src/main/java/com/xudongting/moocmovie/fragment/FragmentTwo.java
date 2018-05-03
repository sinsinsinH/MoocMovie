package com.xudongting.moocmovie.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idlestar.ratingstar.RatingStarView;
import com.xudongting.moocmovie.DetialActivity;
import com.xudongting.moocmovie.R;
import com.xudongting.moocmovie.entity.Movice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xudongting on 2018/4/8.
 */

public class FragmentTwo extends Fragment {

    private static final String TAG = "ddd";

    ArrayList<Movice> movices;
    Context context;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    public FragmentTwo() {
    }

    @SuppressLint("ValidFragment")
    public FragmentTwo(ArrayList<Movice> list, Context context) {
        this.movices = list;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView: " + movices);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(movices, context);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Intent it = new Intent(context, DetialActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("message", movices.get(position));
                it.putExtras(bundle);
                context.startActivity(it);
            }
        });
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new MyDecoration(10));

    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MainViewHolder> {
        private ArrayList<Movice> movices;
        private Context context;
        private OnItemClickListener onItemClickListener;


        public MyAdapter(ArrayList<Movice> movices, Context context) {
            this.movices = movices;
            this.context = context;
        }

        //设置点击事件接口
        public interface OnItemClickListener {
            public abstract void OnItemClickListener(int position);
        }

        //暴露点击事件的接口
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }


        @Override
        public MyAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_two_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyAdapter.MainViewHolder holder, final int position) {
            String str1 = movices.get(position).getDirectors().toString();
            String text1 = "导演：" + str1.substring(1, str1.length() - 1);
            String str2 = movices.get(position).getCasts().toString();
            String text2 = "主演：" + str2.substring(1, str2.length() - 1);

            Glide.with(context).load(movices.get(position).getImageUrl()).into(holder.imageView);
            holder.ratingBar.setRating((movices.get(position).getRating().getAverage())/2);
            holder.textView1.setText(movices.get(position).getTitle());
            holder.textView2.setText(text1);
            holder.textView3.setText(text2);
            holder.textView4.setText("上映：" + new Integer(movices.get(position).getYear()).toString());
            holder.textView5.setText(""+movices.get(position).getRating().getAverage());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClickListener(position);
                }
            });


        }

        @Override
        public int getItemCount() {
            return movices.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
            RatingStarView ratingBar;
            TextView textView5;

            public MainViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageview);
                textView1 = (TextView) itemView.findViewById(R.id.text1);
                textView2 = (TextView) itemView.findViewById(R.id.text2);
                textView3 = (TextView) itemView.findViewById(R.id.text3);
                textView4 = (TextView) itemView.findViewById(R.id.text4);
                ratingBar = (RatingStarView) itemView.findViewById(R.id.ratingbar);
                textView5=(TextView)itemView.findViewById(R.id.num);

            }
        }
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public MyDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = space;
            outRect.bottom = space;
            outRect.left = 0;
            outRect.right = 0;
        }
    }
}
