package com.fuxiaosong.fengya.poetry.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.common.model.PoetryVO;

import java.util.ArrayList;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetryAdapter extends RecyclerView.Adapter<PoetryViewHolder> {

    private ArrayList<PoetryVO> dataSource = null;

    public PoetryAdapter(ArrayList<PoetryVO> dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public PoetryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //填充布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item_poetry, parent, false);

        //返回 PoetViewHolder
        return new PoetryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PoetryViewHolder holder, int position) {
        //设置诗的名字
        holder.titleTV.setText(dataSource.get(position).title);
        //设置诗的 id
        holder.titleTV.setTag(dataSource.get(position).id);

        // setTag
        holder.itemView.setTag(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}