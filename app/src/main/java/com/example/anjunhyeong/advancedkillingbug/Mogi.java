package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;


import java.util.Random;


/**
 * Created by anjunhyeong on 2015. 11. 24..
 */
public class Mogi extends Thread {

        public MainActivity.GraphicsView mogi_view;
        Bitmap mogi_move[] = new Bitmap[2];


        // 모양 관련 변수
        float halfWidth;
        float halfHeight;
        float x;
        float y;
        int count = 0;
        float screenWidth;
        float screenHegiht;

        //움직임 관련 변수
        float range = 100;
        float leftBound;
        float rightBound;
        float speedX = 5;
        float speedY;
//        float gravity = 50;

        float gravity;
        Random random;

        //충돌을 위한 경계 변수
        public RectF bounds;
        public boolean isCollision = false;


        public Mogi(MainActivity.GraphicsView view,float speed) {
                mogi_view = view;
                bounds = new RectF();
                random = new Random();

                screenHegiht = 1024;
                screenWidth = 720;

                mogi_move[0] = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi);
                mogi_move[1] = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_move);


                //이미지 크기 조정
                int viewHeight = 70;
                float width = mogi_move[0].getWidth();
                float height = mogi_move[0].getHeight();

                if (height > viewHeight) {
                        float percente = (float) (height / 100);
                        float scale = (float) (viewHeight / percente);
                        width *= (scale / 100);
                        height *= (scale / 100);
                }
                for (int i = 0; i < 2; i++) {
                        mogi_move[i] = Bitmap.createScaledBitmap(mogi_move[i], (int) width, (int) height, true);
                }

                halfHeight = mogi_move[0].getHeight() / 2;
                halfWidth = mogi_move[0].getWidth() / 2;


                //모기의 시작위치 설정
                x = (random.nextFloat() * (screenWidth - width)) + width;
                y = -300;
                leftBound = x - halfWidth;
                rightBound = leftBound + range;

                gravity=speed;

        }


        public float getMogiX() {
                return x;
        }

        public float getMogiY() {
                return y;
        }


        private void update() {
                if (speedX > 0) speedY = gravity / (rightBound - x);
                else speedY = gravity / (x - leftBound);

                x += speedX;
                y += speedY;

                if (x + halfWidth >= rightBound || x - halfWidth <= leftBound) speedX = -speedX;

        }


        public void draw(Canvas canvas) {
                //충돌을 위한 bound set
                bounds.set(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
                canvas.drawBitmap(mogi_move[count], x - halfWidth, y - halfHeight, null);

                count++;
                count = count % 2;
        }


        //모기의 움직이는 동작 구현
        public void run() {
                try {
                        while(!currentThread().isInterrupted()) {
                                update();
                                sleep(70);
                        }
                } catch (InterruptedException e) {
                        //e.printStackTrace();
                }
                finally {
                        System.out.println("Mogi Thread dead");
                }
        }

        // 모기 객체의 비트맵 할당 메모리를 제거해주는 함수
        public void freeBitmap() {
                for(int i=0; i<2;i++) {
                        mogi_move[i].recycle();
                }
        }

}
