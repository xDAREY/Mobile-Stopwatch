package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private boolean Ran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("Ran");
        }

        runTimer();
    }

    public void onStart(View view){
        running = true;
    }
    public void onStop(View v){
        running = false;
    }
    public void onReset(View v){
        running = false;
        seconds = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Ran = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Ran){
            running = true;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("Ran", Ran);
    }

    private void runTimer() {
        TextView Timer = findViewById(R.id.TimerId);
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);

                Timer.setText(time);

                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}