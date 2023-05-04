package ua.vladmoyseienko.notevault.StartingActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ua.vladmoyseienko.notevault.R;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        Intent slider = new Intent(StartingActivity.this, SliderActivity.class);
        startActivity(slider);
    }
}