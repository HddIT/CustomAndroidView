package com.example.pzdf.jinjie.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pzdf.jinjie.R;
import com.example.pzdf.jinjie.entity.PieData;
import com.example.pzdf.jinjie.entity.Translation;
import com.example.pzdf.jinjie.view.PieChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能描述:
 * Created by pzdf on 2018/11/16.
 */
public class RetrofitActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.text)TextView textView;

    private List<PieData> mDatas = new ArrayList<>();
    @BindView(R.id.btn1) Button btnpart;
    @BindView(R.id.btn2) Button btncount;
    @BindView(R.id.pieChart)PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_layout);
        ButterKnife.bind(this);
        request();
        btnpart.setOnClickListener(this);
        btncount.setOnClickListener(this);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 6; i++) {
            mDatas.add(new PieData((float) (10+Math.random()*90)));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                pieChart.setmStartAngle(180);
                pieChart.setData(mDatas, PieChart.PART);
                break;
            case R.id.btn2:
                pieChart.setmStartAngle(45);
                pieChart.setData(mDatas, PieChart.COUNT);
                break;
        }
    }


    @OnClick(R.id.text)
    public void onRequest(){
        request();
    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<Translation>  call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                Translation translation = response.body();
                textView.setText(translation.toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                textView.setText("请求失败！");
            }
        });
    }
}
