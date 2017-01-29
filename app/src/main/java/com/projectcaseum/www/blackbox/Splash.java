package com.projectcaseum.www.blackbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;


public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_splash);


        ImageView imgView=(ImageView) findViewById(R.id.imageView);
        Ion.with(imgView)
                .animateGif(AnimateGifMode.ANIMATE)
                .load("android.resource://com.projectcaseum.www.blackbox/" + R.drawable.blackbox_crop)
                .withBitmapInfo();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();


    }
    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }

}
