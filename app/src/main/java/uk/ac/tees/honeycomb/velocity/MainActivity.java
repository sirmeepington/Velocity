package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByCoords;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByCoordsResponse;
import uk.ac.tees.honeycomb.velocity.stops.BusStop;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StopByCoords coords = new StopByCoords(this,54.570907,-1.238532);
        coords.query(
        new Response.Listener<ImpetusResponse<StopByCoordsResponse>>() {
             @Override
             public void onResponse(ImpetusResponse<StopByCoordsResponse> response) {
                Log.d("Velocity",response.getMessage().getDataSource());
                List<NaptanBusStop> stops = response.getMessage().getData();
                if (stops == null){
                    Log.d("Velocity","No values found.");
                    return;
                }
                for (NaptanBusStop stop : stops) {
                    Log.d("Velocity Result", stop.getName());
                }
             }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Velocity", "Error"+error);
            }
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
}
