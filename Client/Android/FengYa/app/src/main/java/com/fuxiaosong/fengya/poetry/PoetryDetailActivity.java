package com.fuxiaosong.fengya.poetry;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.common.config.URLConfig;
import com.fuxiaosong.fengya.common.model.PoetryVO;
import com.fuxiaosong.fengya.common.model.network.ResponseModel;
import com.fuxiaosong.fengya.common.network.NetworkTask;
import com.fuxiaosong.fengya.common.network.ResponseCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxiaosong on 16/9/1.
 */
public class PoetryDetailActivity extends AppCompatActivity {

    TextView titleTV, poetNameTV;
//    TextView contentTV;

    ListView contentListView;

    String poetryId;
    String poetName;

    Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry_detail);

        poetryId = getIntent().getStringExtra("poetryId");
        poetName = "李白";//getIntent().getStringExtra("poetName");

        //初始化视图
        initViews();


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == ResponseCode.REQUEST_SUCCESS) {
                    fillData(msg.obj.toString());
                }

            }
        };


        //加载数据
        loadData();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        titleTV = (TextView) findViewById(R.id.title_tv);
        poetNameTV = (TextView) findViewById(R.id.poet_name_tv);
//        contentTV = (TextView) findViewById(R.id.content_tv);

        contentListView = (ListView) findViewById(R.id.content_lv);

        getSupportActionBar().hide();
    }

    /**
     * 加载数据
     */
    public void loadData() {
        //
        new NetworkTask(URLConfig.GET_POETRY_DETAIL_URL + "?poetryId=" + poetryId,handler).start();
    }

    /**
     * 填充数据
     *
     * @param data
     */
    public void fillData(String data) {
        ResponseModel responseModel = new Gson().fromJson(data, ResponseModel.class);

        if (responseModel==null ||  "400".equals(responseModel.code)) {
            Toast.makeText(this, "请求错误", Toast.LENGTH_SHORT).show();
        } else {
            //
            PoetryVO poetry = new Gson().fromJson(responseModel.data, PoetryVO.class);


            String[] dataArr = poetry.content.split("。");
            System.out.println(dataArr.length);

            ArrayList<Map<String, String>> dataList = new ArrayList<>(dataArr.length);
            Map<String, String> dataMap;
            for (String str : dataArr) {
                dataMap = new HashMap<>();
                dataMap.put("line", str+"。");
                dataList.add(dataMap);
            }


            titleTV.setText(poetry.title);
            poetNameTV.setText("-------- "+poetName);
//            contentTV.setText(poetry.content);

            contentListView.setAdapter(new SimpleAdapter(this
                    , dataList, R.layout.listview_item_poetry_line
                    , new String[]{"line"}, new int[]{R.id.line_tv}));
        }
    }
}