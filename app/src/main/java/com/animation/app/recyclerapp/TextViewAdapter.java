package com.animation.app.recyclerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.animation.app.recyclerapp.flow.FlowAdapter;
import com.animation.app.recyclerapp.flow.FlowViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 11.08.2017
 */

public class TextViewAdapter extends FlowAdapter {

    List<String> args = new ArrayList<>();

    public void setList(List<String> args){
        this.args = args;
    }

    @Override
    public FlowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlowViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return args.size();
    }


    private class TvViewHolder extends FlowViewHolder{



        public TvViewHolder(View itemView) {
            super(itemView);
        }
    }
}
