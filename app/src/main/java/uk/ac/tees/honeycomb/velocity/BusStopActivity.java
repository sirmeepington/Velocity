package uk.ac.tees.honeycomb.velocity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class BusStopActivity extends AppCompatActivity {


    HashMap<String,String> loadedStops = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busstop_activity);
    }


    public void showBusStopViaName(View view)
    {
        final Spinner choices = (Spinner) findViewById(R.id.spinner_busstop);

        final Context type = this;
        final EditText busStopInput = (EditText) findViewById(R.id.busStopInput);
        final String message = busStopInput.getText().toString();

        final StopByName SBN = new StopByName(this,message);
        busStopInput.setHint("Loading");
        busStopInput.setText("");

        SBN.query(
                new Response.Listener<ImpetusResponse<StopByNameResponse>>() {
                    @Override
                    public void onResponse(ImpetusResponse<StopByNameResponse> response) {
                        Log.d("Velocity",response.getMessage().getDataSource());
                        busStopInput.setHint("Loaded Successfully");
                        final List<NaptanBusStop> stops = response.getMessage().getData();
                        if (stops == null){
                            Log.d("Velocity","No values found.");
                            busStopInput.setHint("Error. Stop Name Does Not Exist");
                            return;
                        }

                        if(!loadedStops.isEmpty()) {
                            loadedStops.clear();
                        }
                        for (NaptanBusStop stop : stops) {
                            Log.d("Velocity Result", stop.getName());

                            loadedStops.put(stop.getAtcoCode().toLowerCase(),stop.getName() + " - " + stop.getLocality());
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(type, android.R.layout.simple_spinner_item, new ArrayList<>(loadedStops.values()));
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choices.setAdapter(arrayAdapter);
                        choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                TableLayout tb = (TableLayout) findViewById(R.id.score_table);
                                tb.removeAllViews();
                                TableRow tr = new TableRow(type);
                                TextView test = new TextView(type);
                                test.setText(getStopAtPos(position).getKey());
                                tr.addView(test);
                                tb.addView(tr);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Velocity", "Error"+error); }
                    

                });

    }

    private Map.Entry<String,String> getStopAtPos(int pos){
        int i = 0;
        for(Map.Entry<String,String> entry : loadedStops.entrySet()){
            if (i == pos){
                return entry;
            }
            i++;
        }
        return null;
    }

    public void getTimesViaATCO()
    {

    }
}
