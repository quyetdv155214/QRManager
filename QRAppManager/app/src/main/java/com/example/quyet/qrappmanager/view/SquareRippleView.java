package com.example.quyet.qrappmanager.view;

import android.content.Context;
import android.util.AttributeSet;

import com.andexert.library.RippleView;

/**
 * Created by Khuong Duy on 9/21/2017.
 */

public class SquareRippleView extends RippleView {


    public SquareRippleView(Context context) {
        super(context);
    }

    public SquareRippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRippleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int size;
        if (widthMode == MeasureSpec.EXACTLY && widthSize > 0) {
            size = widthSize;
        } else if (heightMode == MeasureSpec.EXACTLY && heightSize > 0) {
            size = heightSize;
        } else {
            size = widthSize < heightSize ? widthSize : heightSize;
        }

        int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        super.onMeasure(finalMeasureSpec, finalMeasureSpec);
    }

}
