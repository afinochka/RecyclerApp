package com.animation.app.recyclerapp.flow;

import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Dasha on 11.08.2017
 */

public abstract class FlowViewHolder {

    public final View itemView;

    private RelativeLayout mRelativeLayout;

    public FlowViewHolder(View itemView) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = itemView;
    }

    public View getItemView() {
        return itemView;
    }
}
