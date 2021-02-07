package com.samsung.surfaceexample1701;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    int count = 0;
    Paint paint = new Paint();

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    setColorFromCount();
                    Canvas c = getHolder().lockCanvas();
                    c.drawRect(0, 0, c.getWidth(), c.getHeight(), paint);
                    //do smth
                    getHolder().unlockCanvasAndPost(c);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void setColorFromCount() {
        switch (count) {
            case 0:
                paint.setColor(Color.RED);
                break;
            case 1:
                paint.setColor(Color.GREEN);
                break;
            case 2:
                paint.setColor(Color.BLUE);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        count = (count + 1) % 3;
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}


