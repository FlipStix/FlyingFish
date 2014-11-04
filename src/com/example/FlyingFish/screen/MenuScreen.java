package com.example.FlyingFish.screen;

import android.content.Context;
import android.graphics.Canvas;
import com.example.FlyingFish.helper.Delegate;

/**
 * Created by Esperanza on 10/9/2014.
 */
public abstract class MenuScreen extends Screen {

    protected MenuOption[] menuOptions;
    protected String screenTitle;

    public MenuScreen(Context context, Delegate delegate){
        super(context, delegate);
    }

    protected void drawBackground(){

    }

    protected void updateOptions(){

    }

}
