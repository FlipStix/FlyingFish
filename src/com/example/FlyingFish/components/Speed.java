package com.example.FlyingFish.components;

/**
 * Created by Esperanza on 11/14/2014.
 */
public class Speed {

    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = -1;
    public static final int DIRECTION_DOWN = 1;

    private float xVelocity = 1;
    private float yVelocity = 1;

    private int xDirection = DIRECTION_RIGHT;
    private int yDirection = DIRECTION_DOWN;


    public Speed(){
        this.xVelocity = 5;
        this.yVelocity = 5;
    }

    public Speed(float xVelocity, float yVelocity){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public Speed(float xVelocity, float yVelocity, int xDirection, int yDirection){
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }


    public float getxVelocity() {
        return xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void togglexDirection(){
        xDirection = xDirection * -1;
    }
    public void toggleyDirection(){
        yDirection = yDirection * -1;
    }

}
