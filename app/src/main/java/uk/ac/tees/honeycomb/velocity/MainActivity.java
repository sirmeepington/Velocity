package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    AccessibilitySettings copy = AccessibilitySettings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void redirectJourney(View view)
    {
        Intent intent = new Intent(this, JourneyPlannerActivity.class);
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
        Button btn = (Button) findViewById(R.id.optionsButton);
        btn.setTextSize(copy.getTextSize());
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}
