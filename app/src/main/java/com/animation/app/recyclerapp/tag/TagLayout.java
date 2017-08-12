package com.animation.app.recyclerapp.tag;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.os.TraceCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 12.08.2017
 */

public class TagLayout extends ViewGroup {

    private int deviceWidth;
    private int deviceHeight;

    private float currAlpha = 0f;

    List<Animator> mAnimators = new ArrayList<>();

    private Adapter mAdapter;


    public TagLayout(Context context) {
        this(context, null, 0);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        int currWidth, currHeight, currLeft, currTop, maxHeight;

        deviceHeight = getBottom();
        deviceWidth = getRight();

        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;

        maxHeight = 0;
        currLeft = childLeft;
        currTop = childTop;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                return;
            }

            child.measure(
                    MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));

            currWidth = child.getMeasuredWidth();
            currHeight = child.getMeasuredHeight();

            if (currLeft + currWidth >= childRight) {
                currLeft = childLeft;
                currTop += maxHeight;
                maxHeight = 0;
            }

            child.layout(currLeft, currTop, currLeft + currWidth, currTop + currHeight);

            if (maxHeight < currHeight) {
                maxHeight = currHeight;
            }

            currLeft += currWidth;

            child.setAlpha(currAlpha);

            if (currAlpha < 1f) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, View.ALPHA, 0f, 1f);
                animator.setDuration(10);
                mAnimators.add(animator);
            }
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int mLeftWidth = 0;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            int chWidth = child.getMeasuredWidth();
            maxWidth += Math.max(maxWidth, chWidth);
            mLeftWidth += chWidth;

            int chHeight = child.getMeasuredHeight();
            if (maxHeight > deviceHeight) {
                removeViews(i - 1, count - i + 1);
                break;
            }

            if (mLeftWidth > deviceWidth) {
                maxHeight += chHeight;
                mLeftWidth = chWidth;
            } else {
                maxHeight = Math.max(maxHeight, chHeight);
            }

            childState = combineMeasuredStates(childState, chHeight);
        }

        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
    }

    public void animateChilds() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(mAnimators);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                currAlpha = 1f;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimators.clear();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
    }


    public void updateViews() {
        removeAllViews();
        currAlpha = 0f;


        if (mAdapter.getItemCount() > 0) {
            for (int i = 0; i < mAdapter.getItemCount(); i++) {
                ViewHolder holder = mAdapter.onCreateViewHolder(TagLayout.this);
                mAdapter.onBindViewHolder(holder, i);
                addView(holder.getItemView());
            }
        }

        invalidate();
    }


    public static abstract class Adapter<VH extends ViewHolder> {

        public abstract VH onCreateViewHolder(ViewGroup parent);

        public abstract void onBindViewHolder(VH holder, int position);

        public abstract int getItemCount();

    }

    public static abstract class ViewHolder {

        public final View itemView;

        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }

        public View getItemView() {
            return itemView;
        }
    }
}
