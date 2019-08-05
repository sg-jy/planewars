package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image>map = new HashMap<>();

    static {

        //背景图
        map.put("bg01",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg2.jpg"));


        //我方飞机1
        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_1.png"));
        //我方飞机2
        map.put("my02",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_2.png"));
        //我方飞机3
        map.put("my03",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_3.png"));


        //我方子弹1
        map.put("myb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_1.png"));
        //我方子弹2
        map.put("myb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_2.png"));
        //我方子弹3
        map.put("myb03",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_3.png"));

        //加血道具
        map.put("blood",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\blood.png"));
        //加子弹
        map.put("addBullet",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\addBullet.png"));
        //加护盾
        map.put("protect",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\protect.png"));



        //敌方飞机1
        map.put("ep01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        //敌方飞机2
        map.put("ep02",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_2.png"));
        //敌方飞机3
        map.put("ep03",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_3.png"));
        //敌方飞机4
        map.put("ep04",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_4.png"));


        //敌方boss
        map.put("boss01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\boss_1.png"));



        //敌方子弹1
        map.put("epb01",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_1.png"));
        //敌方子弹2
        map.put("epb02",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_2.png"));
        //敌方子弹3
        map.put("epb03",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_3.png"));
        //敌方子弹4
        map.put("epb04",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_4.png"));


        //敌方boss子弹
        map.put("bossB1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_5.png"));
        map.put("bossB2",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_6.png"));
        map.put("bossB3",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_7.png"));
        map.put("bossB4",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_9.png"));


    }

    public static Image get(String key){
        return  map.get(key);
    }
}
