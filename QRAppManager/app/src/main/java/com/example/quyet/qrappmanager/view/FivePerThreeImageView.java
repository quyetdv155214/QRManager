package com.example.quyet.qrappmanager.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Khuong Duy on 9/19/2017.
 */

public class FivePerThreeImageView extends android.support.v7.widget.AppCompatImageView {
    public FivePerThreeImageView(Context context) {
        super(context);
    }

    public FivePerThreeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FivePerThreeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width*3/5);
    }

}
