package com.shen.game.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

/**
 * Created by shen on 15/9/28.
 */
public class Snake {
    private List<JieDian> jieDianList;
    private List<Body> bodyList;


    public List<JieDian> getJieDianList() {
        return jieDianList;
    }

    public void setJieDianList(List<JieDian> jieDianList) {
        this.jieDianList = jieDianList;
    }

    public List<Body> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<Body> bodyList) {
        this.bodyList = bodyList;
    }

    public Snake(List<JieDian> jieDianList, List<Body> bodyList) {
        this.jieDianList = jieDianList;
        this.bodyList = bodyList;
    }

    public void draw() {
        for (Body body:bodyList){
            body.draw();
        }
    }

}
