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

public class Boss extends BaseSprite implements Moveable, Drawable {
    private Image image;

    public static boolean bossLive = true;

    private int speed = FrameConstant.FRAME_SPEED ;

    private static int bossBlood = FrameConstant.BLOOD * 100;

    public int getBossblood() {
        return bossBlood;
    }

    public void setBossblood(int bossblood) {
        this.bossBlood = bossblood;
    }

    public Boss( ) {
        this( (FrameConstant.FRAME_WIDTH- ImageMap.get("boss01").getWidth(null))/2,
                350,
                ImageMap.get("boss01"));

    }


    public Boss(int x, int y, Image image) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        borderCheck();

        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

    }

    private boolean right,down = true;
    @Override
    public void move() {

        if (right){
            setX(getX() + speed );

        }else {
            setX(getX() - speed);
        }
        if (down){
            setY(getY() + speed );

        }else {
            setY(getY() - speed);
        }




    }

    public void fire(){
        if (BaseSprite.random.nextInt(1000) > 985){
           fire1();
        }
        if (BaseSprite.random.nextInt(1000) > 993){
            fire2();
        }
        if (BaseSprite.random.nextInt(1000) > 996){
            fire3();
        }
        if (BaseSprite.random.nextInt(1000) > 994){
            fire4();
        }

    }






    public void fire1(){
        GameFrame gameFrame =  DateStore.get("gameFrame");

            gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2-50),
                    getY() + image.getHeight(null),
                    ImageMap.get("bossB1"),5));
            gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2+50),
                    getY() + image.getHeight(null),
                    ImageMap.get("bossB1"),5));

    }
    public void fire2(){
        GameFrame gameFrame =  DateStore.get("gameFrame");

        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2),
                getY() + image.getHeight(null),
                ImageMap.get("bossB2"),6));


    }
    public void fire3(){
        GameFrame gameFrame =  DateStore.get("gameFrame");

        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2),
                getY() + image.getHeight(null),
                ImageMap.get("bossB3"),7));


    }
    public void fire4(){
        GameFrame gameFrame =  DateStore.get("gameFrame");
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2) + 80,
                getY() + image.getHeight(null),
                ImageMap.get("bossB4"),8));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2),
                        getY() + image.getHeight(null),
                        ImageMap.get("bossB4"),8));
        gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null)/2) - (ImageMap.get("bossB1").getWidth(null)/2) - 80,
                        getY() + image.getHeight(null),
                ImageMap.get("bossB4"),8));


    }

    /**
     * 边缘判断
     */
    public void borderCheck(){

        if(getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH){

            right = false;
        }else if (getX() < 0){

            right = true;
        }
        if(getY() >= 200){
            down = false;

        }else if (getY() > 0){

           down = true;
        }

    }


    /**
     * 获取图片边框
     * @return
     */
    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }


    /**
     * 边缘检测我方飞机与加血道具
     * @param bulletList
     */
    public void collisionCheck(List<Bullet> bulletList){
        GameFrame gameFrame = DateStore.get("gameFrame");
        for (Bullet bullet : bulletList) {
            if (bullet.getRectangle().intersects(this.getRectangle())){
                bulletList.remove(bullet);
                gameFrame.bulletList.remove(bullet);
                bossBlood -= 10;
            }
        }

    }




}
