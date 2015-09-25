package com.shen.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by shen on 15/9/15.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder sfh;
    private Paint paint;
    int x = 100;
    int y = 0;
    int h;

    public MySurfaceView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myDraw();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    y = y + 10;
                    if (y == h) {
                        y = 0;
                    }
                    myDraw();
                }
            }
        });
        thread.start();
        h = this.getHeight();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
        myDraw();

        return true;


    }
        


    public void myDraw() {
        Canvas canvas = sfh.lockCanvas();
        //canvas.drawColor(Color.BLACK);//每次绘图前填充画布
//        Paint paint1 = new Paint();//画一个等同屏幕的矩形
//        paint1.setColor(Color.BLACK);
//        canvas.drawRect(0,0,this.getWidth(),this.getHeight(),paint1);
        canvas.drawRGB(255, 255, 255);//填充RGB值
        canvas.drawText("备", x, y, paint);
        sfh.unlockCanvasAndPost(canvas);
    }
}
