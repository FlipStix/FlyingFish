package com.example.FlyingFish.screen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import com.example.FlyingFish.helper.Delegate;

/**
 * Created by Esperanza on 11/2/2014.
 */
public class MainMenuScreen extends MenuScreen{

    public MainMenuScreen(Context context, Delegate delegate){
        super(context, delegate);
        initialize();
    }

    @Override
    public void initialize(){
        metrics = context.getResources().getDisplayMetrics();
        screenTitle = "Flying Fish";

        menuOptions = new MenuOption[2];
        for(int i = 0; i < menuOptions.length; i++){
            menuOptions[i] = new MenuOption();
            menuOptions[i].setFontSize(72f);
            menuOptions[i].setPosition(new Point(300, 100 + 125*(i+1)));
        }
        menuOptions[0].setTittle("Play");
        menuOptions[1].setTittle("Quit");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void handleTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){

            //isSelected = true;
            int xLocation = (int)event.getX();
            int yLocation = (int)event.getY();

            for(int i = 0; i < menuOptions.length; i++) {
                if (yLocation >= menuOptions[i].getRect().top && yLocation <= menuOptions[i].getRect().bottom) {
                    if (xLocation >= menuOptions[i].getRect().left && xLocation <= menuOptions[i].getRect().right)
                        menuOptions[i].setPressed(true);
                    else{
                        menuOptions[i].setPressed(false);
                    }
                }
                else{
                    menuOptions[i].setPressed(false);
                }
            }
        }
    }

    @Override
    public void render(Canvas canvas){

        // sets the background colour.
        canvas.drawColor(Color.DKGRAY);

        // sets the text attributes
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(menuOptions[0].getFontSize());

        Paint rectPaint = new Paint();

        canvas.drawText(screenTitle, metrics.widthPixels / 5, metrics.heightPixels / 5, paint);

        for(int i = 0; i < menuOptions.length; i++){
            if(menuOptions[i].isPressed())
                rectPaint.setColor(Color.YELLOW);
            else
                rectPaint.setColor(Color.BLACK);

            menuOptions[i].draw(canvas, paint, rectPaint);
        }

    }

    @Override
    public void update(){

        if(menuOptions[0].isPressed()){
            menuOptions[0].setPressed(false);
            delegate.change(Screen.GAME_SCREEN);
        }
        if(menuOptions[1].isPressed())
            System.exit(0);
    }
}
