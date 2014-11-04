package com.example.FlyingFish;
import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.FlyingFish.core.MainThread;
import com.example.FlyingFish.helper.Delegate;
import com.example.FlyingFish.screen.GameScreen;
import com.example.FlyingFish.screen.MainMenuScreen;
import com.example.FlyingFish.screen.Screen;
import com.example.FlyingFish.screen.TempGameScreen;

/**
 * Created by Esperanza on 10/7/2014.
 *
 * Class that has access to the device's  screen and manages the screen that is rendering
 */
public class MainPanel extends SurfaceView implements SurfaceHolder.Callback{


    private MainThread thread;
    private Screen[] screens;
    private Screen currentScreen;

    /**
     * Constructor. initializes th thread and creates the screen.
     * @param context allows access to application specific resources
     */
    public MainPanel(Context context){
        super(context);
        getHolder().addCallback(this);
        Delegate delegate = new Delegate() {
            @Override
            public void change(int id) {
                switchScreens(id);
            }
        };
        thread = new MainThread(getHolder(), this);
        screens = new Screen[]{new MainMenuScreen(context, delegate),
                               new TempGameScreen(context, delegate)};
        currentScreen = screens[0];
        setFocusable(true);
    }


    /**
     * Starts the thread when the surfaces has been created.
     * @param surfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }


    /**
     *
     * @param surfaceHolder
     * @param i
     * @param i2
     * @param i3
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    /**
     * Ends the processes that is running.
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    /**
     * Handles touch events.
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentScreen.handleTouchEvent(event);
        return true;
    }

    /**
     * Updates the screen information.
     */
    public void update(){
        currentScreen.update();
    }

    /**
     * Renders the current screen.
     * @param canvas
     */
    public void render(Canvas canvas) {
        currentScreen.render(canvas);
    }

    public void switchScreens(int id){
        if(id < screens.length && id > -1)
            currentScreen = screens[id];
    }
}
