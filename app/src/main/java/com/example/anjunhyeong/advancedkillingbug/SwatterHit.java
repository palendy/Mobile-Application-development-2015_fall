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
public class SwatterHit {
        public MainActivity.GraphicsView swatter_view;
        Bitmap swatterHit[]=new Bitmap[3];

        float halfWidth;
        float halfHeight;

        //swatter 위치 관련 변수
        float x;
        float y;
        int swatterAnimationCount=0;

        float screenHeight=1024;
        float screenWidth=720;

        //에니메이션 관련 변수
        public int count=0;
        public int animationImageNum=3;


        //충돌을 위한 bound
        public RectF bounds;
        float barX;
        float barY;
        float hitBoundary=70;



        public SwatterHit (MainActivity.GraphicsView view){

            swatter_view=view;
            bounds=new RectF();

            swatterHit[0]= BitmapFactory.decodeResource(view.getResources(), R.drawable.swatter_move1);
            swatterHit[1]= BitmapFactory.decodeResource(view.getResources(), R.drawable.swatter_move2);
            swatterHit[2]= BitmapFactory.decodeResource(view.getResources(), R.drawable.swatter_move3);

            //이미지 비율에 맞게 크기 조정
            int viewHeight = 500;
            float width = swatterHit[0].getWidth();
            float height = swatterHit[0].getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }
            for(int i=0; i<3;i++) {
                swatterHit[i] = Bitmap.createScaledBitmap(swatterHit[i], (int) width, (int) height, true);
            }
            halfHeight=height/2;
            halfWidth=width/2;

        }

        public void setLocation(float swatterX,float swatterY) {
            x=swatterX+100;
            y=swatter_view.swatterEnergyBar.getEnergyY()+150;
        }


        public void collisionDetection() {
            barX = x - 100;
            barY = swatter_view.swatterEnergyBar.getEnergyY();
            bounds.set(barX - hitBoundary, barY - hitBoundary, barX + hitBoundary, barY + hitBoundary);



            for (int i = 0; i < swatter_view.mogiTest.size(); i++) {
                if (bounds.intersect(swatter_view.mogiTest.get(i).bounds))
                    swatter_view.mogiTest.get(i).isCollision = true;
            }

            for (int i = 0; i < swatter_view.fly.size(); i++) {
                if (bounds.intersect(swatter_view.fly.get(i).bounds))
                    swatter_view.fly.get(i).isCollision = true;
            }

            for (int i = 0; i < swatter_view.cock.size(); i++) {
                if (bounds.intersect(swatter_view.cock.get(i).bound))
                    swatter_view.cock.get(i).isCollision = true;
            }
        }


        public void draw(Canvas canvas) {
            canvas.drawBitmap(swatterHit[count],x-halfWidth,y-halfHeight,null);
            count++;
            collisionDetection();

        }
    }

