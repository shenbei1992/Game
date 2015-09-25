package com.shen.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by shen on 15/9/21.
 */

public class MyDialog extends SurfaceView implements SurfaceHolder.Callback {

    int x = 100;
    int cX = 500;
    int cY = 800;
    int j = 0;//判断当前圆圈的的大小
    int witch = 0;
    int heigth = 0;
    Paint paint;
    Paint paint1;
    Paint paint2;
    int style = 0;
    int style1 = 0;
    int style2 = 0;
    private SurfaceHolder sfh;

    Boolean isGo = true;


    public MyDialog(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        //paint.setAlpha(90);//设置透明度

        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        //paint1.setAlpha(90);

        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        //paint2.setAlpha(90);


    }

    public MyDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyDialog(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    protected void myDraw(int i) {
        Canvas canvas = sfh.lockCanvas();
        canvas.drawRGB(255, 255, 255);
        canvas.drawCircle(x, 100, 30, paint);

        if (i == 1) {
            canvas.drawCircle(cX, cY, 300, paint);
            canvas.drawCircle(cX, cY, style1, paint1);
            canvas.drawCircle(cX, cY, style2, paint2);
            canvas.drawCircle(cX, cY, style, paint);
        } else if (i == 2) {
            canvas.drawCircle(cX, cY, 300, paint1);
            canvas.drawCircle(cX, cY, style2, paint2);
            canvas.drawCircle(cX, cY, style, paint);
            canvas.drawCircle(cX, cY, style1, paint1);
        } else if (i == 3) {
            canvas.drawCircle(cX, cY, 300, paint2);
            canvas.drawCircle(cX, cY, style, paint);
            canvas.drawCircle(cX, cY, style1, paint1);
            canvas.drawCircle(cX, cY, style2, paint2);
        } else if (i == 0) {
            canvas.drawCircle(cX, cY, style, paint);
            canvas.drawCircle(cX, cY, style1, paint1);
            canvas.drawCircle(cX, cY, style2, paint2);
        }
        sfh.unlockCanvasAndPost(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        cX = (int) event.getX();
//        cY = (int) event.getY();
//        myDraw(j);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        witch = this.getWidth();
        heigth = this.getHeight();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runWhere();

                    if (isGo) {
                        style = style + 5;
                        if (i >= 20) {
                            style1 = style1 + 5;
                        }
                        if (i >= 40) {
                            style2 = style2 + 5;
                        }
                        if (style == 300) {
                            style = 0;
                            j = 1;
                        }
                        if (style1 == 300) {
                            style1 = 0;
                            j = 2;
                        }
                        if (style2 == 300) {
                            style2 = 0;
                            j = 3;
                        }

                        x = x + 10;
                        if (x == 500) {
                            x = 100;
                        }
                        i++;
                        myDraw(j);
                    }

                }
            }
        });
        thread.start();
    }


    int zhuantai = 4;
    public void runWhere() {

        if (cX + 300 == witch) {
            if (zhuantai == 1) {
                zhuantai = 2;
            } else if (zhuantai == 4) {
                zhuantai = 3;
            }
        } else if (cY + 300 == heigth) {
            if (zhuantai == 1) {
                zhuantai = 4;
            } else if (zhuantai == 2) {
                zhuantai = 3;
            }
        } else if (cX == 300) {
            if (zhuantai == 2) {
                zhuantai = 1;
            } else if (zhuantai == 3) {
                zhuantai = 4;
            }

        } else if (cY == 300) {
            if (zhuantai == 3) {
                zhuantai = 2;
            } else if (zhuantai == 4) {
                zhuantai = 1;
            }

        }

        if (zhuantai == 1) {
            cX = cX + 5;
            cY = cY + 5;
        } else if (zhuantai == 2) {
            cX = cX - 5;
            cY = cY + 5;
        } else if (zhuantai == 3) {
            cX = cX - 5;
            cY = cY - 5;
        } else if (zhuantai == 4) {
            cX = cX + 5;
            cY = cY - 5;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isGo = false;
    }
}
