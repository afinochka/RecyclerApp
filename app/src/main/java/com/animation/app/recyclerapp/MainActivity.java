package com.animation.app.recyclerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.animation.app.recyclerapp.tag.StringItemAdapter;
import com.animation.app.recyclerapp.tag.TagLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn;

    TagLayout tagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagLayout = (TagLayout) findViewById(R.id.tagLayout);
        tagLayout.setAdapter(new StringItemAdapter());

        btn = (Button) findViewById(R.id.btn_apply);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
            }
        });

    }

    private void apply() {
        LayoutInflater layoutInflater = getLayoutInflater();
        String tag;

       /* tagLayout.updateViews();
        Random r = new Random();
        int size = r.nextInt(50 - 30) + 30;

        for (int i = 0; i <= size; i++) {
            if (i % 5 == 0) {
                tag = "#tag" + i + "pur";
            } else {
                tag = "#tag" + i;
            }

            View tagView = layoutInflater.inflate(R.layout.child_view, null, false);
            TextView tagTextView = (TextView) tagView.findViewById(R.id.tagTextView);
            tagTextView.setText(tag);
            tagLayout.addView(tagView);

        }*/

        tagLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tagLayout.animateChilds();
                tagLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        tagLayout.updateViews();

    }

}
