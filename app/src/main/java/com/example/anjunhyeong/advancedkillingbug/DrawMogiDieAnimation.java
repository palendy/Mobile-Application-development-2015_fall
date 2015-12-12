package com.example.anjunhyeong.advancedkillingbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by anjunhyeong on 2015. 11. 28..
 */
public class DrawMogiDieAnimation {
    public MainActivity.GraphicsView draw_view;
    Bitmap mogi_die[]=new Bitmap[7];

    float x;
    float y;

    float halfHeight;
    float halfWidth;


    public int count=0;
    public int animationImageNum=7;


    public DrawMogiDieAnimation (MainActivity.GraphicsView view) {
        draw_view=view;

        mogi_die[0]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die1);
        mogi_die[1]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die2);
        mogi_die[2]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die3);
        mogi_die[3]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die4);
        mogi_die[4]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die5);
        mogi_die[5]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die6);
        mogi_die[6]= BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.mogi_die7);

        //이미지 scale
        int viewHeight = 70;
        float width = mogi_die[0].getWidth();
        float height = mogi_die[0].getHeight();

        if(height > viewHeight) {
            float percente = (float)(height / 100);
            float scale = (float)(viewHeight / percente);
            width *= (scale / 100);
            height *= (scale / 100);
        }

        halfHeight=height/2;
        halfWidth=width/2;
        for(int i=0;i<7;i++) {
            mogi_die[i]=Bitmap.createScaledBitmap(mogi_die[i],(int) width,(int)height,true);
        }


    }
    public void draw(Canvas canvas,float mogiX,float mogiY) {

        x=mogiX;
        y=mogiY;
        canvas.drawBitmap(mogi_die[count],x-halfWidth,y-halfHeight,null);
        count++;
    }

}
