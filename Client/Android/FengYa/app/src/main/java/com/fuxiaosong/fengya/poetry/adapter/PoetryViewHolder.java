package com.fuxiaosong.fengya.poetry.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.poetry.PoetryDetailActivity;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //诗名称 TextView
    public TextView titleTV;

    public PoetryViewHolder(View itemView) {
        super(itemView);

        titleTV = (TextView) itemView.findViewById(R.id.poetry_item_tv);

        //设置点击事件
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //诗的 id
        final String poetryId = titleTV.getTag().toString();
//        String[] data = poetryId.split("#");

        //启动诗界面
        Intent intent = new Intent(view.getContext() , PoetryDetailActivity.class);
        intent.putExtra("poetryId" , poetryId);
        view.getContext().startActivity(intent);
    }
}
