package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anjunhyeong on 2015. 11. 27..
 */
public class Bullet extends Thread{
    public MainActivity.GraphicsView bullet_view;
    Bitmap bullet;

    float halfWidth;
    float halfHeight;

    //총 위치 관련 변수
    float x;
    float y;
    float bulletSpeed=10;

    //총알 생성,발사 여부
    public boolean isShooted=false;
    float boundMargin=5;

    //충돌을 위한 bound
    public RectF bound;




    public Bullet (MainActivity.GraphicsView view){

        bullet_view=view;
        bound=new RectF();
        bullet= BitmapFactory.decodeResource(view.getResources(),R.drawable.bullet);

        //이미지 비율에 맞게 크기 조정
        int viewHeight = 90;
        float width = bullet.getWidth();
        float height = bullet.getHeight();

        if(height > viewHeight) {
            float percente = (float)(height / 100);
            float scale = (float)(viewHeight / percente);
            width *= (scale / 100);
            height *= (scale / 100);
        }
        bullet=Bitmap.createScaledBitmap(bullet,(int)width,(int)height,true);
        halfHeight=height/2;
        halfWidth=width/2;

    }

    public void setLocation(float gunX,float gunY) {
        x=gunX+100;
        y=gunY;
        bound.set(x - boundMargin - halfWidth, y - boundMargin - halfHeight, x + boundMargin + halfWidth, y + boundMargin + halfHeight);
    }

    private void update() {
        y-=bulletSpeed;
        collisionDetection();
        bound.set(x - boundMargin - halfWidth, y - boundMargin - halfHeight, x + boundMargin + halfWidth, y + boundMargin + halfHeight);
    }

    public void collisionDetection() {
        for(int i=0;i<bullet_view.mogiTest.size();i++) {
            if (bound.intersect(bullet_view.mogiTest.get(i).bounds)) {
                bullet_view.mogiTest.get(i).isCollision = true;
            }
        }

        for(int i=0; i<bullet_view.fly.size();i++) {
            if (bound.intersect(bullet_view.fly.get(i).bounds)) {
                bullet_view.fly.get(i).isCollision = true;
            }
        }
        for(int i=0; i<bullet_view.cock.size();i++) {
            if (bound.intersect(bullet_view.cock.get(i).bound)) {
                bullet_view.cock.get(i).isCollision = true;
            }
        }
    }

    public void draw(Canvas canvas) {
        if(!isOutOfScreen()) canvas.drawBitmap(bullet, x - halfWidth, y - halfHeight, null);
        update();
    }

    public boolean isOutOfScreen() {
        if(y+halfHeight<0) return true;
        else return false;
    }

   /* public void run() {
        while (isShooted) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
