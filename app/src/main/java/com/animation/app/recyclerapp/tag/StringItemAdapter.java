package com.animation.app.recyclerapp.tag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.animation.app.recyclerapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dasha on 12.08.2017
 */

public class StringItemAdapter extends TagLayout.Adapter<StringItemAdapter.StringHolder> {

    List<String> args = Collections.nCopies(250, "#tag");

    @Override
    public StringHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_view, parent, false);
        return new StringHolder(view);
    }

    @Override
    public void onBindViewHolder(StringHolder holder, int position) {
        String str = args.get(position);
        holder.mTextView.setText(str + position);
    }

    @Override
    public int getItemCount() {
        return args.size();
    }

    public class StringHolder extends TagLayout.ViewHolder {

        TextView mTextView;

        public StringHolder(final View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tagTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "click item", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
