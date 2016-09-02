package com.fuxiaosong.fengya.poetry;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.common.config.URLConfig;
import com.fuxiaosong.fengya.common.model.PoetVO;
import com.fuxiaosong.fengya.common.model.PoetryVO;
import com.fuxiaosong.fengya.common.model.network.ResponseModel;
import com.fuxiaosong.fengya.common.network.NetworkTask;
import com.fuxiaosong.fengya.common.network.ResponseCode;
import com.fuxiaosong.fengya.poetry.adapter.PoetryAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetryActivity extends AppCompatActivity {
    //RecyclerView
    RecyclerView poetryRecycleView;
    //Adapter
    PoetryAdapter poetryAdapter;

    //数据源
    ArrayList<PoetryVO> dataSource;

    //诗人 ID
    private String poetId;
    //诗人名字
    private String name;


    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poet);



        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == ResponseCode.REQUEST_SUCCESS){
                    setRecycleView(msg.obj.toString());
                }

            }
        };

        //初始化数据
        initData();

        //初始化视图
        initViews();

        //设置 RecycleView
//        setRecycleView();


    }

    /**
     * 初始化数据
     *
     */
    public void initData(){
        //从 Intent 中获取诗人 id 和诗人 name
        poetId = getIntent().getStringExtra("poetId");
        name = getIntent().getStringExtra("name");


        //!!!
//        dataSource = new ArrayList<>();

        //
        new NetworkTask(URLConfig.GET_POETRY_URL + "?poetId=" + poetId, handler).start();
    }

    /**
     * 初始化视图
     */
    public void initViews() {
        //绑定 View
        poetryRecycleView = (RecyclerView) findViewById(R.id.poet_recycle);

        //设置 Title
        getSupportActionBar().setTitle(name);
    }

    /**
     * 设置 RecycleView
     */
    public void setRecycleView(String data){
        //为 RecycleView 设置布局管理器
        poetryRecycleView.setLayoutManager(new LinearLayoutManager(this));

        ResponseModel responseModel = new Gson().fromJson(data, ResponseModel.class);

        if(responseModel==null || "400".equals(responseModel.code)){
            Toast.makeText(this , "请求错误" , Toast.LENGTH_SHORT).show();
        }else {
            //
            Type listType = new TypeToken<ArrayList<PoetryVO>>(){}.getType();
            dataSource = new Gson().fromJson(responseModel.data, listType);

            //创建 适配器
            poetryAdapter = new PoetryAdapter(dataSource);

            //为 RecycleView 设置适配器
            poetryRecycleView.setAdapter(poetryAdapter);
        }
    }
}
