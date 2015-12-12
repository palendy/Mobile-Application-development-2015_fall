package com.example.anjunhyeong.advancedkillingbug;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new GraphicsView(this));


    }


    public class GraphicsView extends View {

        public int gameSpeed=getIntent().getExtras().getInt("GameSpeed");

        ThreadControl threadControl;

        Bitmap background;


        ArrayList<Mogi> mogiTest;
        ArrayList<DrawMogiDieAnimation> drawMogiDieAnimations;


        ArrayList<Fly> fly;
        ArrayList<DrawFlyDieAnimation> drawFlyDieAnimation;


        ArrayList<Cock> cock;
        ArrayList<DrawCockDieAnimation> drawCockDieAnimation;

        Gun gun;
//        Bullet bullet;
        ArrayList<Bullet> bullet;

        Spray spray;
        SprayEmit sprayEmit;

        Swatter swatter;
        SwatterHit swatterHit;
        SwatterEnergyBar swatterEnergyBar;

        GameButton gameButton;
        WeaponChangeButton weaponChangeButton;

        ScoreView scoreView;


        //0는 button default 1은 left, 2는 right, 3은 shoot 4는 button up
        int buttonTouchFlag = 0;
        int bulletIndex=0;
        boolean isFinishied;


        LevelingThread levelingThread;
        LifeThread lifeThread;

        public GraphicsView(Context context) {
            super(context);

            background=BitmapFactory.decodeResource(getResources(),R.drawable.background_small);

            //배경화면 이미지 크기 조절
            setImageScale();

            threadControl = new ThreadControl(this);
            threadControl.start();

            mogiTest = new ArrayList<>();
            drawMogiDieAnimations = new ArrayList<>();

            fly = new ArrayList<>();
            drawFlyDieAnimation = new ArrayList<>();

            cock = new ArrayList<>();
            drawCockDieAnimation = new ArrayList<>();


            gun = new Gun(this);

//            bullet = new Bullet(this);
            bullet=new ArrayList<>();
            bullet.add(0,new Bullet(this));

            spray = new Spray(this);
            sprayEmit = new SprayEmit(this);
            sprayEmit.start();

            swatter = new Swatter(this);
            swatterHit = new SwatterHit(this);
            swatterEnergyBar = new SwatterEnergyBar(this);

            gameButton = new GameButton(this);

            weaponChangeButton = new WeaponChangeButton(this);

            scoreView=new ScoreView(this);

            levelingThread=new LevelingThread(this);

            levelingThread.start();

            lifeThread=new LifeThread(this);
            lifeThread.start();


        }

        protected void onDraw(Canvas canvas) {
            canvas.setDensity(90);
            canvas.drawBitmap(background,0,0,null);

            //mogi
            for (int i = 0; i < mogiTest.size(); i++) {
                if (!mogiTest.get(i).isCollision) {
                    mogiTest.get(i).draw(canvas);
                } else {
                    if (drawMogiDieAnimations.get(i).count <
                            drawMogiDieAnimations.get(i).animationImageNum) {
                        drawMogiDieAnimations.get(i).draw(canvas, mogiTest.get(i).getMogiX(), mogiTest.get(i).getMogiY());
                    } else {
                        mogiTest.get(i).interrupt();
                    }
                }
            }


            //fly
            for (int i = 0; i < fly.size(); i++) {
                if (!fly.get(i).isCollision)
                    fly.get(i).draw(canvas);

                else {
                    if (drawFlyDieAnimation.get(i).count <
                            drawFlyDieAnimation.get(i).animationImageNum) {
                        drawFlyDieAnimation.get(i).draw(canvas, fly.get(i).getFlyX(), fly.get(i).getFlyY());
                    } else fly.get(i).interrupt();
                }
            }

            //바퀴벌레 일때
            for (int i = 0; i < cock.size(); i++) {
                if (!cock.get(i).isCollision) cock.get(i).draw(canvas);

                else {
                    if (drawCockDieAnimation.get(i).count <
                            drawCockDieAnimation.get(i).animationImageNum) {
                        drawCockDieAnimation.get(i).draw(canvas, cock.get(i).getCockX(), cock.get(i).getCockY());
                    } else cock.get(i).interrupt();
                }
            }

            //swatter 일 떄
            if (weaponChangeButton.currentWeapon == 0) {
                swatter.draw(canvas);
                swatter.update(buttonTouchFlag);

                //shoot button 을 눌렀을 떄
                if (buttonTouchFlag == 3) {
                    swatterEnergyBar.draw(canvas);
                    swatterHit.count = 0;
                }
                //button up이 될떄
                if (buttonTouchFlag == 4) {
                    if (swatterHit.count < swatterHit.animationImageNum) {
                        swatterHit.setLocation(swatter.getSwatterLocationX(), swatter.getSwatterLocationY());
                        swatterHit.draw(canvas);
                    } else swatterEnergyBar.initializeEnergyBar();
                }
            }

            //spray 일 때
            if (weaponChangeButton.currentWeapon == 1) {
                scoreView.drawSprayCount(canvas);
                spray.draw(canvas);
                spray.update(buttonTouchFlag);

                //shoot button이 클릭되서 스프레이가 나갈때
                if (buttonTouchFlag == 3) {
                    sprayEmit.setLocation(spray.getSprayLocationX(), spray.getSprayLocationY());
                    sprayEmit.draw(canvas);

                }
            }


            //총일 때
            if (weaponChangeButton.currentWeapon == 2) {
                scoreView.drawBulletCount(canvas);
                gun.draw(canvas);
                gun.update(buttonTouchFlag);

                //슛 버튼이 클릭되면
            /*    if (buttonTouchFlag == 3) {
                    bullet.setLocation(gun.getGunLocationX(), gun.getGunLocationY());
                    bullet.isShooted = true;
                }*/
                //슛 버튼이 클릭되면
                if(buttonTouchFlag==3 && bulletIndex<bullet.size()) {
                    bullet.get(bulletIndex).setLocation(gun.getGunLocationX(),gun.getGunLocationY());
                    bullet.get(bulletIndex).isShooted=true;
                    bulletIndex++;
                }
            }

            //발사 되서 총알이 나간 상태라면 그려준다.
//            if (bullet.isShooted) bullet.draw(canvas);
            for(int i=0;i<bullet.size();i++) {
                if (bullet.get(i).isShooted) bullet.get(i).draw(canvas);
            }

            //게임 진행 관련 버튼, 메뉴를 그려주는 부분
            gameButton.draw(canvas);
            weaponChangeButton.draw(canvas);

            //score 와 life 메세지를 그려주는 부분
            scoreView.draw(canvas);

            if(scoreView.isDead()) {
                Intent intent=new Intent(MainActivity.this,EndActivity.class);
                intent.putExtra("Score", scoreView.getScore());
                isFinishied=true;
                startActivity(intent);
                sprayEmit.interrupt();
                lifeThread.interrupt();
                levelingThread.interrupt();
                threadControl.interrupt();
            }

        }




        public boolean onTouchEvent(MotionEvent event) {
            float currentX=event.getX();
            float currentY=event.getY();
            buttonTouchFlag=0;
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    System.out.println("Action Down");
                    if(gameButton.isIntersectLeftButton(currentX,currentY)) buttonTouchFlag=1;
                    if(gameButton.isIntersectRightButton(currentX, currentY)) buttonTouchFlag=2;
                    if(gameButton.isIntersectShootButton(currentX, currentY)) buttonTouchFlag=3;
                    if(weaponChangeButton.isIntersectChangeButton(currentX,currentY)) {
                        weaponChangeButton.weaponChangeFlag=(weaponChangeButton.weaponChangeFlag+1)%weaponChangeButton.weaponNum;
                        weaponChangeButton.currentWeapon=(weaponChangeButton.currentWeapon+1)%weaponChangeButton.weaponNum;
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    System.out.println("Action Up");
                    if(gameButton.isIntersectShootButton(currentX,currentY)) buttonTouchFlag=4;
                    break;

            }
            return true;
        }

        private void setImageScale (){

            //배경화면 이미지 크기에 맞게 조절
            int viewHeight = 1500;
            float width = background.getWidth();
            float height = background.getHeight();

            if(height > viewHeight) {
                float percente = (float)(height / 100);
                float scale = (float)(viewHeight / percente);
                width *= (scale / 100);
                height *= (scale / 100);
            }

            background=Bitmap.createScaledBitmap(background, (int) width, (int) height, true);
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
