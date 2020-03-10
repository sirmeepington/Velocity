package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import uk.ac.tees.honeycomb.velocity.fragments.MainFragment;

public class MainActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener
{

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
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
