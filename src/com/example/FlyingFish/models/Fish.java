package com.example.FlyingFish.models;

import android.content.Context;
import android.graphics.*;
import com.example.FlyingFish.R;
import com.example.FlyingFish.body.Entity;
import com.example.FlyingFish.components.Speed;

import java.util.Random;

/**
 * Created by Esperanza on 11/8/2014.
 */
public class Fish extends Entity {

    private Bitmap bitmap;
    private int type;
    private boolean isSelected;
    private double startTime;
    private boolean isAlive;
    private Rect swimArea;
    private Speed speed;
    private int timeLimit;


    public Fish(Context context, Rect swimArea){
        super();
        Random randGen = new Random();
        this.type = randGen.nextInt(2);
        if(type == SALT_WATER)
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fish_2);
        else
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fish_1);
        setSwimArea(swimArea);
        this.position = new Point(swimArea.centerX(),50);
        this.isSelected = false;
        speed = new Speed(randGen.nextInt(5) +3,randGen.nextInt(5)+3);

        if(randGen.nextInt(2) == 1)
           speed.setxDirection(1);
        else
            speed.setxDirection(-1);
        if(randGen.nextInt(2) == 1)
            speed.setyDirection(1);
        else
            speed.setyDirection(-1);

        this.isAlive = true;
        timeLimit = 5;
        startTime = System.nanoTime();
    }

    public Fish(Bitmap bitmap, Point position, int type, Rect swimArea){
        super();
        this.bitmap = bitmap;
        this.position = position;
        this.type = type;
        this.isSelected = false;
        speed = new Speed(7,7);
        setSwimArea(swimArea);

    }
    @Override
     public void initialize() {
    }

    public void setSwimArea(Rect swimArea){
        this.swimArea = new Rect();
        this.swimArea.set(swimArea.left + (bitmap.getWidth()/2),
                swimArea.top + (bitmap.getHeight()/2),
                swimArea.right - (bitmap.getWidth()/2),
                swimArea.bottom - (bitmap.getHeight()/2));
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }


    @Override
    public void update() {
        double sec5 = 1000000000.0 * timeLimit;
        if(System.nanoTime() - startTime > sec5){
            isAlive = false;
        }

        if(!isSelected && isAlive){
            position.x += (speed.getxVelocity() * speed.getxDirection());
            position.y += (speed.getyVelocity() * speed.getyDirection());

            // checks collisions with the wall
            if(speed.getxDirection() == Speed.DIRECTION_LEFT && position.x <= swimArea.left)
                speed.togglexDirection();
            if(speed.getxDirection() == Speed.DIRECTION_RIGHT && position.x >= swimArea.right)
                speed.togglexDirection();
            if(speed.getyDirection() == Speed.DIRECTION_UP && position.y <= swimArea.top)
                speed.toggleyDirection();
            if(speed.getyDirection() == Speed.DIRECTION_DOWN &&position.y >= swimArea.bottom)
                speed.toggleyDirection();

        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (isAlive) {
            if (isSelected)
                canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 1.75),
                                (int) (bitmap.getHeight() * 1.75), false),
                        position.x - bitmap.getWidth() / 2, position.y - bitmap.getHeight() / 2, null
                );
            else
                canvas.drawBitmap(bitmap, position.x - bitmap.getWidth() / 2, position.y - bitmap.getHeight() / 2, null);


            Paint timerPaint = new Paint();
            timerPaint.setColor(Color.RED);
            timerPaint.setStyle(Paint.Style.FILL);
            timerPaint.setTextSize(bitmap.getHeight()/2);
            canvas.drawText(String.valueOf( timeLimit - (System.nanoTime() - startTime)/1000000000.0).substring(0,1)
                    ,position.x, position.y, timerPaint);
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean overlaps(Entity entity) {
        return false;
    }

    public boolean overlaps(int x, int y){
        if(x < position.x + (bitmap.getWidth()/2) && x > position.x - (bitmap.getWidth()/2)){
            if(y < position.y + (bitmap.getHeight()/2) && y > position.y - (bitmap.getHeight()/2))
                return true;
        }
        return false;
    }
}
