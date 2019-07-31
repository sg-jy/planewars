package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    //创建背景图片
    private Background background = new Background();

    //创建飞机图片
    private Plane plane = new Plane();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //设置游戏结束默认false，一直继续
    public boolean gameOver = false;


    @Override
    public void paint(Graphics g) {
        //画背景
        background.draw(g);
        if (!gameOver) {


            //画飞机
            plane.draw(g);

            //遍历我方子弹集合，画子弹
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }

            //遍历敌方飞机集合，画敌方飞机
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }

            //遍历敌方子弹集合，画敌方子弹
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }

            //遍历我方子弹集合，调用collisionCheck方法，判断我方子弹与敌方飞机的碰撞
            for (Bullet bullet : bulletList) {
                bullet.collisionCheck(enemyPlaneList);
            }

            //遍历我方子弹集合，调用collisionCheck方法，判断我方子弹与敌方子弹的碰撞
            for (Bullet bullet : bulletList) {
                bullet.collisionCheck1(enemyBulletList);
            }

            //遍历敌方子弹集合，调用collisionCheck方法，判断敌方子弹与我方飞机的碰撞
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionCheck(plane);
            }

            //遍历敌方子弹集合，调用collisionCheck方法，判断敌方子弹与我方飞机的碰撞
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.collisionCheck(plane);
            }

        }
        else{
                //测试累计对象
                g.setColor(Color.red);
                g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 28));
//            g.setFont(font);
//        g.drawString(""+ bulletList.size(),100,100);
                g.drawString(" GAME OVER ！！！", FrameConstant.FRAME_WIDTH/3, FrameConstant.FRAME_HEIGHT/2);

        }
    }

    /**
     * 使用这个方法初始化窗口
     */
    public void init(){
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);


        enableInputMethods(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }

        });

        new Thread(){
            @Override
            public void run() {

                while(true){
                    repaint();
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

        //在游戏初始前添加敌方飞机
        enemyPlaneList.add(new EnemyPlane(300,30, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(100,70, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(400,-50, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(200,40, ImageMap.get("ep01")));
        enemyPlaneList.add(new EnemyPlane(500,-20, ImageMap.get("ep01")));


        //启动窗口，放最后一行
        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图

        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }



}
