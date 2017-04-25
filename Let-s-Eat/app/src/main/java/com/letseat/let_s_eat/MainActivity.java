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
    //instance variables
    private Button timeButton;
    private EditText editTextMsg;
    private int pickerHour;
    private int pickerMin;
    private TestPlace place;
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeButton = (Button)findViewById(R.id.button_time);
        editTextMsg = (EditText)findViewById(R.id.editText_msg);
        place = (TestPlace) getIntent().getParcelableExtra("place_param_data");
        search = (Button)findViewById(R.id.button_search);
        if(place != null){
            search.setText(place.getName());
        }
    }

    public void searchOnclick(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
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

    /*
    method to share the inviation (text) when clicking the share button
    needs to work on refactoring because message should be built in other method
     */
    public void shareOnClick(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        StringBuilder sb = new StringBuilder();
        sb.append(place.getName());
        sb.append("\n");
        sb.append(timeButton.getText());
        sb.append("\n");
        sb.append(editTextMsg.getText());
        sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share with..."));
    }
}
