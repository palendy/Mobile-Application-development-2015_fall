package com.example.anjunhyeong.advancedkillingbug;

/**
 * Created by anjunhyeong on 2015. 11. 27..
 */
public class ThreadControl extends Thread {
    public MainActivity.GraphicsView threadView;
    int gamespeed;

    public ThreadControl (MainActivity.GraphicsView view) {
        threadView=view;
        gamespeed=100-threadView.gameSpeed;
    }

    public void run () {
//        while(true) {
        while(!threadView.isFinishied){
            threadView.postInvalidate();
            try {
                sleep(gamespeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
