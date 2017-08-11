package com.animation.app.recyclerapp.flow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.animation.app.recyclerapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 11.08.2017
 */

public class FlowLyt extends RelativeLayout {

    private final List<List<View>> mLines;
    private final List<Integer> mLineHeights;
    private final List<Integer> mLineMargins;


    public FlowLyt(Context context) {
        this(context, (AttributeSet) null);
    }

    public FlowLyt(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLyt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mLines = new ArrayList<List<View>>();
        this.mLineHeights = new ArrayList<Integer>();
        this.mLineMargins = new ArrayList<Integer>();

    }



    /*public void setAdapter(FlowAdapter adapter){
        setFlowAdapter(adapter);
        requestLayout();
    }

    private void setFlowAdapter(FlowAdapter adapter){

    }*/

    public void setList(List<String> list) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int maxHeight = metrics.heightPixels;
        int height = 0;

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout);

        for (int i = 0; i < list.size(); i++) {

            TextView textView = new TextView(getContext());
            textView.setBackgroundResource(R.drawable.item_bg);
            textView.setText(list.get(i) + " " + i);
            textView.setPadding(15, 15, 15, 15);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(lp);

            linearLayout.addView(textView);

            textView.measure(0, 0);
            height += textView.getMeasuredHeight();

            invalidate();

            if (height + textView.getMeasuredHeight() > maxHeight) {
                break;
            }

        }

        addView(linearLayout);
    }
}
