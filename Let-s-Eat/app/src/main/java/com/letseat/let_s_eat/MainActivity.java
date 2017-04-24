package com.letseat.let_s_eat;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends Activity implements TimePickerDialog.OnTimeSetListener {
    private Button timeButton;
    private EditText editTextMsg;
    private int pickerHour;
    private int pickerMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeButton = (Button)findViewById(R.id.button_time);
        editTextMsg = (EditText)findViewById(R.id.editText_msg);
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
        timeButton.setText(output);
    }
    /*
    method to erase the initial (Optional) text when clicking the textfield
     */
    public void textOnClick(View view){
        editTextMsg.setText("");
    }
}
