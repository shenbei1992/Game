package com.shen.game.paopaolong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by shen on 15/10/8.
 */
public class PaoGameView extends SurfaceView implements SurfaceHolder.Callback {
    public PaoGameView(Context context) {
        super(context);
    }

    public PaoGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaoGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaoGameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    public void myDraw(){

    }
}
