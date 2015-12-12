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
public class SwatterEnergyBar {
        public MainActivity.GraphicsView swatter_view;

        float halfWidth;
        float halfHeight;

        //spray 위치 관련 변수
        float x;
        float y;
        int swatterAnimationCount=0;

        float screenHeight=1024;
        float screenWidth=720;


        //충돌을 위한 bound
        public RectF bound;
        RectF energyBar;
        Paint paint;
        float barSpeed=15;
        float barWidth=10;
        float barMaxHeight=screenHeight*0.9f;
        float barHeight=barMaxHeight;



        public SwatterEnergyBar(MainActivity.GraphicsView view){

            swatter_view=view;
            bound=new RectF();
            paint=new Paint();
            paint.setColor(Color.rgb(240,64,9));
            energyBar=new RectF();

            //enery bar 최초 설정
            energyBar.set(screenWidth-barWidth,0,screenWidth,0);

        }

        public float getEnergyY() {
            return barHeight;
        }

        public void initializeEnergyBar() {
            barHeight=barMaxHeight;
        }

        public void updateEnergyBar() {
            barHeight-=barSpeed;
            energyBar.set(screenWidth-barWidth,barHeight,screenWidth,barMaxHeight);
            if(barHeight<0) barHeight=barMaxHeight;
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(energyBar,paint);
            updateEnergyBar();
        }
    }

