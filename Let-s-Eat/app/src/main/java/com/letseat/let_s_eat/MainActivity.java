package com.letseat.let_s_eat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button_search);
    }

    public void searchOnclick(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
        finish();
    }
}
