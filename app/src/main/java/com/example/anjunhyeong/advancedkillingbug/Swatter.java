package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by anjunhyeong on 2015. 11. 28..
 */
public class Swatter  {
        public MainActivity.GraphicsView swatter_view;
        Bitmap swatter;

        //spray 모양 관련 변수
        float halfWidth;
        float halfHeight;

        //Spray 위치 관련 변수
        float screenHeight=1024;
        float screenWidth=720;
        float x;
        float y;
        float swatterSpeedX=10;

        boolean isShootClicked=false;

        public Swatter (MainActivity.GraphicsView view ) {
            swatter_view=view;
            swatter= BitmapFactory.decodeResource(view.getResources(), R.drawable.swatter_hold);

            //이미지 비율에 맞게 크기 조정
            int viewHeight = 150;
            float width = swatter.getWidth();
            float height = swatter.getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            width=1.5f*width;
            height=1.6f*height;
            swatter=Bitmap.createScaledBitmap(swatter,(int)width,(int)height,true);
            halfHeight=height/2;
            halfWidth=width/2;

            //spray의 시작 위치 설정
            x=screenWidth/2;
            y=screenHeight*0.75f;

        }

        public float getSwatterLocationX (){
            return x;
        }

        public float getSwatterLocationY () {
            return y;
        }

        public void update(int motion) {
            //move left
            if(motion==1) {
                x=x-swatterSpeedX;
                return;
            }

            //move right
            if(motion==2) {
                x=x+swatterSpeedX;
                return;
            }
            //button off
            if(motion==0) return;

            //shoot
            if(motion==3) {
                isShootClicked=true;
            }

        }

        public void draw(Canvas canvas) {
            if(isShootClicked) {
                isShootClicked=false;
            }
            else canvas.drawBitmap(swatter,x-halfWidth,y-halfHeight,null);
        }

}
