package com.fuxiaosong.fengya.poet.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fuxiaosong.fengya.R;
import com.fuxiaosong.fengya.poetry.PoetryActivity;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //诗人名字所在的 CardView
//    public CardView poetCardView;
    //诗人名字 TextView
    public TextView poetNameTV;

    public PoetViewHolder(View itemView) {
        super(itemView);
//        poetCardView = (CardView) itemView.findViewById(R.id.poet_item_cv);
        poetNameTV = (TextView) itemView.findViewById(R.id.poet_item_tv);

        //设置点击事件
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //诗人的 id
        final String poetId = poetNameTV.getTag().toString();
        final String name = poetNameTV.getText().toString();

        if(poetId == "00"){
            return;
        }


        //启动诗界面
        Intent intent = new Intent(view.getContext() , PoetryActivity.class);
        intent.putExtra("poetId" , poetId);
        intent.putExtra("name" , name);
        view.getContext().startActivity(intent);
    }
}
