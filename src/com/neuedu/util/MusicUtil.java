package com.neuedu.util;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.neuedu.runtime.Plane;
/***
 * 音乐播放器类
 *
 * @author 远哥
 */
// 继承自线程类Thread
public class MusicUtil extends Thread {
    private Plane plane;
    private File music;
    private String musicPath;
    private boolean loop;
    /**
     * 构造方法（默认不循环播放）
     * @param
     */
    public MusicUtil(String musicPath) {
        this.musicPath = musicPath;
    }
    /**
     * 构造方法(是否循环)
     * @param musicpath 音乐文件所在路径
     */
    public MusicUtil(String musicpath,boolean loop) {
        this(musicpath);
        this.loop = loop;
    }
    /**
     * 重写run方法
     */
    @Override
    public void run() {
        super.run();

            if(loop) {
                while(true) {

                    try {
                        fire();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                try {
                    fire();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    /**
     * 播放方法
     * @throws FileNotFoundException
     * @throws
     */
    private void fire() throws FileNotFoundException{
        BufferedInputStream buffer = new
                BufferedInputStream(MusicUtil.class.getClassLoader().getResourceAsStream(musicPath));
        plane = new Plane();
        plane.fire();
    }
    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args) {
        MusicUtil mu = new MusicUtil("com\\neuedu\\music\\bgm_1.mp3",false);
        mu.start();
        MusicUtil mu1 = new MusicUtil("com\\neuedu\\music\\bgm_2.mp3",false);
        mu1.start();
        MusicUtil mu2 = new MusicUtil("com\\neuedu\\music\\bgm_3.mp3",false);
        mu1.start();
        MusicUtil mu3 = new MusicUtil("com\\neuedu\\music\\danger.mp3",false);
        mu1.start();
        MusicUtil mu4 = new MusicUtil("com\\neuedu\\music\\mybm.mp3",false);
        mu1.start();
    }
}
