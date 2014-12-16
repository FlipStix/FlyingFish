package com.example.FlyingFish.models;

import android.graphics.*;
import android.util.DisplayMetrics;
import android.view.Display;
import com.example.FlyingFish.body.Entity;

import java.util.ArrayList;

/**
 * Created by Esperanza on 11/13/2014.
 */
public class Tank extends Entity {

    private DisplayMetrics metrics;

    public int getFishCount() {
        return fishCount;
    }

    private int fishCount;
    private Rect tankBounds;
    private int type;

    public Tank(DisplayMetrics metrics, int type){
        super();
        this.metrics = metrics;
        this.type = type;
        initialize();
    }


    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void initialize() {
        fishCount = 0;
        int left, right;
        if(this.type == FRESH_WATER){
            left = 5;
            right = (int)(metrics.widthPixels/2) - 5;
        }
        else {
            left = (int)(metrics.widthPixels / 2) + 5;
            right = (int)(metrics.widthPixels - 5);
        }
        tankBounds = new Rect(left, (int)(metrics.heightPixels* 0.6), right, metrics.heightPixels - 5);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        Paint tankPaint = new Paint();
        if(type == Entity.FRESH_WATER)
            tankPaint.setARGB(150, 64, 194, 206);
        else
            tankPaint.setARGB(150, 47, 210, 123);

        canvas.drawRect(tankBounds, tankPaint);

        Paint countPaint = new Paint();
        countPaint.setColor(Color.DKGRAY);
        countPaint.setTextSize(tankBounds.height()/5);
        countPaint.setStyle(Paint.Style.FILL);


        canvas.drawText("" +  fishCount, tankBounds.exactCenterX(), tankBounds.exactCenterY(), countPaint);
    }

    @Override
    public void destroy() {

    }
    @Override
    public boolean overlaps(Entity entity){
        return false;
    }

    public int checkTank(ArrayList<Fish> entities, int killCount) {
        for(int i = 0; i < entities.size(); i++){
            if(tankBounds.contains(entities.get(i).getPosition().x, entities.get(i).getPosition().y)
                    && !entities.get(i).isSelected()){
                if(entities.get(i).getType() == type) {
                    fishCount++;
                    entities.remove(i);
                    return killCount;
                }
                else{
                    entities.remove(i);
                    return (killCount + 1);
                }
            }
        }
        return killCount;
    }

}
