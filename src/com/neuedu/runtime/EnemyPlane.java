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

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int enemyPlaneBlood;

    private int speed = FrameConstant.FRAME_SPEED * 3;

    //控制敌机属性
    private int type ;

    public int getEnemyPlaneBlood() {
        return enemyPlaneBlood;
    }

    public void setEnemyPlaneBlood(int enemyPlaneBlood) {
        this.enemyPlaneBlood = enemyPlaneBlood;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private  boolean right = true;//判断飞机向右移动

    private int count;//计算飞机总数


    private int attack;


    private boolean down;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //初始开始画的第一架飞机
    public EnemyPlane() {
        this( 0,0,FrameConstant.BLOOD,ImageMap.get("epo1"),1,1);

    }

    public EnemyPlane(int x, int y,int type,Image image,int enemyPlaneBlood,int attack) {
        super(x, y);
        this.type = type;
        this.image = image;
        this.enemyPlaneBlood = enemyPlaneBlood;


    }



    @Override
    public void draw(Graphics g) {
        move();
        if (getY() > 0) {
            fire();
        }
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


    }

    public void fire(){
        GameFrame gameFrame =  DateStore.get("gameFrame");
        if (type == 1){

                if (BaseSprite.random.nextInt(1000) >= 985  ) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb01").getWidth(null) / 2),
                            getY() + image.getHeight(null),
                            ImageMap.get("epb01"),1));
                }

        }
        if (type == 2){

                if (BaseSprite.random.nextInt(1000) >= 985  ) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb02").getWidth(null) / 2),
                            getY() + image.getHeight(null),
                            ImageMap.get("epb02"),2));
                }

        }
       if (type == 3){


               if (BaseSprite.random.nextInt(1000) >= 985  ) {
                   gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb03").getWidth(null) / 2),
                           getY() + image.getHeight(null),
                           ImageMap.get("epb03"),3));
                   gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb03").getWidth(null) / 2)- 40,
                           getY() + image.getHeight(null),
                           ImageMap.get("epb03"),3));
                   gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb03").getWidth(null) / 2)+40,
                           getY() + image.getHeight(null),
                           ImageMap.get("epb03"),3));
                   gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb03").getWidth(null) / 2)+20,
                           getY() + image.getHeight(null),
                           ImageMap.get("epb03"),3));
                   gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb03").getWidth(null) / 2)-20,
                           getY() + image.getHeight(null),
                           ImageMap.get("epb03"),3));

               }

        }
        if (type == 4){
                if (BaseSprite.random.nextInt(1000) >= 985  ) {
                    gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb04").getWidth(null) / 2),
                            getY() + image.getHeight(null),
                            ImageMap.get("epb04"),4));
                }

        }




    }


    @Override
    public void move() {
        if (type == 1) {
            setY(getY() + speed);
        }
        else if (type == 2){
            if (right){
                setX(getX() + speed );

            }else {
                setX(getX() - speed * 2);
            }

        }
        else if (type == 3){
            if(right){
                setX(getX() + speed);
                setY(getY() + speed);
            }else {
                setX(getX() - speed * 2);
                setY(getY() - speed * 2);
            }

        }
        else if (type == 4){
            if(right){
                setX(getY() + speed);
                setY(getX() + speed * 2);
            }else {
                setX(getY() + speed * 2);
                setY(getX() - speed * 3);
            }

        }



        borderCheck();
    }

    /**
     * 边缘判断
     */
    public void borderCheck(){
        if (type == 1){
            if (getY() > FrameConstant.FRAME_HEIGHT){
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        }
        else if (type == 2) {
            if(getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH){
                right = false;
            }else if (getX() < 0){

                right = true;
            }

        }

        else if (type == 3) {
            if(getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH){

                right = false;
            }else if (getX() < 10){

                right = true;
            }
            if (getY()+image.getHeight(null) >= FrameConstant.FRAME_HEIGHT){
                down = true;
            }else if (getY()>200){
                down = false;
            }

        }

        else if (type == 4) {
            if(getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH - 100){
                right = false;
            }else if (getX() < 50){

                right = true;
            }
            if (getY()+image.getHeight(null) >= FrameConstant.FRAME_HEIGHT){
                down = true;
            }else if (getY()>500){
                down = false;
            }

        }


    }


    /**
     * 获取图片边框
     * @return
     */
    public Rectangle getRectangle(){
        return  new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }






}
