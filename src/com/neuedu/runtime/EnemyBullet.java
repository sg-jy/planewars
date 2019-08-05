package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;


    private int speed = FrameConstant.FRAME_SPEED * 5;

    private int type;


    public EnemyBullet() {
        this(0, 0, ImageMap.get("epb02"),1);

    }

    public EnemyBullet(int x, int y, Image image,int type) {
        super(x, y);
        this.image = image;
        this.type = type;



    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    public void borderCheck() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);

        }
    }

    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }






}
