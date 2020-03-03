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
import java.util.List;

import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class BusStopActivity extends AppCompatActivity {

    ArrayList<String> ATCOCode = new ArrayList<>();
    ArrayList<String> busStopNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busstop_activity);
    }


    public void showBusStopViaName(View view)
    {
        final Spinner choices = (Spinner) findViewById(R.id.spinner_busstop);

final Object type = this;
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
                        List<NaptanBusStop> stops = response.getMessage().getData();
                        if (stops == null){
                            Log.d("Velocity","No values found.");
                            busStopInput.setHint("Error. Stop Name Does Not Exist");
                            return;
                        }

                        if(!ATCOCode.isEmpty()) {
                            ATCOCode.removeAll(ATCOCode);
                            busStopNames.removeAll(busStopNames);
                        }
                        for (NaptanBusStop stop : stops) {
                            Log.d("Velocity Result", stop.getName());

                            busStopNames.add(stop.getName() + " " + stop.getLocality());
                           ATCOCode.add(stop.getAtcoCode());

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((Context) type, android.R.layout.simple_spinner_item, busStopNames);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choices.setAdapter(arrayAdapter);
                        choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                TableLayout tb = (TableLayout) findViewById(R.id.score_table);
                                tb.removeAllViews();
                                TableRow tr = new TableRow((Context) type);
                                TextView test = new TextView((Context) type);
                                test.setText(ATCOCode.get(position).toString());
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

    public void getTimesViaATCO()
    {

    }
}
