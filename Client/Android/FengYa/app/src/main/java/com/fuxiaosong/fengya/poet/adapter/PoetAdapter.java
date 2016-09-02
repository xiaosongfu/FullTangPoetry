package com.fuxiaosong.fengya.poet.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.common.model.PoetVO;

import java.util.ArrayList;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetAdapter extends RecyclerView.Adapter<PoetViewHolder> {

    //数据源
    private ArrayList<PoetVO> dataSource = null;

    /**
     * 构造方法
     * @param dataSource 数据源
     */
    public PoetAdapter(ArrayList<PoetVO> dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public PoetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //填充布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item_poet, parent, false);

        //返回 PoetViewHolder
        return new PoetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PoetViewHolder holder, int position) {

        if(position == getItemCount()){
//            holder.poetNameTV.setBackgroundColor();
        }

        //设置诗人名字
        holder.poetNameTV.setText(dataSource.get(position).name);
        //设置诗人 id
        holder.poetNameTV.setTag(dataSource.get(position).id);

        // setTag
        holder.itemView.setTag(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }





}
