package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.ac.tees.honeycomb.velocity.fragments.MainFragment;
import uk.ac.tees.honeycomb.velocity.fragments.MainFragmentDirections;
import uk.ac.tees.honeycomb.velocity.fragments.StopTimetableFragment;

public class MainActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener, StopTimetableFragment.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment);
        BottomNavigationView menu = findViewById(R.id.bottom_nav);

        menu.setOnNavigationItemSelectedListener(item -> {
            if (controller.getCurrentDestination().getId() != R.id.fragment_main){
                controller.navigate(R.id.fragment_main); // Go back to main for compatibility
            }
            NavDirections dir = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    break; // Already gone back to main
                case R.id.nav_bus_stop:
                    dir = MainFragmentDirections.mainToStopTimetable();
                default:
                    break;
            }
            if (dir != null){
                controller.navigate(dir); // Go to the selected area.
            }
            return true;
        });
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
