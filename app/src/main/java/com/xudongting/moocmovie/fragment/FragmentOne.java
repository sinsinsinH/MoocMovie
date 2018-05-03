package com.xudongting.moocmovie.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idlestar.ratingstar.RatingStarView;
import com.xudongting.moocmovie.DetialActivity;
import com.xudongting.moocmovie.R;
import com.xudongting.moocmovie.UserLoader;
import com.xudongting.moocmovie.entity.Movice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by xudongting on 2018/4/8.
 */

public class FragmentOne extends Fragment {
    private static final String TAG = "ddd";


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Loader
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, new LoaderManager.LoaderCallbacks<ArrayList<Movice>>() {
            @Override
            public Loader<ArrayList<Movice>> onCreateLoader(int id, Bundle args) {
                return new UserLoader(getContext());
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<Movice>> loader, final ArrayList<Movice> data) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                MyAdapter myAdapter = new MyAdapter(data, getContext());
                myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(int position) {
                        Context context = getContext();
                        Intent it = new Intent(context, DetialActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("message", data.get(position));
                        it.putExtras(bundle);
                        context.startActivity(it);
                    }
                });
                recyclerView.setAdapter(myAdapter);
                recyclerView.addItemDecoration(new MyDecoration(10));
                Log.d(TAG, "onLoadFinished: " + data);
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<Movice>> loader) {

            }
        });

    }

    //自定义RecyclerView设配器
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
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_one_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, final int position) {
            Glide.with(context)
                    .load(movices.get(position).getImageUrl())
//                    .placeholder(R.drawable.loading1)
                    .into(holder.imageView);
            holder.textView.setText(movices.get(position).getTitle());
            holder.ratingBar.setRating((movices.get(position).getRating().getAverage())/2);
            holder.textViewNum.setText(""+movices.get(position).getRating().getAverage());
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

        //自定义ViewHolder
        class MainViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            RatingStarView ratingBar;
            TextView textViewNum;

            public MainViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageview);
                textView = (TextView) itemView.findViewById(R.id.textview);
                ratingBar = (RatingStarView) itemView.findViewById(R.id.ratingbar);
                textViewNum=(TextView)itemView.findViewById(R.id.num);

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
            outRect.left = space;
            outRect.right = space;
        }
    }
}


