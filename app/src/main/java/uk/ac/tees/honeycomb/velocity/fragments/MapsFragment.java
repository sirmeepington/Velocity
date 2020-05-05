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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;

import uk.ac.tees.honeycomb.velocity.R;

public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleMap.OnPoiClickListener {

    private GoogleMap mMap;
    private View parentView;
    private LocationManager location;
    private static final int LOCATION_UPDATE_DELAY_TIME = 10000;
    private static final int LOCATION_UPDATE_DISTANCE = 10000; // Unsure if this is the right type
    private PlacesClient placesApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Places.initialize(parentView.getContext(),getString(R.string.google_maps_key));
        placesApi = Places.createClient(parentView.getContext());

        return parentView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnPoiClickListener(this);

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
    }
    /**
        // For the method above.
        if (mMap == null)
            return;

        LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(loc).title("Your Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }
    */

    // Unsure on the usage of these and the potential implications.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }
    @Override
    public void onProviderEnabled(String provider) { }
    @Override
    public void onProviderDisabled(String provider) { }

    @Override
    public void onPoiClick(PointOfInterest pointOfInterest) {
        List<Place.Field> filterFields = new ArrayList<>();
        filterFields.add(Place.Field.NAME);
        filterFields.add(Place.Field.TYPES);
        filterFields.add(Place.Field.ADDRESS);
        filterFields.add(Place.Field.ID);
        filterFields.add(Place.Field.LAT_LNG);
        FetchPlaceRequest req = FetchPlaceRequest.builder(pointOfInterest.placeId,filterFields).build();
        Task<FetchPlaceResponse> responseTask = placesApi.fetchPlace(req);
        responseTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                FetchPlaceResponse res = task.getResult();
                if (res == null){
                    return;
                }
                Place place = res.getPlace();
                if (place.getTypes().contains(Place.Type.BUS_STATION) || place.getTypes().contains(Place.Type.TRANSIT_STATION)){
                    Log.d("Places","Nearby stop:  "+place.getName()+" - "+place.getId()+" - "+place.getAddress());
                }
            }
        });
    }
}
