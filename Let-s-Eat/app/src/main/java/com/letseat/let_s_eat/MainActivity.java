package com.letseat.let_s_eat;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements TimePickerDialog.OnTimeSetListener {
    private int pickerHour;
    private int pickerMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchOnclick(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
        //finish();
    }

    /*
    buttonon Click method to load the time picker dialog
     */
    public void showTimePickerDialog(View view){
        DialogFragment newFrag = new TimePickerFragment();
        newFrag.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        pickerHour = hourOfDay;
        pickerMin = minute;
        Context context = view.getContext();
        String output = Integer.toString(hourOfDay) + " " + Integer.toString(minute);
        CharSequence text = output;
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
