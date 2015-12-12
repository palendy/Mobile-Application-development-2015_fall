package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by anjunhyeong on 2015. 11. 27..
 */
public class Gun {
    public MainActivity.GraphicsView gun_view;
    Bitmap gunHold;
    Bitmap gunShoot;

    float halfWidth;
    float halfHeight;

    //총 위치 관련 변수
    float screenHeight=1024;
    float screenWidth=720;
    float gunX;
    float gunY;
    float gunSpeedX=10;

    boolean isShootClicked=false;

    public Gun (MainActivity.GraphicsView view ) {
        gun_view=view;
        gunHold=BitmapFactory.decodeResource(view.getResources(),R.drawable.gun);
        gunShoot=BitmapFactory.decodeResource(view.getResources(),R.drawable.gun_shoot);

        //이미지 비율에 맞게 크기 조정
        int viewHeight = 150;
        float width = gunHold.getWidth();
        float height = gunHold.getHeight();

        if(height > viewHeight) {
            float percente = (float)(height / 100);
            float scale = (float)(viewHeight / percente);
            width *= (scale / 100);
            height *= (scale / 100);
        }

        gunHold=Bitmap.createScaledBitmap(gunHold,(int)width,(int)height,true);
        width=1.5f*width;
        height=1.6f*height;
        gunShoot=Bitmap.createScaledBitmap(gunShoot,(int)width,(int)height,true);

        halfHeight=height/2;
        halfWidth=width/2;

        //총의 시작 위치 설정
        gunX=screenWidth/2;
        gunY=screenHeight*0.75f;

    }

    public float getGunLocationX (){
        return gunX;
    }

    public float getGunLocationY () {
        return gunY;
    }

    public void update(int motion) {
        //move left
        if(motion==1) {
            gunX=gunX-gunSpeedX;
            return;
        }

        //move right
        if(motion==2) {
            gunX=gunX+gunSpeedX;
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
            canvas.drawBitmap(gunShoot,gunX,gunY,null);
            isShootClicked=false;
        }
        else canvas.drawBitmap(gunHold,gunX,gunY,null);
    }

}
