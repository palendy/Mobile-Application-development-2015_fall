package com.example.anjunhyeong.advancedkillingbug;

/**
 * Created by anjunhyeong on 2015. 12. 3..
 */
public class LifeThread extends Thread {

    public MainActivity.GraphicsView life_view;

    int current_mogi ;
    int current_fly ;
    int current_cock ;


    float screenWidth = 720;
    float screenHeight = 1024;

    public LifeThread (MainActivity.GraphicsView view) {
        life_view=view;

    }

    public synchronized void run () {
            try {
                while (true) {

                    current_mogi = life_view.mogiTest.size();
                    current_fly = life_view.fly.size();
                    current_cock = life_view.cock.size();


                    for (int i = current_mogi-1; i >=0 ; i--) {
                        if (life_view.mogiTest.get(i).getMogiY() > screenHeight) {
                            life_view.scoreView.lifeDown();
                            life_view.mogiTest.remove(i);
                            life_view.drawMogiDieAnimations.remove(i);
                        }
                    }

                    for (int i = current_fly-1; i >=0; i--) {
                        if (life_view.fly.get(i).getFlyY() > screenHeight) {
                            life_view.scoreView.lifeDown();
                            life_view.fly.remove(i);
                            life_view.drawFlyDieAnimation.remove(i);
                        }
                    }

                    for (int i = current_cock-1; i >=0 ; i--) {
                        if (life_view.cock.get(i).getCockY() > screenHeight) {
                            life_view.scoreView.lifeDown();
                            life_view.cock.remove(i);
                            life_view.drawCockDieAnimation.remove(i);
                        }
                    }


                    sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

