package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Background extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private   int index = 0;

    public Background() {
        this(0,FrameConstant.FRAME_HEIGHT-ImageMap.get("bg01").getHeight(null)+1000,
                ImageMap.get("bg01"));

    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void move() {
        index++;
        if(index < 600){
            setY(getY() + FrameConstant.FRAME_SPEED * 2);
        }else if(index >= 600 && index <= 1200){
            setY(getY() - FrameConstant.FRAME_SPEED * 2);
        }else{
            index = 0;
        }

    }

    @Override
    public void draw(Graphics g) {
        move();

        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


    }
}
