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

    private int speed = FrameConstant.FRAME_SPEED * 3;

    public EnemyBullet() {
        this(0, 0, ImageMap.get("epb01"));

    }

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        borderCheck();
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

    public void collisionCheck(Plane plane){
        GameFrame gameFrame = DateStore.get("gameFrame");
            if (plane.getRectangle().intersects(this.getRectangle())){
                gameFrame.enemyPlaneList.remove(this);
                gameFrame.gameOver = true;
            }

    }



}
