package com.working.view;

import android.content.Context;
import android.util.AttributeSet;

public class CircleImageView extends RoundRectImageView {

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRoundRatio(0.5f);
    }
}
