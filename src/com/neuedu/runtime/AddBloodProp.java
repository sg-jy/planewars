package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class AddBloodProp extends BaseSprite implements Moveable, Drawable {
    private Image image;

    private int speed = FrameConstant.FRAME_SPEED * 2;


    public AddBloodProp() {
        this( (FrameConstant.FRAME_WIDTH- ImageMap.get("blood").getWidth(null))/2 ,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("blood").getHeight(null),ImageMap.get("blood"));
    }

    public AddBloodProp(int x, int y,Image image ) {
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
            gameFrame.addBloodPropListdB.remove(this);

        }
    }






    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }


}
