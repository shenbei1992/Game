package com.shen.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity {
    View myview;
    EditText editText;
    LinearLayout linearLayout;
    StretchAnimation stretchanimation;
    Animation animation;
    Button button1;
    Button button2;

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(200);
                while (true){

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t.start();
        Button button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        editText = (EditText) findViewById(R.id.editText);

        myview = findViewById(R.id.view);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("")) {
                    myToast.showPopupWindow(MainActivity.this, "请输入内容！", myview, 0);
                } else {
                    myToast.showPopupWindow(MainActivity.this, editText.getText().toString(), myview, 0);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (linearLayout.getVisibility() == View.GONE) {
                    zhankai(linearLayout);
                } else if (linearLayout.getVisibility() == View.VISIBLE) {
                    shousuo(linearLayout);
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void shousuo(LinearLayout view) {
        animation = (Animation) AnimationUtils.loadAnimation(MainActivity.this, R.anim.shousuo_anim);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void zhankai(LinearLayout view) {
        animation = (Animation) AnimationUtils.loadAnimation(MainActivity.this, R.anim.zhankai_anim);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
