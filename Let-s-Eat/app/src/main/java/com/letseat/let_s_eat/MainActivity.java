package com.letseat.let_s_eat;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends Activity implements TimePickerDialog.OnTimeSetListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    //instance variables
    private Button timeButton;
    private EditText editTextMsg;
    private int pickerHour;
    private int pickerMin;
    private TestPlace place;
    private Button search;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeButton = (Button) findViewById(R.id.button_time);
        editTextMsg = (EditText) findViewById(R.id.editText_msg);
        place = (TestPlace) getIntent().getParcelableExtra("place_param_data");
        search = (Button) findViewById(R.id.button_search);
        if (place != null) {
            search.setText(place.getName());
        }
        //create an instance of GoogleAPIClient
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        ;
        super.onStop();
    }

    public void searchOnclick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /*
    buttonon Click method to load the time picker dialog
     */
    public void showTimePickerDialog(View view) {
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
    public void textOnClick(View view) {
        editTextMsg.setText("");
    }

    /*
    method to share the inviation (text) when clicking the share button
    needs to work on refactoring because message should be built in other method
     */
    public void shareOnClick(View view) {
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

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation != null){
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
