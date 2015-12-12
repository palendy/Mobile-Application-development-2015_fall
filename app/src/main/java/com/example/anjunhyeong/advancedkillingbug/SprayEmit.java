package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anjunhyeong on 2015. 11. 28..
 */
public class SprayEmit extends Thread{
        public MainActivity.GraphicsView spray_view;
        Bitmap sprayEmit[]=new Bitmap[4];

        float halfWidth;
        float halfHeight;

        //spray 위치 관련 변수
        float x;
        float y;
        public int count=0;
        public int animationImageNum=4;
        int sprayNum=0;
        int drawCount=0;

        //충돌을 위한 bound
        public RectF bounds;



        public SprayEmit (MainActivity.GraphicsView view){


            spray_view=view;
            bounds=new RectF();
            sprayEmit[0]= BitmapFactory.decodeResource(view.getResources(), R.drawable.pesticide_fire1);
            sprayEmit[1]= BitmapFactory.decodeResource(view.getResources(), R.drawable.pesticide_fire2);
            sprayEmit[2]= BitmapFactory.decodeResource(view.getResources(), R.drawable.pesticide_fire3);
            sprayEmit[3]= BitmapFactory.decodeResource(view.getResources(), R.drawable.pesticide_fire4);

            //이미지 비율에 맞게 크기 조정
            int viewHeight = 500;
            float width = sprayEmit[0].getWidth();
            float height = sprayEmit[0].getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }
            for(int i=0; i<4;i++) {
                sprayEmit[i] = Bitmap.createScaledBitmap(sprayEmit[i], (int) width, (int) height, true);
            }
            halfHeight=height/2;
            halfWidth=width/2;

        }

        public void setLocation(float sprayX,float sprayY) {
            x=sprayX;
            y=sprayY-50;
        }


        public void collisionDetection() {

            bounds.set(x - halfWidth, y - halfHeight+20, x + halfWidth, y + halfHeight);

            for(int i=0;i<spray_view.mogiTest.size();i++) {
                if(spray_view.mogiTest.get(i).bounds.intersects(x - halfWidth, y - halfHeight+20, x + halfWidth, y + halfHeight))
                    spray_view.mogiTest.get(i).isCollision=true;

            }

            for(int i=0;i<spray_view.fly.size();i++) {
                if(spray_view.fly.get(i).bounds.intersects(x - halfWidth, y - halfHeight+20, x + halfWidth, y + halfHeight))
                    spray_view.fly.get(i).isCollision=true;

            }
            for(int i=0;i<spray_view.cock.size(); i++) {
                if (spray_view.cock.get(i).bound.intersects(x - halfWidth, y - halfHeight+20, x + halfWidth, y + halfHeight))
                    spray_view.cock.get(i).isCollision=true;
            }
        }



        public void draw(Canvas canvas) {
            drawCount=count%animationImageNum;
            canvas.drawBitmap(sprayEmit[drawCount], x - halfWidth, y - halfHeight, null);
//            count=(count+1)%animationImageNum;
//            collisionDetection();

            if(count<sprayNum) {
                count++;
                collisionDetection();
            }

        }
    public int getSprayNum() {
        return sprayNum-count;
    }

    public void run() {
        while (true) {
            try {
                count=0;
                sprayNum+=animationImageNum*5;
                sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
