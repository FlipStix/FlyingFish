package com.example.FlyingFish.screen;

import android.graphics.*;
import android.view.Menu;

/**
 * Created by Esperanza on 10/12/2014.
 */
public class MenuOption {
    private String tittle;
    private Rect rect;
    private boolean isPressed;
    private Point position;
    private float fontSize;
    private Rect textBounds;
    private int rectWidth;
    private int rectHeight;

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public void setPosition(Point position) {
        this.position = position;
        calculateRect();
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
        calculateRect();
    }

    public String getTittle() {

        return tittle;
    }

    public Rect getRect() {
        return rect;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public Point getPosition() {
        return position;
    }

    public float getFontSize() {
        return fontSize;
    }

    public Rect getTextBounds() {
        return textBounds;
    }

    public void setTextBounds(Rect textBounds) {
        this.textBounds = textBounds;
    }

    public void setRectWidth(int rectWidth) {
        this.rectWidth = rectWidth;
        calculateRect();
    }

    public void setRectHeight(int rectHeight) {
        this.rectHeight = rectHeight;
        calculateRect();
    }




    public MenuOption(){
        this.tittle = "Option";
        this.isPressed = false;
        this.position = new Point(0,0);
        this.fontSize = 12f;
        this.rect = new Rect();
        this.textBounds = new Rect();
        this.rectWidth = -1;
        this.rectHeight = -1;
        calculateRect();
    }

    private void calculateRect(){
        Paint paint = new Paint();
        paint.setTextSize(this.fontSize);

        paint.getTextBounds(tittle, 0, tittle.length(), textBounds);
        int vPadding, hPadding;
        if(rectHeight == -1)
            vPadding = (int) this.fontSize /2;
        else
            vPadding = (rectHeight - textBounds.height()) / 2;
        if(rectWidth == -1)
            hPadding = (int) this.fontSize /2;
        else
            hPadding = (rectWidth - textBounds.width()) / 2;


        int left = position.x  - (textBounds.width()/2) - hPadding;
        int right = position.x + (textBounds.width()/2) + hPadding;
        int top = position.y  - (textBounds.height()/2) - vPadding;
        int bottom =  position.y  + (textBounds.height()/2) + vPadding;

        rect = new Rect(left, top, right, bottom);
    }

    public void draw(Canvas canvas, Paint fontPaint, Paint rectPaint){
        canvas.drawRect(rect, rectPaint);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(5f);
        rectPaint.setColor(Color.LTGRAY);
        canvas.drawRect(rect, rectPaint);
        canvas.drawText(tittle, position.x - textBounds.exactCenterX(),
                position.y - textBounds.exactCenterY(), fontPaint);

    }
}
