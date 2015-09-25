package com.shen.game.dazhuankuai;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by shen on 15/9/23.
 */
public class Ball {
    private Canvas canvas;
    private Paint paint;
    private int x;
    private int y;
    private int r;

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public Ball(Canvas canvas, Paint paint, int x, int y, int r) {
        this.canvas = canvas;
        this.paint = paint;
        this.x = x;
        this.y = y;
        this.r = r;
        draw();
    }

    public void draw(){
        canvas.drawCircle(x,y,r,paint);
    }
}
