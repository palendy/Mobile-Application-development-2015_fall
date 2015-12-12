package com.example.anjunhyeong.advancedkillingbug;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by anjunhyeong on 2015. 12. 6..
 */
public class EndActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.end);

            TextView textView=(TextView) findViewById(R.id.textView3);
            Intent intent=getIntent();
            int score = intent.getExtras().getInt("Score");
            textView.setText("Score is  "+score);


        } // end onCraete()

    }