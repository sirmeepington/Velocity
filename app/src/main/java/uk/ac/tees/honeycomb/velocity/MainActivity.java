package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_planner);
    }

    public void redirectJourney(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void redirectLineTimetable(View view)
    {
        Intent intent = new Intent(this, LineTimetableActivity.class);
        startActivity(intent);
    }

    public void redirectBusStop(View view)
    {
        Intent intent = new Intent(this, BusStopActivity.class);
        startActivity(intent);
    }

    public void redirectOptions(View view)
    {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}
