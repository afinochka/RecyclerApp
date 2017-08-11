package com.animation.app.recyclerapp.flow;

import android.view.ViewGroup;

/**
 * Created by Dasha on 11.08.2017
 */

public abstract class FlowAdapter<VH extends FlowViewHolder> {

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(VH holder, int position);

    public abstract int getItemCount();
}
