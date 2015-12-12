package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by anjunhyeong on 2015. 11. 28..
 */
public class DrawFlyDieAnimation {
        public MainActivity.GraphicsView draw_view;
        Bitmap fly_die[]=new Bitmap[3];

        float x;
        float y;

        float halfHeight;
    float halfWidth;

        boolean isDraw=false;

        public int count=0;
        public int animationImageNum=3;


        public DrawFlyDieAnimation (MainActivity.GraphicsView view) {
            draw_view=view;

            fly_die[0]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.fly_die);
            fly_die[1]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.fly_die2);
            fly_die[2]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.fly_die3);

            //이미지 scale
            int viewHeight = 70;
            float width = fly_die[0].getWidth();
            float height = fly_die[0].getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            halfHeight=height/2;
            halfWidth=width/2;
            for(int i=0;i<animationImageNum;i++) {
                fly_die[i]=Bitmap.createScaledBitmap(fly_die[i],(int) width,(int)height,true);
            }


        }
        public void draw(Canvas canvas,float flyX,float flyY) {
            x=flyX;
            y=flyY;
            canvas.drawBitmap(fly_die[count],x-halfWidth,y-halfHeight,null);
            count++;
        }

    }
