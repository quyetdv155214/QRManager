package com.example.quyet.qrappmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Khuong Duy on 9/19/2017.
 */

public class FivePerThreeFrameLayout extends FrameLayout {

    public FivePerThreeFrameLayout(Context context) {
        super(context);
    }

    public FivePerThreeFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FivePerThreeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width*3/5);
    }

}
