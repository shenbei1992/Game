package com.shen.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shen on 15/9/14.
 */
public class MyView extends View {

    int textx = 20;
    int texty = 20;

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.GREEN);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);
        canvas.drawText("shen", textx, texty, paint);
        Log.e("shen",this.getHeight()+"");
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            textx = x;
            texty = y;
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            textx = x;
            texty = y;
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            textx = x;
            texty = y;
        }

        invalidate();
        //postInvalidate();
        return true;
    }
}
