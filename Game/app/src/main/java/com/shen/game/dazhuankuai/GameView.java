package com.shen.game.dazhuankuai;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shen on 15/9/23.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint paint;
    private Paint button;
    private Paint button1;
    private Canvas canvas;
    private SurfaceHolder sfh;
    private Boolean over = false;//判断结束 绘制结束提示
    private Boolean stop = true;//判断已经绘制结束提示，保证结束绘制仅一次

    public GameView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.BLUE);

        button = new Paint();
        button.setColor(Color.RED);

        button1 = new Paint();
        button1.setColor(Color.YELLOW);
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
        heigth = this.getHeight();
        x = this.getWidth() / 2;
        y = this.getHeight() - 300;
        gunX = x - 150;
        zhuankuai();
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {

                        Thread.sleep(2);
                        if (!over) {
                            myDraw();
                        } else {
                            if (stop) {
                                if (num == zhuanKuaiList.size()) {
                                    winDraw();
                                    over = true;
                                } else {
                                    overDraw();
                                }
                                stop = false;
                            }
                        }
                        if (num == zhuanKuaiList.size()) {
                            over = true;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    int width;
    int heigth;
    int x;
    int y;
    int state = 2;
    int gunX;
    int speed = 10;
    Ball ball;
    int rownum = 6;//砖块的总行数
    int num = 0;//打掉的砖块数
    List<ZhuanKuai> zhuanKuaiList;
    int juliOne;//球心到砖块起点坐标的距离
    int juliTwo;
    int juliThree;
    int juliFour;

    public void myDraw() {
        canvas = sfh.lockCanvas();
        if (canvas != null) {
            boolean removed = false;
            canvas.drawRGB(250, 250, 210);
            //canvas.drawCircle(x, y, 30, paint);
            for (int i = 0; i < zhuanKuaiList.size(); i++) {
                juliOne = zhuanKuaiList.get(i).getOneX() - x;
                juliTwo = zhuanKuaiList.get(i).getOneY() - y;
                juliThree = zhuanKuaiList.get(i).getTwoX() - x;
                juliFour = zhuanKuaiList.get(i).getTwoY() - y;
                zhuanKuaiList.get(i).draw();
                //Log.e("shen", zhuankai.getTwoY() + "");
                if (x + 30 == zhuanKuaiList.get(i).getOneX() && y >= zhuanKuaiList.get(i).getOneY() && y < zhuanKuaiList.get(i).getTwoY()) {
                    if (state == 1) {
                        state = 2;
                    } else if (state == 4) {
                        state = 3;
                    }
                    zhuanKuaiList.get(i).setOneX(0);
                    zhuanKuaiList.get(i).setOneY(0);
                    zhuanKuaiList.get(i).setTwoX(0);
                    zhuanKuaiList.get(i).setTwoY(0);

                    num = num + 1;
                } else if (y + 30 == zhuanKuaiList.get(i).getOneY()) {
                    int a = i / 6 * 6;
                    for (int j = a; j < a + 6; j++) {
                        Log.e("shen", j + "");
                        if (x >= zhuanKuaiList.get(j).getOneX() && x < zhuanKuaiList.get(j).getTwoX()) {
                            if (state == 1) {
                                state = 4;
                            } else if (state == 2) {
                                state = 3;
                            }

                            zhuanKuaiList.get(j).setOneX(0);
                            zhuanKuaiList.get(j).setOneY(0);
                            zhuanKuaiList.get(j).setTwoX(0);
                            zhuanKuaiList.get(j).setTwoY(0);
                            num = num + 1;
                        }
                    }
                } else if (x - 30 == zhuanKuaiList.get(i).getTwoX() && y >= zhuanKuaiList.get(i).getOneY() && y < zhuanKuaiList.get(i).getTwoY()) {
                    if (state == 2) {
                        state = 1;
                    } else if (state == 3) {
                        state = 4;
                    }
                    zhuanKuaiList.get(i).setOneX(0);
                    zhuanKuaiList.get(i).setOneY(0);
                    zhuanKuaiList.get(i).setTwoX(0);
                    zhuanKuaiList.get(i).setTwoY(0);
                    num = num + 1;
                } else if (y - 30 == zhuanKuaiList.get(i).getTwoY()) {
                    int a = i / 6 * 6;
                    for (int j = a; j < a + 6; j++) {
                        Log.e("shen", j + "");
                        if (x >= zhuanKuaiList.get(j).getOneX() && x < zhuanKuaiList.get(j).getTwoX()) {
                            if (state == 3) {
                                state = 2;
                            } else if (state == 4) {
                                state = 1;
                            }

                            zhuanKuaiList.get(j).setOneX(0);
                            zhuanKuaiList.get(j).setOneY(0);
                            zhuanKuaiList.get(j).setTwoX(0);
                            zhuanKuaiList.get(j).setTwoY(0);
                            num = num + 1;
                        }
                    }

                } else if (juliOne * juliOne + juliTwo * juliTwo <= 30 * 30 ||
                        juliThree * juliThree + juliTwo * juliTwo <= 30 * 30 ||
                        juliOne * juliOne + juliFour * juliFour <= 30 * 30 ||
                        juliThree * juliThree + juliFour * juliFour <= 30 * 30) {
                    Log.e("碰到了砖块的角","碰到了砖块的角");

                    if (state == 1) {
                        state = 4;
                    } else if (state == 2) {
                        state = 3;
                    } else if (state == 3) {
                        state = 2;
                    } else if (state == 4) {
                        state = 1;
                    }
                    zhuanKuaiList.get(i).setOneX(0);
                    zhuanKuaiList.get(i).setOneY(0);
                    zhuanKuaiList.get(i).setTwoX(0);
                    zhuanKuaiList.get(i).setTwoY(0);
                    num = num + 1;

                }
            }

            setState();
            ball = new Ball(canvas, paint, x, y, 30);
            runWhere();
            canvas.drawRect(gunX, heigth - 300, gunX + 300, heigth - 290, paint);
            canvas.drawRect(0, heigth - 150, width / 2, heigth, button);
            canvas.drawRect(width / 2, heigth - 150, width, heigth, button1);
            sfh.unlockCanvasAndPost(canvas);
        }
    }


    public void zhuankuai() {
        zhuanKuaiList = new ArrayList<ZhuanKuai>();

        for (int j = 0; j < 50 * rownum; j = j + 50) {
            for (int i = 0; i < width; i = i + width / 6) {
                Paint paint = new Paint();
                int a = 1 + (int) (Math.random() * 10);
                switch (a) {
                    case 1:
                        paint.setColor(Color.YELLOW);
                        break;
                    case 2:
                        paint.setColor(Color.BLUE);
                        break;
                    case 3:
                        paint.setColor(Color.RED);
                        break;
                    case 4:
                        paint.setColor(Color.GREEN);
                        break;
                    case 5:
                        paint.setColor(Color.WHITE);
                        break;
                    case 6:
                        paint.setColor(Color.BLACK);
                        break;
                    case 7:
                        paint.setColor(Color.CYAN);
                        break;
                    case 8:
                        paint.setColor(Color.GRAY);
                        break;
                    case 9:
                        paint.setColor(Color.MAGENTA);
                        break;
                    case 10:
                        paint.setColor(Color.DKGRAY);
                        break;
                }
                canvas = sfh.lockCanvas();
                zhuanKuaiList.add(new ZhuanKuai(canvas, paint, i, j, i + width / 6, j + 50));
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void overDraw() {
        canvas = sfh.lockCanvas();
        paint.setTextSize(100);
        if (canvas != null) {
            canvas.drawText("GAME OVER", this.getWidth() / 2 - 280, this.getHeight() / 2, paint);
            sfh.unlockCanvasAndPost(canvas);
        }
    }

    public void winDraw() {
        canvas = sfh.lockCanvas();
        paint.setTextSize(100);
        if (canvas != null) {
            canvas.drawText("YOU WIN", this.getWidth() / 2 - 280, this.getHeight() / 2, paint);
            sfh.unlockCanvasAndPost(canvas);
        }
    }

    public void setState() {

        if (x + 30 >= width) {
            if (state == 1) {
                state = 2;
            } else if (state == 4) {
                state = 3;
            }
        } else if (y + 30 >= heigth) {
            if (state == 1) {
                state = 4;
            } else if (state == 2) {
                state = 3;
            }
        } else if (x <= 30) {
            if (state == 2) {
                state = 1;
            } else if (state == 3) {
                state = 4;
            }

        } else if (y <= 30) {
            if (state == 3) {
                state = 2;
            } else if (state == 4) {
                state = 1;
            }

        } else if (y + 30 >= heigth - 300 && x >= gunX && x <= gunX + 300) {
            if (state == 1) {
                state = 4;
            } else if (state == 2) {
                state = 3;
            }
        } else if (y >= heigth - 300 && (x < gunX || x > gunX + 300)) {//判断游戏结束
            over = true;
            Log.e("shen", "over");
        }

    }

    public void runWhere() {
        if (state == 1) {
            x = x + speed;
            y = y + speed;
        } else if (state == 2) {
            x = x - speed;
            y = y + speed;
        } else if (state == 3) {
            x = x - speed;
            y = y - speed;
        } else if (state == 4) {
            x = x + speed;
            y = y - speed;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int clickX = (int) event.getX();
        int clickY = (int) event.getY();
        if (clickX < width / 2 && clickY > heigth - 150) {
            if (gunX <= 0) {

            } else {
                if (y + 30 == heigth - 300) {
                    if (x - 30 == 0) {

                    } else {
                        x = x - 20;
                    }
                }
                gunX = gunX - 20;
            }
        }
        if (clickX > width / 2 && clickY > heigth - 150) {
            if (gunX + 300 >= width) {

            } else {
                if (y + 30 == heigth - 300) {
                    if (x + 30 == width) {

                    } else {
                        x = x + 20;
                    }
                }
                gunX = gunX + 20;
            }
        }
        if (over) {
            x = this.getWidth() / 2;
            y = this.getHeight() - 300;
            gunX = x - 150;

            num = 0;
            zhuankuai();
            over = false;
            stop = true;
        }

        return true;
    }
}
