package com.example.FlyingFish.screen;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.example.FlyingFish.helper.Delegate;

/**
 * Created by Esperanza on 10/8/2014.
 */
public abstract class Screen {

    public static final int MAIN_MENU = 0;
    public static final int GAME_SCREEN = 1;
    public static final int GAME_OVER_SCREEN = 2;
    //public static final int SETTINGS_MENU = 2;

    protected Delegate delegate;
    protected Context context;
    protected DisplayMetrics metrics;

    public Screen(Context context, Delegate delegate){
        this.delegate = delegate;
        this.context = context;
    }
    abstract public void initialize();
    abstract public void handleTouchEvent(MotionEvent event);
    abstract public void render(Canvas canvas);
    abstract public void update();
    abstract public void destroy();
}
