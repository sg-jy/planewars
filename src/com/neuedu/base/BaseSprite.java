package com.neuedu.base;

import java.awt.*;
import java.util.Random;

public abstract class BaseSprite {

    private  int x;
    private  int y;

    public BaseSprite() {


    }

    public BaseSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public static Random random = new Random();

    public boolean sleep = false;

    public static int pass = 0;



}
