package com.goosh.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class InitialActivity extends AppCompatActivity {

    //instance variables
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        view = (View)findViewById(R.id.activity_initial);
        view.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                Context context = getApplicationContext();
                CharSequence text = "Swipe is working";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
            }
            @Override
            public void onSwipeRight() {
                Context context = getApplicationContext();
                CharSequence text = "Swipe is working";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
            }
        });

    }
}
