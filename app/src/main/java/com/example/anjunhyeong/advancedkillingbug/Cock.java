package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

/**
 * Created by anjunhyeong on 2015. 11. 25..
 */
public class Cock extends Thread {

        public MainActivity.GraphicsView cock_view;
        Bitmap cock_move[]=new Bitmap[2];


        float halfWidth;
        float halfHeight;
        float x;
        float y;
        float screenWidth;
        float screenHeight;
        float margin=20;
        float speedX;
        float speedY;
        float speedRate=20;
//        float gravity=1;
        float gravity;
        int count=0;
        Random random;

        //충돌 관련 변수
        public RectF bound;
        public boolean isCollision=false;


        public Cock(MainActivity.GraphicsView view,float speed) {
            cock_view = view;
            bound =new RectF();
            cock_move[0] = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock);
            cock_move[1] = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.cock_move);

            random = new Random();
            screenWidth = 720;
            screenHeight = 1024;

            int viewHeight = 60;
            float width = cock_move[0].getWidth();
            float height = cock_move[0].getHeight();

            if (height > viewHeight) {
                float percente = (float) (height / 100);
                float scale = (float) (viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }
            for (int i = 0; i < 2; i++) {
                cock_move[i] = Bitmap.createScaledBitmap(cock_move[i], (int) width, (int) height, true);
            }
            halfHeight = cock_move[0].getHeight() / 2;
            halfWidth = cock_move[0].getWidth() / 2;


            x = (random.nextFloat() * (screenWidth - margin)) + margin;
            y = -300;

            gravity=speed;
        }

    public float getCockX() {
        return x;
    }
    public float getCockY() {
        return y;
    }

        private void update() {
            speedX=random.nextInt()%speedRate;
            speedY=random.nextInt()%speedRate;

            if(0<=x+speedX-halfWidth && x+speedX+halfWidth<=screenWidth) {
                x += speedX;
                y += speedY+gravity;
            }
        }


        public void draw(Canvas canvas){
            bound.set(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);

            canvas.drawBitmap(cock_move[count],x-halfWidth,y-halfHeight,null);
            count ++;
            count=count%2;
        }

        //모기의 움직이는 동작 구현
        public void run (){
                try {
                    while(!currentThread().isInterrupted()) {
                        update();
                        sleep(100);
                    }
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            finally {
                    System.out.println("Cockaroch Thread is Dead");
                }
            }

    public void freeBitmap() {
        for(int i=0; i<2;i++) {
            cock_move[i].recycle();
        }
    }

}

