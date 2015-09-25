package com.shen.game.dazhuankuai;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by shen on 15/9/23.
 */
public class ZhuanKuai {
    private Canvas canvas;
    private Paint paint;
    private int oneX;
    private int oneY;
    private int twoX;
    private int twoY;


    public ZhuanKuai(Canvas canvas, Paint paint, int oneX, int oneY, int twoX, int twoY) {
        this.canvas = canvas;
        this.paint = paint;
        this.oneX = oneX;
        this.oneY = oneY;
        this.twoX = twoX;
        this.twoY = twoY;
    }

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

    public int getOneX() {
        return oneX;
    }

    public void setOneX(int oneX) {
        this.oneX = oneX;
    }

    public int getOneY() {
        return oneY;
    }

    public void setOneY(int oneY) {
        this.oneY = oneY;
    }

    public int getTwoX() {
        return twoX;
    }

    public void setTwoX(int twoX) {
        this.twoX = twoX;
    }

    public int getTwoY() {
        return twoY;
    }

    public void setTwoY(int twoY) {
        this.twoY = twoY;
    }


    public void draw() {
        canvas.drawRect(oneX, oneY, twoX, twoY, paint);
    }


}
