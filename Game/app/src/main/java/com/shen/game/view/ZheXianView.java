package com.shen.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shen on 15/9/18.
 */
public class ZheXianView extends View {


    public ZheXianView(Context context) {
        super(context);
    }

    public ZheXianView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZheXianView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ZheXianView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(7);
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(50, 100, 50, this.getWidth() - 100, paint);
        canvas.drawLine(50, this.getWidth() - 100, this.getWidth() - 50, this.getWidth() - 100, paint);
        canvas.drawRect(80,120,120,this.getWidth() - 100,paint);
        super.onDraw(canvas);
    }
}
