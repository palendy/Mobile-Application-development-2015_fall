package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by anjunhyeong on 2015. 11. 27..
 */
public class GameButton  {
        public MainActivity.GraphicsView button_view;

        //버튼 모양 관련 변수
        Bitmap buttonLeft;
        Bitmap buttonRight;
        Bitmap buttonShoot;


        float halfWidth;
        float halfHeight;

        //방향 조절 및 슛 버튼 위치 관련 변수
        float screenHeight=1024;
        float screenWidth=720;
        float buttonShootX;
        float buttonShootY;
        float buttonLeftX;
        float buttonRightX;
        float buttonRange=150;



        public GameButton (MainActivity.GraphicsView view ) {
            button_view=view;
            buttonLeft= BitmapFactory.decodeResource(view.getResources(), R.drawable.left);
            buttonRight=BitmapFactory.decodeResource(view.getResources(),R.drawable.right);
            buttonShoot=BitmapFactory.decodeResource(view.getResources(),R.drawable.push);

            //이미지 비율에 맞게 크기 조정
            int viewHeight = 70;
            float width = buttonLeft.getWidth();
            float height = buttonLeft.getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            halfWidth=width/2;
            halfHeight=height/2;

            buttonLeft=Bitmap.createScaledBitmap(buttonLeft,(int)width,(int)height,true);
            buttonRight=Bitmap.createScaledBitmap(buttonRight,(int)width,(int)height,true);
            buttonShoot=Bitmap.createScaledBitmap(buttonShoot,(int)width,(int)height,true);


            //총의 시작 위치 설정
            buttonShootX=screenWidth/2;
            buttonShootY=screenHeight*0.95f;
            buttonLeftX=buttonShootX-buttonRange;
            buttonRightX=buttonShootX+buttonRange;

        }

    public float getButtonX() {
        return buttonShootX;
    }
    public float getButtonY() {
        return buttonShootY;
    }

    public boolean isIntersectLeftButton(float touchX,float touchY) {
        if(touchX<=buttonLeftX+halfWidth && touchX>=buttonLeftX-halfWidth) {
            if(touchY<=buttonShootY+halfHeight && touchY>=buttonShootY-halfHeight) return true;
        }
        return false;
     }

    public boolean isIntersectRightButton(float touchX,float touchY) {
        if(touchX<=buttonRightX+halfWidth && touchX>=buttonRightX-halfWidth) {
            if(touchY<=buttonShootY+halfHeight && touchY>=buttonShootY-halfHeight) return true;
        }
        return false;
    }

    public boolean isIntersectShootButton (float touchX,float touchY) {
        if(touchX<=buttonShootX+halfWidth && touchX>=buttonShootX-halfWidth) {
            if(touchY<=buttonShootY+halfHeight && touchY>=buttonShootY-halfHeight) return true;
        }
        return false;
    }


        public void draw(Canvas canvas) {
            canvas.drawBitmap(buttonShoot,buttonShootX-halfWidth,buttonShootY-halfHeight,null);
            canvas.drawBitmap(buttonLeft,buttonLeftX-halfWidth,buttonShootY-halfHeight,null);
            canvas.drawBitmap(buttonRight,buttonRightX-halfWidth,buttonShootY-halfHeight,null);

        }

    }
