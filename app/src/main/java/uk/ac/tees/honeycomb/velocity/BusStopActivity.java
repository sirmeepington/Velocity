package uk.ac.tees.honeycomb.velocity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.provider.Telephony;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByPostcode;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopFromAtcoCode;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopTimetable;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.BusStopTimetableResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByPostcodeResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopFromAtcoCodeResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Departure;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class BusStopActivity extends AppCompatActivity {

    final Context type = this;
    HashMap<String, String> loadedStops = new HashMap<>();
    ArrayList<String> atco = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busstop_activity);
    }

    public void redirectButton(View view) {
        final EditText busStopInput = findViewById(R.id.busStopInput);

        if (validatePostCode(busStopInput.getText().toString())) {
            showBusStopsViaPostCode(view);
        } else {
            showBusStopViaName(view);
        }
    }

    public boolean validatePostCode(String postCode) {
        Pattern pattern = Pattern.compile("([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})");
        return pattern.matcher(postCode).matches();

    }

    public void showBusStopsViaPostCode(View view) {
        final Spinner choices = findViewById(R.id.spinner_busstop);

        final EditText busStopInput = findViewById(R.id.busStopInput);
        final String message = busStopInput.getText().toString();

        final StopByPostcode SBP = new StopByPostcode(this, message);
        SBP.query(
            new Response.Listener<ImpetusResponse<StopByPostcodeResponse>>() {
                @Override
                public void onResponse(ImpetusResponse<StopByPostcodeResponse> response) {
                    Log.d("Velocity", response.getMessage().getSources().toString());
                    busStopInput.setHint("Loaded Successfully");
                    final List<NaptanBusStop> stops = response.getMessage().getData();
                    if (stops == null) {
                        Log.d("Velocity", "No values found.");
                        busStopInput.setHint("Error. Stop Name Does Not Exist");
                        return;
                    }

                    if (!loadedStops.isEmpty()) {
                        loadedStops.clear();
                    }
                    for (NaptanBusStop stop : stops) {
                        Log.d("Velocity Result", stop.getName());

                        loadedStops.put(stop.getAtcoCode().toLowerCase(), stop.getName() + " - " + stop.getLocality());
                        atco.add(stop.getAtcoCode().toLowerCase());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(type, android.R.layout.simple_spinner_item, new ArrayList<>(loadedStops.values()));
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    choices.setAdapter(arrayAdapter);
                    choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getTimesViaATCO(atco.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) { }
                    });

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Velocity", "Error" + error);
                }


            });
    }

    public void showBusStopViaName(View view) {
        final Spinner choices = findViewById(R.id.spinner_busstop);

        final EditText busStopInput = findViewById(R.id.busStopInput);
        final String message = busStopInput.getText().toString();

        final StopByName SBN = new StopByName(this, message);
        busStopInput.setHint("Loading");
        busStopInput.setText("");

        SBN.query(
            new Response.Listener<ImpetusResponse<StopByNameResponse>>() {
                @Override
                public void onResponse(final ImpetusResponse<StopByNameResponse> response) {
                    Log.d("Velocity", response.getMessage().getDataSource());
                    busStopInput.setHint("Loaded Successfully");
                    final List<NaptanBusStop> stops = response.getMessage().getData();
                    if (stops == null || stops.isEmpty()) {
                        Log.d("Velocity", "No values found.");
                        busStopInput.setHint("Error. Stop Name Does Not Exist");
                        return;
                    }

                    if (!loadedStops.isEmpty()) {
                        loadedStops.clear();
                    }
                    for (NaptanBusStop stop : stops) {
                        Log.d("Velocity Result", stop.getName());

                        loadedStops.put(stop.getAtcoCode().toLowerCase(), stop.getName() + " - " + stop.getLocality());
                        atco.add(stop.getAtcoCode().toLowerCase());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(type, android.R.layout.simple_spinner_item, new ArrayList<>(loadedStops.values()));
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    choices.setAdapter(arrayAdapter);
                    choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getTimesViaATCO(atco.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) { }
                    });

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Velocity", "Error" + error);
                }


            });

    }

    public void getTimesViaATCO(String atco) {

        final StopTimetable stopTimetable = new StopTimetable(this, atco);

        stopTimetable.query(
            new Response.Listener<ImpetusResponse<BusStopTimetableResponse>>() {
                @Override
                public void onResponse(final ImpetusResponse<BusStopTimetableResponse> response) {
                    ArrayList<Departure> departures = response.getMessage().getStopTimetable().getDepartures();
                    TableLayout tb = findViewById(R.id.score_table);
                    tb.removeAllViews();
                    if (departures == null || departures.isEmpty()){
                        AddRow(type,tb,"No Departures Listed.");
                        return;
                    }

                    for(Departure departure : departures){
                        AddRow(type,tb,departure.getLineName()+" by "+departure.getOperatorName()+" at "+departure.getAimedDepartureTime());
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Velocity", "Error: " + error);
                }
            }
        );
    }

    private void AddRow(Context context, TableLayout layout, String text){
        TableRow row = new TableRow(context);
        TextView textView = new TextView(context);
        textView.setText(text);
        row.addView(textView);
        layout.addView(row);
    }
}