package uk.ac.tees.honeycomb.velocity.fragments;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.ac.tees.honeycomb.velocity.R;

public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private View parentView;
    private LocationManager location;
    private static final int LOCATION_UPDATE_DELAY_TIME = 10000;
    private static final int LOCATION_UPDATE_DISTANCE = 10000; // Unsure if this is the right type

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return parentView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);

        location = (LocationManager) parentView.getContext().getSystemService(Context.LOCATION_SERVICE);
        if (location == null) {
            Toast.makeText(parentView.getContext(), "Location services are unavailable.", Toast.LENGTH_LONG).show();
            return; // Don't bother doing the other location stuff since we cant find any location services.
        }
        
        try {
            location.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_DELAY_TIME, LOCATION_UPDATE_DISTANCE, this);
        } catch (SecurityException ex){
            Toast.makeText(parentView.getContext(), "Unable to find location.", Toast.LENGTH_LONG).show();
            Log.e("Velocity Map", "No Location provider enabled: "+ex.getMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (mMap == null)
            return;

        LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(loc).title("Player"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }

    // Unsure on the usage of these and the potential implications.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }
    @Override
    public void onProviderEnabled(String provider) { }
    @Override
    public void onProviderDisabled(String provider) { }
}
