package com.example.quyet.qrappmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Khuong Duy on 9/28/2017.
 */

public class FourPerThreeRelaytiveLayout extends RelativeLayout {
    public FourPerThreeRelaytiveLayout(Context context) {
        super(context);
    }

    public FourPerThreeRelaytiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FourPerThreeRelaytiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width * 16 / 9);
    }
}
