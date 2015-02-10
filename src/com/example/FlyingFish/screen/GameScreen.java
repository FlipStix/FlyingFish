package com.example.FlyingFish.screen;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import com.example.FlyingFish.R;
import com.example.FlyingFish.body.Entity;
import com.example.FlyingFish.helper.Delegate;
import com.example.FlyingFish.models.Fish;
import com.example.FlyingFish.models.Tank;

import java.util.ArrayList;

/**
 * Created by Esperanza on 11/2/2014.
 */
public class GameScreen extends Screen {

    public GameScreen(Context context, Delegate delegate){
        super(context, delegate);
        initialize();
    }

    private MenuOption backButton;
    private ArrayList<Fish> freeFishes;
    private Tank tankFresh;
    private Tank tankSalt;
    private Rect swimArea;
    private Rect topBar;
    private int killCount;
    private int killLimit;
    private int savedCount;
    private boolean selected;
    private double startTime;
    private double spawnTime;
    private int spawnNum;

    @Override
    public void initialize(){

        selected = false;

        metrics = context.getResources().getDisplayMetrics();
        killCount = 0;
        killLimit = 5;
        savedCount = 0;
        topBar = new Rect(0, 0, metrics.widthPixels, (int)(metrics.heightPixels * 0.08));
        backButton = new MenuOption();
        backButton.setFontSize(topBar.height()/3);
        backButton.setPosition(new Point((backButton.getRect().width() /2) + 10, topBar.centerY()));
        backButton.setTittle("Back");

        swimArea = new Rect();
        swimArea.set(0, (int)(metrics.heightPixels*0.08), metrics.widthPixels,(int)(metrics.heightPixels*0.6));

        freeFishes = new ArrayList<Fish>();
        tankFresh = new Tank(metrics, Entity.FRESH_WATER);
        tankSalt = new Tank(metrics, Entity.SALT_WATER);

        startTime = System.nanoTime();
        spawnTime = 3.0;

        spawnNum = 1;

    }

    @Override
    public void handleTouchEvent(MotionEvent event){

        int xLocation = (int) event.getX();
        int yLocation = (int) event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
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

        if(event.getAction() == MotionEvent.ACTION_MOVE && !selected){

            for(int i = freeFishes.size()-1; i > -1; i--) {
                if (freeFishes.get(i).overlaps(xLocation, yLocation)) {
                    freeFishes.get(i).setPosition(new Point(xLocation, yLocation));
                    freeFishes.get(i).setSelected(true);
                    // moves the selected to the end of the array so it gets rendered on top of other fishes.
                    freeFishes.add(freeFishes.get(i));
                    freeFishes.remove(i);
                    selected = true;
                    break;
                }
            }
        }
        for(int i = 0; i < freeFishes.size(); i++){
            if(event.getAction() == MotionEvent.ACTION_UP) {
                freeFishes.get(i).setSelected(false);
                selected = false;
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                if(freeFishes.get(i).isSelected()) {
                    Point point = new Point(xLocation, yLocation);
                    freeFishes.get(i).setPosition(point);
                }
            }
        }

    }

    @Override
    public void render(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.LTGRAY);

        Paint fontPaint = new Paint();
        fontPaint.setColor(Color.BLACK);
        fontPaint.setStyle(Paint.Style.FILL);
        fontPaint.setTextSize(backButton.getFontSize());
        backButton.draw(canvas, fontPaint, rectPaint);

        Rect kCountTextBounds = new Rect();
        String killText = "Killed: " + killCount;

        fontPaint.setColor(Color.RED);
        fontPaint.getTextBounds(killText, 0, killText.length(), kCountTextBounds);

        canvas.drawText(killText, topBar.width()-kCountTextBounds.width() - 10,topBar.exactCenterY() - kCountTextBounds.exactCenterY(), fontPaint);
        canvas.drawText("Saved: " + savedCount, topBar.width()/2, topBar.centerY(), fontPaint);

        for(int i = 0; i < freeFishes.size(); i++){
            freeFishes.get(i).draw(canvas);
        }

        tankFresh.draw(canvas);
        tankSalt.draw(canvas);

        Paint swimAreaPaint = new Paint();
        swimAreaPaint.setStyle(Paint.Style.STROKE);
        swimAreaPaint.setColor(Color.CYAN);
        swimAreaPaint.setStrokeWidth(4f);

        canvas.drawRect(swimArea, swimAreaPaint);

    }

    @Override
    public void update(){
        killCount = tankFresh.checkTank(freeFishes, killCount);
        killCount = tankSalt.checkTank(freeFishes, killCount);
        for(int i = 0; i < freeFishes.size(); i++){
            if(freeFishes.get(i).isAlive())
                freeFishes.get(i).update();
            else{
                freeFishes.remove(i);
                killCount++;
            }
        }
        if(backButton.isPressed()) {
            backButton.setPressed(false);
            delegate.change(Screen.MAIN_MENU);
        }
        if(killCount >= killLimit){
            initialize();
            delegate.change(Screen.GAME_OVER_SCREEN);
        }

        savedCount = tankFresh.getFishCount() + tankSalt.getFishCount();

        if(System.nanoTime() - startTime > 1000000000.0 * spawnTime){
            for(int i = 0; i < spawnNum; i++) {
                freeFishes.add(new Fish(context, swimArea));
            }
            startTime = System.nanoTime();
        }

    }

    @Override
    public void destroy(){

    }
}
