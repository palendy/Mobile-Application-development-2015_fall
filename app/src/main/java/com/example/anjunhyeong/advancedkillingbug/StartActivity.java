package com.example.anjunhyeong.advancedkillingbug;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;

public class StartActivity extends Activity {
    private SeekBar sb;
    int gameSpeed=50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.startscreen);

        Button startButton=(Button) findViewById(R.id.button);
        sb=(SeekBar) findViewById(R.id.seekBar);
        sb.setProgress(gameSpeed);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gameSpeed=progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {
            // 버튼을 누르면 인텐트 날리기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,
                        MainActivity.class);
                intent.putExtra("GameSpeed",gameSpeed);
                startActivity(intent);

                    } // end onClick()
                });

    } // end onCraete()

}


