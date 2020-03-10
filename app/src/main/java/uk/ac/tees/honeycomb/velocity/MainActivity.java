package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.ac.tees.honeycomb.velocity.fragments.JourneyPlannerFragment;
import uk.ac.tees.honeycomb.velocity.fragments.MainFragment;
import uk.ac.tees.honeycomb.velocity.fragments.MapsFragment;
import uk.ac.tees.honeycomb.velocity.fragments.StopTimetableFragment;

public class MainActivity extends FragmentActivity {

    AccessibilitySettings copy = AccessibilitySettings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment);
        BottomNavigationView menu = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(menu,controller);
        load(new MainFragment()); // Initial background fragment

        menu.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.nav_bus_stop:
                    load(new StopTimetableFragment());
                    return true;
                case R.id.nav_journey:
                    load(new JourneyPlannerFragment());
                    return true;
                case R.id.nav_home:
                    load(new MainFragment());
                    return true;
                case R.id.nav_map:
                    load(new MapsFragment());
                    return true;
                default:
                    return false;
            }
        });
    }

    private void load(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }
}
