package com.shen.game.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

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

    Context context;

    public GameView(Context context) {
        super(context);
        this.context = context;
        sfh = this.getHolder();
        sfh.addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);
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
        drawSnake();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        myDraw();
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
    int state = 1;//蛇的初始运动状态

    public void myDraw() {
        canvas = sfh.lockCanvas();
        if (canvas != null) {
            canvas.drawRGB(255, 255, 255);
            for (Body body : bodyList) {
                body.setState(backState(body.getX(), body.getY(),body.getState()));
                Log.e("shen",body.getState()+"");
                body.draw();
            }

            canvas.drawRect(width / 7 * 3, height - width / 7, width / 7 * 4, height, buttonDown);
            canvas.drawRect(width / 7 * 3, height - width / 7 * 3, width / 7 * 4, height - width / 7 * 2, buttonUp);
            canvas.drawRect(width / 7 * 4, height - width / 7 * 2, width / 7 * 5, height - width / 7, buttonRight);
            canvas.drawRect(width / 7 * 2, height - width / 7 * 2, width / 7 * 3, height - width / 7, buttonLeft);
            sfh.unlockCanvasAndPost(canvas);
        }
        for (int i = 0; i < bodyList.size(); i++) {
            if (bodyList.get(i).getState() == 1) {
                bodyList.get(i).setY(bodyList.get(i).getY() - 2 * bodyR);//shang
            } else if (bodyList.get(i).getState() == 2) {
                bodyList.get(i).setY(bodyList.get(i).getY() + 2 * bodyR);//xia
            } else if (bodyList.get(i).getState() == 3) {
                bodyList.get(i).setX(bodyList.get(i).getX() - 2 * bodyR);//zuo
            } else if (bodyList.get(i).getState() == 4) {
                bodyList.get(i).setX(bodyList.get(i).getX() + 2 * bodyR);//you
            }

            x = bodyList.get(0).getX();
            y = bodyList.get(0).getY();

        }

    }


    public int backState(int xx, int yy, int state) {
        if (jieDianList.size() > 0) {
            for (int i = 0; i < jieDianList.size(); i++) {
                if (xx == jieDianList.get(i).getX() && yy == jieDianList.get(i).getY()) {
                    state = jieDianList.get(i).getState();
                    break;
                }
            }
        }

        return state;
    }

    public void drawSnake() {
        bodyList.clear();
        canvas = sfh.lockCanvas();
        if (canvas != null) {
            for (int i = 0; i < 8; i++) {
                bodyList.add(new Body(canvas, paint, x, y, bodyR, state));
                y = y - 2 * bodyR;
            }
            sfh.unlockCanvasAndPost(canvas);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (width / 7 * 3 <= event.getX() && event.getX() <= width / 7 * 4
                    && height - width / 7 <= event.getY() && event.getY() <= height) {//下
                //Toast.makeText(context, "xia", Toast.LENGTH_SHORT).show();
                jieDianList.add(new JieDian(x + 2 * bodyR, y, 2));

            } else if (width / 7 * 3 <= event.getX() && event.getX() <= width / 7 * 4
                    && height - width / 7 * 3 <= event.getY() && event.getY() <= height - width / 7 * 2) {//shang
                //Toast.makeText(context, "shang", Toast.LENGTH_SHORT).show();
                jieDianList.add(new JieDian(x + 2 * bodyR, y, 1));

            } else if (width / 7 * 4 <= event.getX() && event.getX() <= width / 7 * 5
                    && height - width / 7 * 2 <= event.getY() && event.getY() <= height - width / 7) {//you
                jieDianList.add(new JieDian(x, y - 2 * bodyR, 4));
                //Toast.makeText(context, "you", Toast.LENGTH_SHORT).show();

            } else if (width / 7 * 2 <= event.getX() && event.getX() <= width / 7 * 3
                    && height - width / 7 * 2 <= event.getY() && event.getY() <= height - width / 7) {//zuo
                //Toast.makeText(context, "zuo", Toast.LENGTH_SHORT).show();
                jieDianList.add(new JieDian(x, y - 2 * bodyR, 3));
            }
        }
        return true;
    }


}
