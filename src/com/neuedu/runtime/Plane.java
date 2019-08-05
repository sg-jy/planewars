package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Plane extends BaseSprite implements Moveable, Drawable {

    private Image image ;
    private Image image1 ;

    private int index = 0;//画的次数

    private boolean up,right,down,left;

    private boolean fire ;

    public static boolean pause;

    private  int type =0;//飞机类型


    private int speed = FrameConstant.FRAME_SPEED * 5;


    private int myPlaneBlood = FrameConstant.BLOOD * 20;


    public int getMyplaneblood() {
        return myPlaneBlood;
    }

    public void setMyplaneblood(int myplaneblood) {
        this.myPlaneBlood = myplaneblood;
    }



    public Plane() {
        //飞机初始位置
        this( (FrameConstant.FRAME_WIDTH- ImageMap.get("my01").getHeight(null))/2 - 10,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"),ImageMap.get("my02"),1);
    }



    public Plane(int x, int y,Image image,Image image1,int type) {
        super(x, y);
        this.image = ImageMap.get("my01");
        this.image1 = ImageMap.get("my02");
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
        fire();
        if(type == 1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

        }
        if(type == 2){
            g.drawImage(image1,getX(),getY(),image1.getWidth(null),image1.getHeight(null),null);

        }


        if (fire){
            index++;
            if (index >= 10) {
                index = 0;
            }
        }else {
            index = 0;
        }

    }


    /**
     * 开火方法：判断开关是否打开
     * 打开则创建一个子弹对象放入到gameFrame的子弹集合中
     */
    public void fire(){
        if (fire && index == 0) {
            GameFrame gameFrame =  DateStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                    getX()+ (image.getWidth(null)/2) - (ImageMap.get("myb01").getWidth(null)/2),
                    getY() - ImageMap.get("myb01").getHeight(null),
                    ImageMap.get("myb01")
            ));
        }

    }
    /**
     * 移动方法
     */
    @Override
    public void move() {
        if(up){
            setY(getY() - speed);
        }
        if(right){
            setX(getX() + speed);
        }
        if(down){
            setY(getY() + speed);
        }
            if(left){
            setX(getX() - speed);
        }

        borderCheck();
    }

    public  void borderCheck(){
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));

        }
        if (getY() < 30) {
            setY(30);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null)) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }
    }



    public void keyPressed(KeyEvent e){
        if (e.getExtendedKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
            pause = !pause;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getExtendedKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_A) {
            left = false;
        }

        if (e.getExtendedKeyCode() == KeyEvent.VK_J) {
            fire = false;

        }
    }


    //获取我方飞机矩形
    public Rectangle getRectangle(){
            return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));

    }

    //检测我方飞机与敌方飞机的碰撞
    public void collisionCheck(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                enemyPlaneList.remove(enemyPlane);
                GameFrame gameFrame = DateStore.get("gameFrame");
                if (gameFrame.enemyPlane.getType() == 1){
                    myPlaneBlood -= FrameConstant.BLOOD ;
                }
                if (gameFrame.enemyPlane.getType() == 2){
                    myPlaneBlood -= FrameConstant.BLOOD * 2;
                }
                if (gameFrame.enemyPlane.getType() == 3){
                    myPlaneBlood -= FrameConstant.BLOOD * 3;
                }
                if (gameFrame.enemyPlane.getType() == 4){
                    myPlaneBlood -= FrameConstant.BLOOD * 4;
                }


            }
        }

    }



    //检测我方飞机与敌方飞机的碰撞
    public void collisionCheckPlanAndEnemyBullet(List<EnemyBullet> enemyBulletList){
        for (EnemyBullet enemyBullet : enemyBulletList) {
            if (enemyBullet.getRectangle().intersects(this.getRectangle())) {
                enemyBulletList.remove(enemyBullet);
                GameFrame gameFrame = DateStore.get("gameFrame");
                    myPlaneBlood -= FrameConstant.BLOOD;



            }
        }

    }


    /**
     * 边缘检测我方飞机与加子弹道具
     * @param addBUlletPropList
     */
    public void collisionCheckPlaneAndBulletProp(List<AddBUlletProp> addBUlletPropList) {

            for (AddBUlletProp addBUlletProp : addBUlletPropList) {
                if (addBUlletProp.getRectangle().intersects(this.getRectangle())) {
                    addBUlletPropList.remove(addBUlletProp);
                    GameFrame gameFrame = DateStore.get("gameFrame");
                    gameFrame.plane.setType(type++);


                }
            }

    }


    /**
     * 边缘检测我方飞机与加血道具
     * @param addBloodPropListdB
     */
    public void collisionCheckPlaneAndBloodProp(List<AddBloodProp> addBloodPropListdB) {
        for (AddBloodProp addBloodProp : addBloodPropListdB) {
            if (addBloodProp.getRectangle().intersects(this.getRectangle())) {
                addBloodPropListdB.remove(addBloodProp);
                if ((myPlaneBlood += 2) >= 20) {
                    myPlaneBlood = 20;
                }else{
                    myPlaneBlood += FrameConstant.BLOOD ;
                }

            }
        }

    }

    private static int count = 0;
    /**
     * 边缘检测我方飞机与保护道具
     * @param addProtectPropsList
     */
    public void collisionCheckPlaneAndProtectProp(List<AddProtectProp> addProtectPropsList) {
        for (AddProtectProp addProtectProp : addProtectPropsList) {
            if (addProtectProp.getRectangle().intersects(this.getRectangle())) {
                addProtectPropsList.remove(addProtectProp);
                if (count>0&&count<1000){
                    count++;
                    //遍历我方子弹集合，调用collisionCheck方法，判断我方子弹与敌方飞机的碰撞
                    GameFrame gameFrame = DateStore.get("gameFrame");
                    for (Bullet bullet : gameFrame.bulletList) {
                        //遍历我方子弹集合，调用collisionCheck方法，判断我方子弹与敌方子弹的碰撞
                        bullet.collisionCheck1(gameFrame.enemyBulletList);
                        myPlaneBlood = myPlaneBlood;
                    }

                }else {
                    count = 0;
                }


            }
        }

    }





}
