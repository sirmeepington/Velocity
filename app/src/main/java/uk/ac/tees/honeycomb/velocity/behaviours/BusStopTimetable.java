package uk.ac.tees.honeycomb.velocity.behaviours;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByPostcode;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopTimetable;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Departure;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class BusStopTimetable implements Behaviour {

    private final View parentView;
    private HashMap<String, String> loadedStops = new HashMap<>();
    private ArrayList<String> atco = new ArrayList<>();

    public BusStopTimetable(View parentView){
        this.parentView = parentView;
        createListeners(parentView);
    }

    private void createListeners(View view){
        Button confirmButton = view.findViewById(R.id.confirm_busstop);
        confirmButton.setOnClickListener((view1) -> redirectButton());
    }

    public void redirectButton() {
        final EditText busStopInput = parentView.findViewById(R.id.busStopInput);

        if (validatePostCode(busStopInput.getText().toString())) {
            showBusStopsViaPostCode();
        } else {
            showBusStopViaName();
        }
    }

    private Context getViewContext(){
        return parentView.getContext();
    }

    private boolean validatePostCode(String postCode) {
        Pattern pattern = Pattern.compile("([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})");
        return pattern.matcher(postCode).matches();

    }

    private void showBusStopsViaPostCode() {
        final Spinner choices = parentView.findViewById(R.id.spinner_busstop);

        final EditText busStopInput = parentView.findViewById(R.id.busStopInput);
        final String message = busStopInput.getText().toString();

        final StopByPostcode SBP = new StopByPostcode(getViewContext(), message);
        SBP.query(
                response -> {
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

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getViewContext(), android.R.layout.simple_spinner_item, new ArrayList<>(loadedStops.values()));
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    choices.setAdapter(arrayAdapter);
                    choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                            getTimesViaATCO(atco.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) { }
                    });

                },
                error -> Log.e("Velocity", "Error" + error));
    }

    private void showBusStopViaName() {
        final Spinner choices = parentView.findViewById(R.id.spinner_busstop);

        final EditText busStopInput = parentView.findViewById(R.id.busStopInput);
        final String message = busStopInput.getText().toString();

        final StopByName SBN = new StopByName(getViewContext(), message);
        busStopInput.setHint("Loading");


        SBN.query(
                response -> {
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

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getViewContext(), android.R.layout.simple_spinner_item, new ArrayList<>(loadedStops.values()));
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    choices.setAdapter(arrayAdapter);
                    choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                            String code = atco.get(position);
                            Log.d("ATCO Code:",code);
                            getTimesViaATCO(code);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) { }
                    });

                },
                error -> Log.e("Velocity", "Error" + error));

    }

    private void getTimesViaATCO(String atco) {

        final StopTimetable stopTimetable = new StopTimetable(getViewContext(), atco);

        stopTimetable.query(
                response -> {
                    ArrayList<Departure> departures = response.getMessage().getStopTimetable().getDepartures();
                    TableLayout tb = parentView.findViewById(R.id.score_table);
                    tb.removeAllViews();
                    if (departures == null || departures.isEmpty()){
                        AddRow(getViewContext(),tb,"No Departures Listed.");
                        return;
                    }

                    for(Departure departure : departures){



                        String ws1 ="";
                        String ws2 ="";

int t = departure.getLineName().length();
                        if(departure.getLineName().length() > 1)
                        {
                            for(int i = 1;i<32;i++)
                            {
                                ws1 += " ";
                            }

                            ws1 =  ws1.substring(departure.getLineName().length()*2,31);
                        }
                        else
                        {
                            for(int i = 1;i<32;i++)
                            {
                                ws1 += " ";
                            }

                            ws1 =  ws1.substring(departure.getLineName().length(),31);
                        }

                        for(int i = 0;i<15;i++)
                        {
                            ws2 += " ";
                        }
                        if(departure.getOperatorName() == "null" ||departure.getOperatorName() == null)
                        {
                            AddRow(getViewContext(),tb,departure.getLineName()+ ws1+departure.getAimedDepartureTime());
                        }
                        else {
                            AddRow(getViewContext(), tb, departure.getLineName() + ws1 + departure.getAimedDepartureTime() + ws2 + departure.getOperatorName());
                        }

                    }
                },
                error -> Log.e("Velocity", "Error: " + error)
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
