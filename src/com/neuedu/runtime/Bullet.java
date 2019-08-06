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

public class Bullet extends BaseSprite implements Moveable, Drawable {
    private Image image;


    private int speed = FrameConstant.FRAME_SPEED * 5;

    public Bullet() {
        this(0, 0, ImageMap.get("myb01"));

    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        borderCheck();

    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    public void borderCheck() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DateStore.get("gameFrame");

            gameFrame.bulletList.remove(this);

        }
    }


    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    //我方子弹与敌方飞机
    public void collisionCheck(List<EnemyPlane> enemyPlaneList){
        GameFrame gameFrame = DateStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                gameFrame.bulletList.remove(this);
                enemyPlane.setEnemyPlaneBlood(enemyPlane.getEnemyPlaneBlood() - 2);
                gameFrame.enemyPlane.setEnemyPlaneBlood(gameFrame.enemyPlane.getEnemyPlaneBlood()-2);
                gameFrame.score += 1;
                if (enemyPlane.getEnemyPlaneBlood() <= 0){
                    enemyPlaneList.remove(enemyPlane);
                    gameFrame.score += enemyPlane.getType() * 5;
                }

            }
        }




    }

    //我方子弹与敌方子弹
    public void collisionCheck1(List<EnemyBullet> enemyBulletList){
        GameFrame gameFrame = DateStore.get("gameFrame");
        for (EnemyBullet enemyBullet : enemyBulletList) {
            if (enemyBullet.getRectangle().intersects(this.getRectangle())){
                enemyBulletList.remove(enemyBullet);
                gameFrame.bulletList.remove(this);
            }
        }

    }

    //子弹与Boss
    public void collisionCheck2(List<Boss> bossList){
        for (Boss boss : bossList ) {
            if (boss.getRectangle().intersects(this.getRectangle())) {
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.bulletList.remove(this);
                boss.setBossblood(boss.getBossblood() - 1);
                gameFrame.score += 5;
                if (boss.getBossblood() == 0) {
                    gameFrame.score += 100;
                }
            }
        }
    }

}
