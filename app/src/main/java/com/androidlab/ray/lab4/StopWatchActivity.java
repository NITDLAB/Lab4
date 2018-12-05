package com.androidlab.ray.lab4;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends AppCompatActivity {

    Boolean timer_run = false;
    TextView timer;
    String stime = "00:00:00:000";
    long hour = 0, min =0, sec = 0, mil=0;

    long initial_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState!=null)
        {
            hour = savedInstanceState.getLong("h");
            min = savedInstanceState.getLong("m");
            sec = savedInstanceState.getLong("s");
            mil = savedInstanceState.getLong("ml");
            timer_run = savedInstanceState.getBoolean("r");

        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        timer = findViewById(R.id.timer);
        runtimer();



    }

    public void runtimer()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if(timer_run)
                {
                    long t = System.currentTimeMillis();
                    mil = t-initial_time;

                    sec = mil/1000;
                    min = sec/60;
                    hour = min/60;

                    mil = mil%1000;
                    min = min%60;
                    sec = sec%60;

                }
                stime=String.format("%d:%02d:%02d:%02d",hour,min,sec,mil);
                timer.setText(stime);
                handler.postDelayed(this,1);
            }
        });
    }

    public void start_func(View v)
    {
        timer_run = true;
        initial_time = System.currentTimeMillis()-(hour*60*60*1000+min*60*1000+sec*1000+mil);
    }
    public void pause_func(View v)
    {
        timer_run = false;
    }
    public void reset_func(View v)
    {
        hour=0;
        sec=0;
        min=0;
        mil = 0;
        timer_run = false;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("h", hour);
        savedInstanceState.putLong("m", min);
        savedInstanceState.putLong("s", sec);
        savedInstanceState.putLong("ml", mil);
        savedInstanceState.putBoolean("r", timer_run);

    }

}
