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
public class TempGameScreen extends Screen {

    public TempGameScreen(Context context, Delegate delegate){
        super(context, delegate);
        initialize();
    }

    private MenuOption backButton;

    @Override
    public void initialize(){
        metrics = context.getResources().getDisplayMetrics();
        backButton = new MenuOption();
        backButton.setFontSize(24f);
        backButton.setPosition(new Point((backButton.getRect().width() /2) + 10,(backButton.getRect().height()/2) + 10));
        backButton.setTittle("Back");
    }

    @Override
    public void handleTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            //isSelected = true;
            int xLocation = (int) event.getX();
            int yLocation = (int) event.getY();

            if (yLocation >= backButton.getRect().top && yLocation <= backButton.getRect().bottom) {
                if (xLocation >= backButton.getRect().left && xLocation <= backButton.getRect().right)
                    backButton.setPressed(true);
                else {
                    backButton.setPressed(false);
                }
            } else {
                backButton.setPressed(false);
            }
        }

    }

    @Override
    public void render(Canvas canvas){
        canvas.drawColor(Color.GREEN);

        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(100f);

        canvas.drawText("Game Screen", metrics.widthPixels /5 , metrics.heightPixels / 5, paint);

        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.BLUE);

        Paint fontPaint = new Paint();
        fontPaint.setColor(Color.BLACK);
        fontPaint.setStyle(Paint.Style.FILL);
        fontPaint.setTextSize(backButton.getFontSize());
        backButton.draw(canvas, fontPaint, rectPaint);
    }

    @Override
    public void update(){
        if(backButton.isPressed()) {
            backButton.setPressed(false);
            delegate.change(Screen.MAIN_MENU);
        }
    }

    @Override
    public void destroy(){

    }
}
