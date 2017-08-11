package com.animation.app.recyclerapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 11.08.2017
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    private List<String> data = new ArrayList<>();

    public RvAdapter() {

    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new RvViewHolder(view);
    }

    public void setList(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void changeListSize(int size) {
        if (size <= 0 || data.size() == 0)
            return;

        if (data.size() > size) {
            setList(new ArrayList<>(data.subList(0, size)));
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        String str = data.get(holder.getAdapterPosition());
        holder.mTextView.setText(str + ": " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public RvViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.txt);
        }
    }
}
