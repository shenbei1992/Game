package com.shen.game.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shen on 15/9/25.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    Canvas canvas;
    Paint paint;
    Paint buttonUp;
    Paint buttonDown;
    Paint buttonLeft;
    Paint buttonRight;
    SurfaceHolder sfh;
    int width;
    int height;
    int bodyR;//蛇身体半径
    int x;//蛇头坐标
    int y;

    public GameView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);

        paint = new Paint();
        buttonUp = new Paint();
        buttonUp.setColor(Color.RED);
        buttonDown = new Paint();
        buttonDown.setColor(Color.YELLOW);
        buttonLeft = new Paint();
        buttonLeft.setColor(Color.BLUE);
        buttonRight = new Paint();
        buttonRight.setColor(Color.GREEN);


    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        width = this.getWidth();
        height = this.getHeight();
        bodyR = width / 54;
        x = width / 2;
        y = height / 2;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        myDraw();
                        Log.e("shen", y + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    List<JieDian> jieDianList = new ArrayList<JieDian>();
    List<Body> bodyList = new ArrayList<Body>();

    public void myDraw() {
        canvas = sfh.lockCanvas();
        if (canvas != null) {
            canvas.drawRGB(255, 255, 255);
            drawSnake();
            new Snake(jieDianList, bodyList).draw();

            canvas.drawRect(width / 7 * 3, height - width / 7, width / 7 * 4, height, buttonDown);
            canvas.drawRect(width / 7 * 3, height - width / 7 * 3, width / 7 * 4, height - width / 7 * 2, buttonUp);
            canvas.drawRect(width / 7 * 4, height - width / 7 * 2, width / 7 * 5, height - width / 7, buttonRight);
            canvas.drawRect(width / 7 * 2, height - width / 7 * 2, width / 7 * 3, height - width / 7, buttonLeft);
            sfh.unlockCanvasAndPost(canvas);
        }
        y = y - bodyR * 2;
    }

    public void drawSnake() {
        int xx = x;
        int yy = y;
        for (int i = 0; i < 4; i++) {
            bodyList.add(new Body(canvas, paint, xx, yy, bodyR));
            yy = yy - 2 * bodyR;
        }

    }


}
