package com.neuedu.main;

import com.neuedu.base.BaseSprite;
import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    //创建背景对象
    public final Background background = new Background();

    //创建敌方飞机对象
    public final EnemyPlane enemyPlane = new EnemyPlane();

/*    //创建敌方子弹
    public final EnemyBullet enemyBullet = new EnemyBullet();*/



    //创建飞机对象
    public final Plane plane = new Plane();

    //创建boss对象
    public final Boss boss = new Boss();

    //获得子弹对象
    public final Bullet bullet = new Bullet();
    //创建我方子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();


    //创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //创建敌方子弹集合
    public final List<Boss> bossList = new CopyOnWriteArrayList<>();

    //创建加子弹道具集合
    public final List<AddBUlletProp> addBUlletPropList = new CopyOnWriteArrayList<>();

    //创建加血道具集合
    public final List<AddBloodProp> addBloodPropListdB = new CopyOnWriteArrayList<>();

    //创建加护盾道具集合
    public final List<AddProtectProp> addProtectPropsList = new CopyOnWriteArrayList<>();

    //设置游戏结束默认false，一直继续
    public boolean gameOver = false;

    public boolean bossShow = false;

    public int score = 0;

    String level = null;

    private int index = 0;//计算血包生成的时间间隔

    @Override
    public void paint(Graphics g) {
        //按空格键暂停
        if (!Plane.pause) {
            //画背景
            background.draw(g);
            //开始游戏
            if (plane.getMyplaneblood() > 0 && boss.getBossblood() > 0) {
                if (!gameOver ) {
                    //画飞机
                    plane.draw(g);
                    //关卡一画第一种飞机
                    if (enemyPlane.getCount() >= 0 && enemyPlane.getCount() < 10) {
                        if (BaseSprite.random.nextInt(1000) >= 985) {
                            GameFrame gameFrame = DateStore.get("gameFrame");
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep01").getWidth(null)),
                                    0
                                    , 1, ImageMap.get("ep01"), 10,1));
                            enemyPlane.setCount(enemyPlane.getCount() + 1);


                        }
                        level = "一";
                    }

                    //关卡一画第二种飞机
                    if (enemyPlane.getCount() >= 10 && enemyPlane.getCount() < 18 && enemyPlaneList.isEmpty()) {
                        if (BaseSprite.random.nextInt(1000) >= 994) {
                            GameFrame gameFrame = DateStore.get("gameFrame");
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep02").getWidth(null)),
                                    200, 2, ImageMap.get("ep02"), 20,2));
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep02").getWidth(null)),
                                    200, 2, ImageMap.get("ep02"), 20,2));
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep02").getWidth(null)),
                                    200, 2, ImageMap.get("ep02"), 20,2));
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep02").getWidth(null)),
                                    200, 2, ImageMap.get("ep02"), 20,2));
                            enemyPlane.setCount(enemyPlane.getCount() + 4);
                        }

                        level = "二";
                    }

                    if (enemyPlane.getCount() >= 18 && enemyPlane.getCount() < 24 && enemyPlaneList.isEmpty()) {
                        if (BaseSprite.random.nextInt(1000) >= 985) {
                            GameFrame gameFrame = DateStore.get("gameFrame");
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep03").getWidth(null)),
                                    110,
                                    3, ImageMap.get("ep03"), 30,3));
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep03").getWidth(null)),
                                    110,
                                    3, ImageMap.get("ep03"), 30,3));
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep03").getWidth(null)),
                                    110,
                                    3, ImageMap.get("ep03"), 30,3));
                            enemyPlane.setCount(enemyPlane.getCount() + 3);

                        }
                        level = "三";

                    }

                    if (enemyPlane.getCount() >= 24 && enemyPlane.getCount() < 28 && enemyPlaneList.isEmpty()) {
                        if (BaseSprite.random.nextInt(1000) >= 985) {
                            GameFrame gameFrame = DateStore.get("gameFrame");
                            //飞机初始生成位置
                            gameFrame.enemyPlaneList.add(new EnemyPlane(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep04").getWidth(null)),
                                    90,
                                    4, ImageMap.get("ep04"), 40,4));
                            gameFrame.enemyPlaneList.add(new EnemyPlane(500,
                                    90,
                                    4, ImageMap.get("ep04"), 40,4));

                            enemyPlane.setCount(enemyPlane.getCount() + 2);

                        }

                        level = "四";

                    }



                    if (enemyPlane.getCount() >= 28 && enemyPlane.getCount() < 29 && enemyPlaneList.isEmpty()) {
                            if (BaseSprite.random.nextInt(1000) >= 985) {
                                GameFrame gameFrame = DateStore.get("gameFrame");
                                //飞机初始生成位置
                                gameFrame.bossList.add(new Boss((FrameConstant.FRAME_WIDTH - ImageMap.get("boss01").getWidth(null)) / 2,
                                        80,
                                        ImageMap.get("boss01")));
                                enemyPlane.setCount(enemyPlane.getCount() + 1);

                            }

                            level = "五";
                        }

                    if (level == "五") {
                        g.setColor(Color.blue);
                        g.setFont(new Font("Serif", Font.BOLD , 27));
                        g.drawString("BossHP： "+boss.getBossblood(), 300, 140);

                    }
                    if (BaseSprite.random.nextInt(1000) >= 996) {
                        //往容器里添加新的加血道具
                        GameFrame gameFrame = DateStore.get("gameFrame");
                        gameFrame.addBloodPropListdB.add(new AddBloodProp(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH- ImageMap.get("blood").getWidth(null)
                                ),
                                0, ImageMap.get("blood")));
                    }

                    if (BaseSprite.random.nextInt(1000) >= 994) {
                        //往容器里添加新的保护道具
                        GameFrame gameFrame = DateStore.get("gameFrame");
                        gameFrame.addProtectPropsList.add(new AddProtectProp(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH -ImageMap.get("protect").getWidth(null)),
                                300, ImageMap.get("protect")));
                    }

/*

                    if (BaseSprite.random.nextInt(1000) >= 998) {

                        //往容器里添加新的加子弹道具
                        GameFrame gameFrame = DateStore.get("gameFrame");
                        gameFrame.addBUlletPropList.add(new AddBUlletProp(BaseSprite.random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("addBullet").getWidth(null)),
                                130, ImageMap.get("addBullet")));
                    }
*/


                    g.setColor(Color.red);
                    g.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 60));
                    g.drawString("关卡" + level, 300, 100);


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


                    //遍历敌方子弹集合，画敌方子弹
                    for (Boss boss : bossList) {
                        boss.draw(g);
                    }


                    //遍历加血道具
                    for (AddBloodProp addBloodProp : addBloodPropListdB) {
                        addBloodProp.draw(g);
                    }


                    //遍历加护盾道具
                    for (AddProtectProp addProtectProp : addProtectPropsList) {
                        addProtectProp.draw(g);
                    }

                    //遍历加子弹道具
                    for (AddBUlletProp addBUlletProp : addBUlletPropList) {
                        addBUlletProp.draw(g);
                    }


                    //我方飞机与加子弹道具的碰撞
                    plane.collisionCheckPlaneAndBulletProp(addBUlletPropList);

                    //保护道具与我方飞机的碰撞
                    plane.collisionCheckPlaneAndProtectProp(addProtectPropsList);

                    //我方飞机与加血道具的碰撞
                    plane.collisionCheckPlaneAndBloodProp(addBloodPropListdB);

                    //我机与敌机的碰撞
                    plane.collisionCheck(enemyPlaneList);


                    //我机与敌机子弹的碰撞
                    plane.collisionCheckPlanAndEnemyBullet(enemyBulletList);

                    //我机与敌机Boss的碰撞
                    plane.   collisionCheckPlanAndEnemyBoss(bossList);





             /*   //保护道具与我方飞机的碰撞
                boss.collisionCheck(bulletList);

*/


                    //遍历我方子弹集合，调用collisionCheck方法，判断我方子弹与敌方飞机的碰撞
                    for (Bullet bullet : bulletList) {

                        bullet.collisionCheck(enemyPlaneList);

                     /*   //遍历我方子弹集合，调用collisionCheck方法，判断我方子弹与敌方子弹的碰撞
                        bullet.collisionCheck1(enemyBulletList);
*/
                        bullet.collisionCheck2(bossList);
                    }


                    g.setColor(Color.green);
                    g.setFont(new Font("行楷", Font.BOLD, 30));
                    g.drawString("得分：" + score, 150, 140);

                    g.setColor(Color.yellow);
                    g.setFont(new Font("行楷", Font.BOLD, 30));
                    g.drawString("MyHP：" + plane.getMyplaneblood(), 500, 140);
                }
                if(plane.noEnemy){
                    g.setFont(new Font("黑体",0,30));
                    g.setColor(new Color(plane.getA(),200-plane.getA(),0));
                    g.drawString("无敌时间"+plane.getA()+"/"+plane.getNoEnemyTime(),280,850);
                }

            }
            if (boss.getBossblood() <= 0) {
                g.setColor(Color.red);
                g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 70));
                g.drawString("VICTORY ! ! ! ", FrameConstant.FRAME_WIDTH / 4, FrameConstant.FRAME_HEIGHT / 2);
            }

            if (plane.getMyplaneblood() <= 0 || gameOver == true) {
                g.setColor(Color.red);
                g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 50));
                g.drawString(" GAME OVER ！！！", FrameConstant.FRAME_WIDTH / 4, FrameConstant.FRAME_HEIGHT / 2);
            }

        }

    }

    /**
     * 使用这个方法初始化窗口
     */
    public void init() {
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);

        //取消输入法选择
        enableInputMethods(false);


        //关闭窗口的方法,添加窗口的监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //关闭窗口
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

        //鼠标监听
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });


        /**
         * 重画方法
         */
        new Thread() {
            @Override
            public void run() {

                while (true) {
                    repaint();
                    try {
                        Thread.sleep(25);//每秒刷新25次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //在游戏初始前添加敌方飞机
//        addBloodPropListdB.add(new AddBloodProp(300, 100, ImageMap.get("blood")));
//        enemyPlaneList.add(new EnemyPlane(100,-500, 2));
//        enemyPlaneList.add(new EnemyPlane(150,-2000, 3));
//        enemyPlaneList.add(new EnemyPlane(100,-4000, 4));
//        enemyPlaneList.add(new EnemyPlane(100,-4500, 5));
//        enemyPlaneList.add(new EnemyPlane(100,70, ImageMap.get("ep01")));
//        enemyPlaneList.add(new EnemyPlane(400,-50, ImageMap.get("ep01")));
//        enemyPlaneList.add(new EnemyPlane(200,40, ImageMap.get("ep01")));
//        enemyPlaneList.add(new EnemyPlane(500,-20, ImageMap.get("ep01")));


        //启动窗口，放最后一行
        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图

        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }


}
