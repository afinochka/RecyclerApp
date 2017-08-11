package com.animation.app.recyclerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.animation.app.recyclerapp.flow.FlowLayout;
import com.animation.app.recyclerapp.flow.FlowLyt;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn;
    FlowLayout mFlowLayout;

    FlowLyt rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlowLayout = (FlowLayout) findViewById(R.id.lyt_flow);

        rl = (FlowLyt) findViewById(R.id.lyt_root);

        btn = (Button) findViewById(R.id.btn_apply);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
                btn.setVisibility(View.GONE);
            }
        });

    }

    private void apply() {
        List<String> list = Collections.nCopies(500, "foo");
        /*for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.item_bg);
            textView.setText(list.get(i) + " " + i);
            textView.setPadding(15, 15, 15, 15);


            int height = mFlowLayout.getHeight();
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            if (height < metrics.heightPixels)
                mFlowLayout.addView(textView,
                        new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT));

        }*/

        rl.setList(list);

      /*  DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int maxHeight = metrics.heightPixels;
        int height = 0;
        for(int i = 0; i < list.size(); i++){
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.item_bg);
            textView.setText(list.get(i) + " " + i);
            textView.setPadding(15, 15, 15, 15);

            rl.addView(textView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            rl.measure(0, 0);
            height = height + rl.getMeasuredHeight();

            if(height > maxHeight){
                rl.removeView(textView);
                break;
            }

        }*/


    }


}
