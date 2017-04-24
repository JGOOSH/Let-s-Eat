package com.letseat.let_s_eat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class ProfileActivity extends Activity {

    //instance variable
    private TestPlace place;
    private TextView placeName;
    private TextView open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //geting the data from the previous activity
        place = (TestPlace) getIntent().getParcelableExtra("data");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        placeName = (TextView)findViewById(R.id.place_name);
        open = (TextView)findViewById(R.id.open);
        loadPlace(place);
    }

    private void loadPlace(TestPlace place){
        placeName.setText(place.getName());
        open.setText(place.getOpen() ?  "Open" : "Closed");
    }

    public void profileOnClick(View view) {
        boolean isOpen = place.getOpen();
        if(!isOpen){
            //if the place is not open, it should prompt error msg
            Context context = getApplicationContext();
            CharSequence text = "Place is not open!";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            //navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("place_param_data", place);
            startActivity(i);
        }
    }
}
