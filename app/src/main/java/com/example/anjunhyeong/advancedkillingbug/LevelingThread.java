package com.example.anjunhyeong.advancedkillingbug;

/**
 * Created by anjunhyeong on 2015. 12. 3..
 */
public class LevelingThread extends Thread {

    public MainActivity.GraphicsView level_view;
    int count=1;
    public LevelingThread (MainActivity.GraphicsView view) {
        level_view=view;


    }

    public synchronized void run () {
        while(true) {
            try {
                level_view.scoreView.levelUp();
                int mogiNum=count;
                int flyNum=count;
                int cockNum=count;

                int current_mogi=level_view.mogiTest.size();
                int current_fly=level_view.fly.size();
                int current_cock=level_view.cock.size();


                //단계별로 빠른 스피드의 벌레를 생성하기 위한 부분
                float speedMogi=50+count;
                float speed=1+count;


                //벌레들 생성
                for (int i = current_mogi; i < current_mogi+mogiNum; i++) {
                    level_view.mogiTest.add(i, new Mogi(level_view,speedMogi));
                    level_view.mogiTest.get(i).start();
                    level_view.drawMogiDieAnimations.add(i, new DrawMogiDieAnimation(level_view));
                }

                for (int i = current_fly; i < current_fly+flyNum; i++) {
                    level_view.fly.add(i, new Fly(level_view,speed));
                    level_view.fly.get(i).start();
                    level_view.drawFlyDieAnimation.add(i, new DrawFlyDieAnimation(level_view));
                }


                for (int i = current_cock; i < current_cock+cockNum; i++) {
                    level_view.cock.add(i, new Cock(level_view,speed));
                    level_view.cock.get(i).start();
                    level_view.drawCockDieAnimation.add(i, new DrawCockDieAnimation(level_view));
                }

                //메모리, 배열 재할당 부분




                for (int i = 0; i < level_view.mogiTest.size(); i++) {
                    if (level_view.mogiTest.get(i).isCollision) {
                        level_view.scoreView.scoreUpMogi();
                        level_view.mogiTest.get(i).freeBitmap();
                        level_view.mogiTest.remove(i);
                        level_view.drawMogiDieAnimations.remove(i);
                    }
                }

                for (int i = 0; i < level_view.fly.size(); i++) {
                    if (level_view.fly.get(i).isCollision) {
                        level_view.scoreView.scoreUpFly();
                        level_view.fly.get(i).freeBitmap();
                        level_view.fly.remove(i);
                        level_view.drawFlyDieAnimation.remove(i);
                    }
                }

                for (int i = 0; i < level_view.cock.size(); i++) {
                    if (level_view.cock.get(i).isCollision) {
                        level_view.scoreView.scoreUpCock();
                        level_view.cock.get(i).freeBitmap();
                        level_view.cock.remove(i);
                        level_view.drawCockDieAnimation.remove(i);
                    }
                }

                System.gc();

                count++;

//                sleep(50000);
                sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
