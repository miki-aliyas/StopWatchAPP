package com.example.stopwatchapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {
    private TextView timerText;
    private MaterialButton playButton, stopButton, refreshButton;

    private int seconds, minutes, milliSeconds;
    private long millisecond, startTime, timeBuff, updateTime = 0L;
    Handler handler;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecond = SystemClock.uptimeMillis() = startTime;
            updateTime = timeBuff + millisecond;
            seconds = (int) (updateTime / 1000 );
            minutes = seconds / 60;
            seconds = seconds % 60;
            milliSeconds = (int) (updateTime % 1000);

            timerText.setText(MessageFormat.format( {0}:{1}:{2}, minutes, String.format(Local.getDefoult(),"%02d", seconds), String.format(Local.getDefoult(),"%02d", milliSeconds)));
            handler.postDelayed(this, 0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        timerText = findViewById(R.id.timer_text);
        playButton = findViewById(R.id.play_button);
        stopButton = findViewById(R.id.stop_button);
        refreshButton = findViewById(R.id.reset_button);

        handler = new Handler(Looper.getMainLooper());

        start.setOnClickListener(new View.OnClickListener() {

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable. 0);
                playButton.setEnabled(false);
                stopButton.setEnabled(true);
                refreshButton.setEnabled(false);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBuff += millisecond;
                handler.removeCallbacks(runnable);
                playButton.setEnabled(true);
                stopButton.setEnabled(false);
                refreshButton.setEnabled(true);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                millisecond = 0L;
                startTime = 0L;
                timeBuff = 0L;
                updateTime = 0L;
                seconds = 0;
                minutes = 0;
                milliSeconds = 0;
                timerText.setText("00:00:00");
            }
        });

        timerText.setText("00:00:00");
    }
}