package com.androidjavapoint.samplehandlerpost;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements View.OnClickListener {

    private Handler mHandler;
    private ProgressBar mProgressBar;
    private Button mStartButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mStartButton = (Button) findViewById(R.id.buttonStart);
        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                startProgress();
                break;
        }
    }

    private void startProgress() {
//      New thread to perform background operation
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 30; i++) {
                    final int currentProgressCount = i;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

//                  Update the value background thread to UI thread
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(currentProgressCount);
                        }
                    });
                }
            }
        }).start();
    }
}