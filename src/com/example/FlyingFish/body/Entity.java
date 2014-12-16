package com.example.FlyingFish.body;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Esperanza on 11/4/2014.
 */
public abstract class Entity {
    public static final int FRESH_WATER = 0;
    public static final int SALT_WATER = 1;

    protected Point position;

    public Entity(){
        position = new Point(0,0);
    }

    abstract public Point getPosition();
    abstract public void setPosition(Point position);

    abstract public void initialize();
    abstract public void update();
    abstract public void draw(Canvas canvas);
    abstract public void destroy();
    abstract public boolean overlaps(Entity entity);
}
