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
public class WeaponChangeButton extends Thread{

        public MainActivity.GraphicsView button_view;
        Bitmap buttonWeaponChnage[]=new Bitmap[3];

        //버튼 위치 관련 변수
        float changeButtonX;
         float changeButtonY;

    float halfWidth;
        float halfHeight;

        //weapon change button  관련 변수
        //0는 총,1은 살충제, 2는 총
        public int weaponNum=3;
        public int weaponChangeFlag=1;
        public int currentWeapon=0;
        float range=300;
    //        RectF bounds;
//        Paint paint;

        public WeaponChangeButton (MainActivity.GraphicsView view){

            button_view=view;
            buttonWeaponChnage[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.change_button_swatter);
            buttonWeaponChnage[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.change_button_pesticide);
            buttonWeaponChnage[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.chage_button_gun);

            //이미지 비율에 맞게 크기 조정
            int viewHeight = 100;
            float width = buttonWeaponChnage[0].getWidth();
            float height = buttonWeaponChnage[0].getHeight();

            if (height > viewHeight) {
                float percente = (float) (height / 100);
                float scale = (float) (viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            halfWidth = width / 2;
            halfHeight = height / 2;


            //무기 바꾸는 버튼 이미지 scale
            for (int i = 0; i < weaponNum; i++)
                buttonWeaponChnage[i] = Bitmap.createScaledBitmap(buttonWeaponChnage[i], (int) width, (int) height, true);

            //무기 바꾸는 버튼 위치 설정
            changeButtonX = view.gameButton.getButtonX()+range;
            changeButtonY= view.gameButton.getButtonY();

//            bounds=new RectF();
//            paint=new Paint();
//            paint.setColor(Color.BLUE);
//            bounds.set(changeButtonX-halfWidth,changeButtonY-halfHeight,changeButtonX+halfWidth,changeButtonY+halfHeight);
        }

    public boolean isIntersectChangeButton (float touchX,float touchY) {
        if(touchX<=changeButtonX+halfWidth && touchX>=changeButtonX-halfWidth) {
            if(touchY<=changeButtonY+halfHeight && touchY>=changeButtonY-halfHeight) {
                System.out.println("In change Button Pushed!!!!!!!!!");
                return true;
            }
        }
        return false;
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(buttonWeaponChnage[weaponChangeFlag], changeButtonX-halfWidth, changeButtonY-halfHeight, null);
        //canvas.drawRect(bounds, paint);
    }

   /* public void run () {
        while(true) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
