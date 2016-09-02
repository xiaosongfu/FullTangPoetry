package com.fuxiaosong.fengya.poet;

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
import com.fuxiaosong.fengya.poet.adapter.PoetAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetActivity extends AppCompatActivity {

    //
    RecyclerView poetRecycleView;
    LinearLayoutManager linearLayoutManager;
    //
    PoetAdapter poetAdapter;

    //
    ArrayList<PoetVO> dataSource;

    //
    Handler handler;


    boolean isLoading = false;
    int pageNo = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poet);

        //初始化视图
        initViews();



        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == ResponseCode.REQUEST_SUCCESS){

                    if(pageNo == 1){
                        setRecycleView(msg.obj.toString());
                    }else{

                        isLoading = false;

                        fillMoreDataForRecycleView(msg.obj.toString());
                    }

                    pageNo ++;
                }

            }
        };


        //初始化数据
        loadData();

        //设置 RecyclerView
//        setRecycleView();
    }

    /**
     * 初始化视图
     */
    public void initViews() {
        //绑定 View
        poetRecycleView = (RecyclerView) findViewById(R.id.poet_recycle);

        linearLayoutManager = new LinearLayoutManager(this);
        //
        getSupportActionBar().setTitle("诗人");


        poetRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading && linearLayoutManager.getItemCount() - poetRecycleView.getChildCount() <= linearLayoutManager.findFirstVisibleItemPosition()){

                    isLoading = true;

                    new NetworkTask(URLConfig.GET_POET_URL +"?pageNo=" +pageNo , handler).start();
                }
            }
        });
    }

    /**
     * 初始化数据
     */
    public void loadData(){
        //
        new NetworkTask(URLConfig.GET_POET_URL ,handler).start();
    }

    /**
     * 设置 RecyclerView
     */
    public void setRecycleView(String data){


        ResponseModel responseModel = new Gson().fromJson(data, ResponseModel.class);

        if(responseModel==null || "400".equals(responseModel.code)){
            Toast.makeText(this , "请求错误" , Toast.LENGTH_SHORT).show();
        }else {
            //
            Type listType = new TypeToken<ArrayList<PoetVO>>(){}.getType();
            dataSource = new Gson().fromJson(responseModel.data, listType);

            //!!!!!!!!!
            dataSource.add(dataSource.size() , new PoetVO("00" , " 上拉加载更多..."));

            //为 RecycleView 设置布局管理器
            poetRecycleView.setLayoutManager(linearLayoutManager);

            //创建适配器
            poetAdapter = new PoetAdapter(dataSource);
            //为 RecycleView 设置适配器
            poetRecycleView.setAdapter(poetAdapter);
        }
    }


    public void fillMoreDataForRecycleView(String data) {
        ResponseModel responseModel = new Gson().fromJson(data, ResponseModel.class);

        if (responseModel == null || "400".equals(responseModel.code)) {
            Toast.makeText(this, "请求错误", Toast.LENGTH_SHORT).show();
        } else {

            System.out.println(responseModel.data);

            //
            Type listType = new TypeToken<ArrayList<PoetVO>>() {}.getType();
            ArrayList<PoetVO> dataList = new Gson().fromJson(responseModel.data, listType);


            int lastIndex = dataSource.size() - 1;
            dataSource.remove(lastIndex);
            poetAdapter.notifyItemRemoved(lastIndex);
//
            int begin = lastIndex;
            int size = dataList.size() - 1;
            int end = lastIndex + size - 1;
            int index = 0;
//            System.out.println(begin + " to " + end);
            while (index < size) {
                dataSource.add(begin + index, dataList.get(index));
                index++;
            }
            dataSource.add(end, new PoetVO("00", " 上拉加载更多2..."));
            poetAdapter.notifyItemRangeInserted(begin, end);
        }
    }
}