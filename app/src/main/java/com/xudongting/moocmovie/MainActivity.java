package com.xudongting.moocmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.xudongting.moocmovie.fragment.FragmentOne;
import com.xudongting.moocmovie.fragment.FragmentTwo;
import com.xudongting.moocmovie.entity.Movice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ddd";
    private ArrayList<Movice> movices_new = new ArrayList<>();
    private ArrayList<Movice> movices = new ArrayList<>();
    private String type_str = "";
    private String name = "";
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private List listType;

    @BindView(R.id.type)
    Spinner type;
    @BindView(R.id.btn_search)
    ImageButton search;
    @BindView(R.id.movieName)
    EditText moviceName;
    @BindView(R.id.back)
    TextView textView_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化数据
        initData();
        //为下拉列表设置适配器
        setAdapter();
        //读取FragmentOne
        loadFragment();
        //设置监听器
        setListener();

    }

    //显示FragmentOne
    private void loadFragment() {
        fragmentOne = new FragmentOne();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.framelayout, fragmentOne)
                .commit();
    }

    //设置监听器
    private void setListener() {
        //spinner设置监听器
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        type_str = "动作";
                        break;
                    case 2:
                        type_str = "科幻";
                        break;
                    case 3:
                        type_str = "剧情";
                        break;
                    case 4:
                        type_str = "运动";
                        break;
                    case 5:
                        type_str = "传记";
                        break;
                    case 6:
                        type_str = "喜剧";
                        break;
                    case 7:
                        type_str = "犯罪";
                        break;
                    case 8:
                        type_str = "悬疑";
                        break;
                    case 9:
                        type_str = "爱情";
                        break;
                    case 10:
                        type_str = "奇幻";
                        break;
                    case 11:
                        type_str = "动画";
                        break;
                    case 12:
                        type_str = "惊悚";
                        break;
                    case 13:
                        type_str = "恐怖";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //search按钮设置监听器
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movices_new.clear();
                name = moviceName.getText().toString();
                for (int i = 0; i < movices.size(); i++) {
                    if ((movices.get(i).getTitle().contains(name)) && (movices.get(i).getTypes().toString().contains(type_str))) {
                        movices_new.add(movices.get(i));
                    }
                }
                Log.d(TAG, "onClick: " + movices_new);
                fragmentTwo = new FragmentTwo(movices_new, MainActivity.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragmentTwo).commit();
                //返回按钮设置可见
                textView_back.setVisibility(View.VISIBLE);
            }

        });

        //返回按钮设置监听器
        textView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragmentOne).commit();
                //返回按钮设置不可见
                textView_back.setVisibility(View.INVISIBLE);
            }
        });

    }

    //spinner设置设配器
    private void setAdapter() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listType);
        type.setAdapter(adapter);
    }

    //初始化数据
    private void initData() {
        listType = new ArrayList();
        listType.add("请选择");
        listType.add("动作");
        listType.add("科幻");
        listType.add("剧情");
        listType.add("运动");
        listType.add("传记");
        listType.add("喜剧");
        listType.add("犯罪");
        listType.add("悬疑");
        listType.add("爱情");
        listType.add("奇幻");
        listType.add("动画");
        listType.add("惊悚");
        listType.add("恐怖");
        new Thread() {
            @Override
            public void run() {
                super.run();
                UserLoader userLoader = new UserLoader(MainActivity.this);
                movices = userLoader.loadInBackground();
            }
        }.start();


    }
}
