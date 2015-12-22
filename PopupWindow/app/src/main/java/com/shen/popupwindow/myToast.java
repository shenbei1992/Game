package com.shen.popupwindow;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shen on 15/10/10.
 */
public class myToast {


    static TextView toast;

    static void showPopupWindow(Context context, String neirong, View view, final int time) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.pop_window, null);
        // 设置按钮的点击事件
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        toast = (TextView) contentView.findViewById(R.id.mytoast);
        toast.setText(neirong);
        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.abc_ab_share_pack_mtrl_alpha));

        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        //popupWindow.showAtLocation(myview,0,0,0);


        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //handler处理消息
                if(msg.what==time){
                    popupWindow.dismiss();
                }else{

                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = i++;
                handler.sendMessage(msg);
            }
        },1000,1000);

    }
}
