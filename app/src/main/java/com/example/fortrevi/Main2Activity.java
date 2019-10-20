package com.example.fortrevi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import com.example.fortrevi.View.CustomView;

public class Main2Activity extends AppCompatActivity {

    private int row, column;
    private CustomView customView;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int randomRow = (int)(Math.random()*row);
            int randomColumn = (int)(Math.random()*column);
            customView.highlight(randomRow, randomColumn);
            handler.postDelayed(runnable, 10000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        row = getIntent().getIntExtra("row", 0);
        column = getIntent().getIntExtra("column", 0);

        customView = new CustomView(this, row, column);
        customView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(customView);

        handler.post(runnable);
    }
}
