package uk.ac.tees.honeycomb.velocity.behaviours;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    private String cacheInput = null;


    public BusStopTimetable(View parentView){
        this.parentView = parentView;
        createListeners(parentView);
    }

    private void createListeners(View view){
        EditText edtTxt = parentView.findViewById(R.id.busStopInput);
        edtTxt.setText(cacheInput);
        Button confirmButton = view.findViewById(R.id.confirm_busstop);
        confirmButton.setOnClickListener((view1) -> redirectButton());
        makeSpinnerVisible(false);
        makeProggressBarvisible(false);
    }

    public void redirectButton() {

        final EditText busStopInput = parentView.findViewById(R.id.busStopInput);
        cacheInput = busStopInput.getText().toString();

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
private void removeArrayElements()
{
    atco.removeAll(atco);
    loadedStops.clear();
}

    private void showBusStopsViaPostCode() {
        removeArrayElements();
        makeProggressBarvisible(true);

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
                        makeProggressBarvisible(false);
                        TableLayout tb = parentView.findViewById(R.id.score_table);
                        tb.removeAllViews();
                        AddRow(getViewContext(),tb,"Apologies, No Bus Stops Can be Found With" +
                                "\nZIP Code");
                        clearSpinner();
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
                    makeProggressBarvisible(false);
                    makeSpinnerVisible(true);
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
                error -> { Log.e("Velocity", "Error" + error);
                    makeProggressBarvisible(false);

                    TableLayout tb = parentView.findViewById(R.id.score_table);
                    tb.removeAllViews();
                    AddRow(getViewContext(),tb,"Apologies, Service Appears To be Down." +
                            "\nTry Again Later! ");}
        );

    }


    private void showBusStopViaName() {
        removeArrayElements();
        makeProggressBarvisible(true);
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
                        TableLayout tb = parentView.findViewById(R.id.score_table);
                        tb.removeAllViews();
                        AddRow(getViewContext(),tb,"No Bus Stops Found with that Name.");
                        makeProggressBarvisible(false);
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
                    makeProggressBarvisible(false);
                    makeSpinnerVisible(true);
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
                error -> { Log.e("Velocity", "Error" + error);
                    makeProggressBarvisible(false);
                    TableLayout tb = parentView.findViewById(R.id.score_table);
                    tb.removeAllViews();
                    AddRow(getViewContext(),tb,"Apologies, Service Appears To be Down." +
                            "\nTry Again Later! ");}
        );

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
                        String operatorName = departure.getOperatorName() == null ? "No Name Specified" : departure.getOperatorName();
                        String ws1 ="";
                        String ws2 ="";

                        StringBuilder sb1 = new StringBuilder();
                        int t = departure.getLineName().length();
                        sb1.append(new String(new char[32]).replace('\0',' ').substring((t > 1) ? t * 2 : t));
                        String secondSpacing = new String(new char[15]).replace("\0"," ");
                        AddRow(getViewContext(), tb, departure.getLineName() + sb1.toString() + departure.getAimedDepartureTime() + secondSpacing + operatorName);
                    }
                },
                error -> { Log.e("Velocity", "Error" + error);
                    makeProggressBarvisible(false);
                    TableLayout tb = parentView.findViewById(R.id.score_table);
                    tb.removeAllViews();
                    AddRow(getViewContext(),tb,"Apologies, Service Appears To be Down." +
                            "\nTry Again Later! ");}
        );
    }

    private void makeSpinnerVisible(boolean visible)
    {
        final Spinner choices = parentView.findViewById(R.id.spinner_busstop);
        choices.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void makeProggressBarvisible(boolean visible)
    {
        final ProgressBar loadingbar = parentView.findViewById(R.id.progressBar);
        loadingbar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void clearSpinner()
    {
        Spinner choices = parentView.findViewById(R.id.spinner_busstop);
        choices.removeAllViews();
    }
    private void AddRow(Context context, TableLayout layout, String text){
        TableRow row = new TableRow(context);
        TextView textView = new TextView(context);
        textView.setText(text);
        row.addView(textView);
        layout.addView(row);
    }

}
