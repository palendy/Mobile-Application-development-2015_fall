package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anjunhyeong on 2015. 11. 30..
 */
public class ScoreView extends Thread{
    MainActivity.GraphicsView scoreView;
    int score=0;
    int available_spray;
    int available_gun;
    int life=5;
    int bulletNum;
    int tempScore;
    int scoreToBulletFlag=0;
    int level=0;
    Paint paint;


    public ScoreView(MainActivity.GraphicsView view) {
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(25);
        scoreView=view;
        bulletNum=scoreView.bullet.size();
        tempScore=1;

    }

    public void scoreUpMogi() {
        score+=1;
    }

    public void scoreUpFly() {
        score+=2;
    }

    public void scoreUpCock() {
        score+=3;
    }

    public void lifeDown() {
        life--;
    }

    public void lifeUp (){
        life++;
    }

    public int getScore () {
        return score;
    }
    public void levelUp() {
        level++;
    }

    public boolean isDead() {
        if (life > 0) return false;
        return true;
    }


    public void draw(Canvas canvas) {
        canvas.drawText(scoreMsg(),0,1000,paint);
        canvas.drawText(lifeMsg(), 0 ,960,paint);
        canvas.drawText(levelMsg(),600,50,paint);
        updateBullet();
//        updateScore();
    }

   public void updateBullet() {
       if(scoreToBulletFlag==1) {
           scoreView.bullet.add(new Bullet(scoreView));
           scoreToBulletFlag=0;
       }
       if(score/10==tempScore) {
           scoreToBulletFlag=1;
           tempScore++;
       }

   }

    public void drawSprayCount(Canvas canvas) {
        canvas.drawText(sprayCountMsg(),0,920,paint);
    }

    public void drawBulletCount(Canvas canvas) {
        canvas.drawText(bulletCountMsg(),0,920,paint);
    }
    public String scoreMsg(){
        return "SCORE : "+score;
    }
    public String lifeMsg() {
        return "LIFE :  "+life;
    }
    public String levelMsg() {return "LEVEL : "+level;}
    public String sprayCountMsg() {
        return "Spray : " +scoreView.sprayEmit.getSprayNum();
    }

    public String bulletCountMsg() {
        return "Bullet : "+(scoreView.bullet.size()-scoreView.bulletIndex);
    }


}
