package com.example.pzdf.jinjie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.pzdf.jinjie.adapter.MainRecyclerViewAdapter;
import com.example.pzdf.jinjie.adapter.MainRecyclerViewGridAdapter;
import com.example.pzdf.jinjie.entity.GoodsInfoEntity;
import com.example.pzdf.jinjie.entity.MeetingEntity;
import com.example.pzdf.jinjie.recyclerInterface.OnItemClickListener;
import com.example.pzdf.jinjie.recyclerInterface.OnItemLongClickListener;
import com.example.pzdf.jinjie.retrofit.RetrofitActivity;
import com.example.pzdf.jinjie.view.DateBarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements OnItemClickListener,OnItemLongClickListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.checkbox) CheckBox checkBox;
    @BindView(R.id.retrofit_btn) Button retrofit_btn;

    private ArrayList<GoodsInfoEntity> goodsInfoEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        ArrayList<MeetingEntity> meetingEntities = new ArrayList<>();
        meetingEntities.add(new MeetingEntity("2019-11-16 09:30:00","2019-11-16 11:30:00",true));
        meetingEntities.add(new MeetingEntity("2019-11-16 14:00:00","2019-11-16 14:30:00",true));
        meetingEntities.add(new MeetingEntity("2019-11-16 15:00:00","2019-11-16 16:30:00",false));
        meetingEntities.add(new MeetingEntity("2019-11-16 11:30:00","2019-11-16 12:30:00",false));
        ArrayList<MeetingEntity> meetingEntities1 = new ArrayList<>();
        meetingEntities1.add(new MeetingEntity("2019-11-16 09:00:00","2019-11-16 11:00:00",true));
        meetingEntities1.add(new MeetingEntity("2019-11-16 14:30:00","2019-11-16 13:00:00",true));
        meetingEntities1.add(new MeetingEntity("2019-11-16 15:00:00","2019-11-16 16:30:00",true));
        meetingEntities1.add(new MeetingEntity("2019-11-16 12:00:00","2019-11-16 12:30:00",false));
        ArrayList<MeetingEntity> meetingEntities2 = new ArrayList<>();
        meetingEntities2.add(new MeetingEntity("2019-11-16 09:30:00","2019-11-16 11:30:00",true));
        meetingEntities2.add(new MeetingEntity("2019-11-16 11:00:00","2019-11-16 14:30:00",true));
        meetingEntities2.add(new MeetingEntity("2019-11-16 15:30:00","2019-11-16 16:00:00",false));
        meetingEntities2.add(new MeetingEntity("2019-11-16 10:00:00","2019-11-16 10:30:00",false));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities1));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities2));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities));
        goodsInfoEntities.add(new GoodsInfoEntity("","Kiehl's 科颜氏 高保湿面霜 50毫升 秋冬必备","滋润 | 任何肤质",666,999,"韩国品牌","","自营",meetingEntities));

    }

    private void initView() {
        final MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(goodsInfoEntities,this);
        final MainRecyclerViewGridAdapter gridAdapter = new MainRecyclerViewGridAdapter(goodsInfoEntities,this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (adapter != null && gridAdapter != null){
                    if (!b){
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);
                    } else {
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                        recyclerView.setAdapter(gridAdapter);
                    }
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        gridAdapter.setOnItemClickListener(this);
        gridAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(adapter);
    }




    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(MainActivity.this,"onItemClick==="+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View v, int position) {
        Toast.makeText(MainActivity.this,"onItemLongClick==="+position,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.retrofit_btn)
    public void toRetrofit(){
        Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
        startActivity(intent);
    }
}
