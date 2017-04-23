package com.letseat.let_s_eat;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by GOOSH on 4/23/2017.
 */

public class TimePickerFragment extends DialogFragment {

    private Activity mActivity;
    private TimePickerDialog.OnTimeSetListener mListener;

    public void onAttach(Activity activity){
        super.onAttach(activity);
        mActivity = activity;
        try{
            mListener = (TimePickerDialog.OnTimeSetListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement onTimeSetListener");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(mActivity, mListener, hour, minute , DateFormat.is24HourFormat(getActivity()));
    }
}
