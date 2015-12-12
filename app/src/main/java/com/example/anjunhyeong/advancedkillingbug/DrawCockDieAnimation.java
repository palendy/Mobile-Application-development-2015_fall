package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by anjunhyeong on 2015. 11. 28..
 */
public class DrawCockDieAnimation  {
        public MainActivity.GraphicsView draw_view;
        Bitmap cock_die[]=new Bitmap[7];

        float x;
        float y;

        float halfHeight;
        float halfWidth;


        public int count=0;
        public int animationImageNum=7;


        public DrawCockDieAnimation (MainActivity.GraphicsView view) {
            draw_view=view;

            cock_die[0]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die1);
            cock_die[1]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die2);
            cock_die[2]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die3);
            cock_die[3]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die4);
            cock_die[4]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die5);
            cock_die[5]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die6);
            cock_die[6]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_die7);

            //이미지 scale
            int viewHeight = 70;
            float width = cock_die[0].getWidth();
            float height = cock_die[0].getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            halfHeight=height/2;
            halfWidth=width/2;
            for(int i=0;i<animationImageNum;i++) {
                cock_die[i]=Bitmap.createScaledBitmap(cock_die[i],(int) width,(int)height,true);
            }


        }
        public void draw(Canvas canvas, float cockX, float cockY) {
            x=cockX;
            y=cockY;
            canvas.drawBitmap(cock_die[count],x-halfWidth,y-halfHeight,null);
            count++;
        }

    }