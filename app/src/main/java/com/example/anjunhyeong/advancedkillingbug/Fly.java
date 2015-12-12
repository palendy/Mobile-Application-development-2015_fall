package com.example.anjunhyeong.advancedkillingbug;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RadialGradient;
import android.graphics.RectF;

import java.util.Random;

/**
 * Created by anjunhyeong on 2015. 11. 24..
 */
public class Fly extends Thread {

        public MainActivity.GraphicsView fly_view;
        Bitmap fly_move[]=new Bitmap[3];

        //파리의 위치 관련 변수
        float halfWidth;
        float halfHeight;
        float x;
        float y;
        float centX;
        float centY;

        //스크린 관련 변수
        float screenWidth;
        float screenHeight;
        float margin=20;

        //파리의 움직임 관련 변수
        float randomRadius=0;
        float randomAngle=0;
        float deltaAngle=0;
//        float gravity=1;
        float gravity;
        float angleRate=3;
        int count=0;
        Random random;

        //충돌을 위한 경계 변수
        public RectF bounds;
        public boolean isCollision=false;

        public Fly(MainActivity.GraphicsView view,float speed) {
            fly_view=view;
            bounds=new RectF();

            fly_move[0]=BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.fly);
            fly_move[1]=BitmapFactory.decodeResource(view.getContext().getResources(),R.drawable.fly_move1);
            fly_move[2]=BitmapFactory.decodeResource(view.getContext().getResources(),R.drawable.fly_move2);

            random=new Random();
            screenWidth=720;
            screenHeight=1024;

            //이미지 크기 조정
            int viewHeight = 70;
            float width = fly_move[0].getWidth();
            float height = fly_move[0].getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            for(int i=0;i<3;i++) {
                fly_move[i]=Bitmap.createScaledBitmap(fly_move[i],(int) width,(int)height,true);
            }

            halfHeight=fly_move[0].getHeight()/2;
            halfWidth=fly_move[0].getWidth()/2;

            x=(random.nextFloat()*(screenWidth-margin))+margin;
            y=-300;


            if(x<screenWidth/2) randomRadius=random.nextFloat()*(x-halfWidth);
            else randomRadius=random.nextFloat()*(screenWidth-halfWidth-x);

            randomAngle=random.nextFloat()*angleRate;
            deltaAngle=randomAngle;

            centX=x;
            centY=y+randomRadius;

            gravity=speed;
        }

    public float getFlyX() {
        return x;
    }
    public float getFlyY() {
        return y;
    }

        private void update() {

            x=(float) (centX+randomRadius*Math.cos(Math.toRadians(randomAngle)));
            y=(float) (centY+randomRadius*Math.sin(Math.toRadians(randomAngle)));
            randomAngle=(randomAngle+deltaAngle)%360;
            centY+=gravity;

        }

        public void draw(Canvas canvas){
            bounds.set(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
            canvas.drawBitmap(fly_move[count],x-halfWidth,y-halfHeight,null);
            count ++;
            count=count%3;
        }

        //모기의 움직이는 동작 구현
        public void run (){
                try {
                    while(!currentThread().isInterrupted()) {
                        update();
                        sleep(70);
                    }
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            finally {
                    System.out.println("Fly Thread is dead");
                }
            }

    public void freeBitmap() {
        for(int i=0; i<3;i++) {
            fly_move[i].recycle();
        }
    }


}



