package com.neatwhiskey.stopwatch;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;


public class StopwatchActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
   // @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        //onSaveInstanceState method is used to save the last state of the activity before it was destroyed.

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(wasRunning){
//            running=true;
//        }
//    }
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running=true;
        }
    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasRunning=running;
//        running = false;
//    }
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }
// the onStart and onStop method works for when the app is visible and when it's not visible, while the onResume and onPause method works for when the app is partially visible and when it's fully visible
    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
           public void run(){
               int hours = seconds/3600;
               int minutes = (seconds%3600)/60;
               int secs = seconds%60;
               String time = String.format(Locale.getDefault(),
                       "%d:%02d:%02d", hours, minutes, secs);
               timeView.setText(time);
               if(running){
                   seconds++;
               }
               handler.postDelayed(this,1000);
           }
        });
    }
}