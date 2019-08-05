package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.List;

/**
 * 道具
 */
public class AddBUlletProp extends BaseSprite implements Moveable, Drawable {
    private Image image;

    private int speed = FrameConstant.FRAME_SPEED * 2;


    public AddBUlletProp() {
        this( (FrameConstant.FRAME_WIDTH- ImageMap.get("addBullet").getWidth(null))/2,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("addBullet").getHeight(null),ImageMap.get("addBullet"));
    }

    public AddBUlletProp(int x, int y,Image image ) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {
        move();
        borderCheck();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }

    @Override
    public void move() {
        setY(getY() + speed);

    }

    //边缘判断
    public void borderCheck() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.addBUlletPropList.remove(this);

        }
    }


    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }



}
