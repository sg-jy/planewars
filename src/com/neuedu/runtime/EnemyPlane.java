package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.FRAME_SPEED * 2;

    private Random random = new Random();
    public EnemyPlane() {
        this( (FrameConstant.FRAME_HEIGHT- ImageMap.get("ep01").getHeight(null))/2,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("ep01").getHeight(null),
                ImageMap.get("ep01"));


    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        fire();
    }

    public void fire(){
        GameFrame gameFrame =  DateStore.get("gameFrame");
        if (random.nextInt(1000) > 985){
            gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("epb01").getWidth(null)/2),
                    getY() + image.getHeight(null),
                    ImageMap.get("epb01")));
        }

    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderCheck();
    }

    public void borderCheck(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
        }
    }


    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }


    public void collisionCheck(Plane plane){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            gameFrame.enemyPlaneList.remove(this);
            gameFrame.gameOver = true;
        }
    }


}
