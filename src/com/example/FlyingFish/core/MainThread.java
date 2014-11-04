package com.example.FlyingFish.core;

import android.view.SurfaceHolder;
import android.graphics.Canvas;
import com.example.FlyingFish.MainPanel;

/**
 * Created by Esperanza on 10/7/2014.
 */
public class MainThread extends Thread {

    /**flag to hold the game state.*/
    private boolean running;

    /**
     * Sets the state of the thread.
     * @param running
     */
    public void setRunning(boolean running){
        this.running = running;
    }

    private SurfaceHolder surfaceHolder;
    private MainPanel screenManager;

    /**
     * Constructor
     * @param sh
     * @param sm
     */
    public MainThread(SurfaceHolder sh, MainPanel sm){
        super();
        this.surfaceHolder = sh;
        this.screenManager = sm;
    }

    /**
     * Main game loop.
     */
    public void run(){
        Canvas canvas;

        while(running){
            canvas = null;

            try{
                //TODO: Look this shit up.
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){

                    this.screenManager.update();
                    this.screenManager.render(canvas);
                }
            } finally{
                if(canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

}
