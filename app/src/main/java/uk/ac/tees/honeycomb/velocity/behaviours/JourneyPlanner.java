package uk.ac.tees.honeycomb.velocity.behaviours;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.JourneyFromStops;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Journey;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.JourneyRoute;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.RoutePart;
import uk.ac.tees.honeycomb.velocity.stops.BusStop;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;



public class JourneyPlanner implements Behaviour {

    private final View parentView;
    BusStop busStopFrom;
    BusStop busStopTo;
    public boolean fromSearchCheck = false;
    public boolean toSearchCheck = false;

    public JourneyPlanner(View parentView){
        this.parentView = parentView;
        createListeners(parentView);
        showKeyboard();
    }

    private void showKeyboard(){
        EditText fromText = parentView.findViewById(R.id.jpFrom);
        fromText.requestFocus();
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(parentView.getWindowToken(),0);
    }
    private void showError(String text){
        AlertDialog.Builder errEmptyFrom = new AlertDialog.Builder(getContext());
        errEmptyFrom.setMessage(text);
        errEmptyFrom.show();
    }

    private void createListeners(View view){
        Button switchButton = view.findViewById(R.id.jpSwitch);
        switchButton.setOnClickListener(v -> switchValues());

        Button confirmButton = view.findViewById(R.id.jpConfirm);
        confirmButton.setOnClickListener(v -> jpConfirm());

        Button fromSearchButton = view.findViewById(R.id.jpFromSearch);
        fromSearchButton.setOnClickListener(v -> fromSearch());

        Button toSearchButton = view.findViewById(R.id.jpToSearch);
        toSearchButton.setOnClickListener(v -> toSearch());


    }

    private Context getContext(){
        return parentView.getContext();
    }


    public void switchValues() {

        EditText from = parentView.findViewById(R.id.jpFrom);
        String strFrom = from.getText().toString();
        EditText to = parentView.findViewById(R.id.jpTo);
        String strTo = to.getText().toString();

        from.setText(strTo);
        to.setText(strFrom);
    }

    private String getEditTextContents(int id){
        EditText txt = parentView.findViewById(id);
        return txt.getText().toString();
    }

    public boolean checkFromText(){
        return getEditTextContents(R.id.jpFrom).isEmpty();
    }

    public boolean checkToText(){
        return getEditTextContents(R.id.jpTo).isEmpty();
    }

    public void fromSearch(){


        hideKeyboard();
        final EditText fromInput = parentView.findViewById(R.id.jpFrom);
        final EditText toInput = parentView.findViewById(R.id.jpTo);
        String fromInputText = fromInput.getText().toString();
        final ListView fromDisplayedBusList = parentView.findViewById(R.id.jpListView);
        final StopByName fromStop = new StopByName(getContext(), fromInputText);
        fromInput.requestFocus();
        fromStop.query(
                response -> {
                    ArrayList<NaptanBusStop> fromBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();
                    ArrayList<String> fromBusListString = new ArrayList<>();

                    for (int i = 0; i != fromBusList.size(); i++){
                        fromBusListString.add(fromBusList.get(i).getName());
                    }

                    final ArrayAdapter<String> fromBusAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, fromBusListString);
                    fromDisplayedBusList.setAdapter(fromBusAdapter);
                    fromDisplayedBusList.setOnItemClickListener((parent, view, position, id) -> {
                        String fromBusStopName = (String) fromBusList.get(position).getName();
                        busStopFrom = fromBusList.get(position);
                        fromInput.setText(fromBusStopName);
                        showKeyboard();
                        toInput.requestFocus();
                        fromBusAdapter.clear();

                    });


                },
                error -> { }
        );
        fromSearchCheck = true;
    }

    public void toSearch(){
        final EditText toInput = parentView.findViewById(R.id.jpTo);
        String toInputText =  toInput.getText().toString();
        final StopByName toStop = new StopByName(getContext(), toInputText);
        final ListView toDisplayedBusList = parentView.findViewById(R.id.jpListView);

        toStop.query(
                response -> {
                    ArrayList<NaptanBusStop> toBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();
                    ArrayList<String> toBusListString = new ArrayList<>();
                    for (int i = 0; i != toBusList.size(); i++){
                        toBusListString.add(toBusList.get(i).getName());
                    }

                    final ArrayAdapter<String> toBusAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, toBusListString);
                    toDisplayedBusList.setAdapter(toBusAdapter);
                    toDisplayedBusList.setOnItemClickListener((parent, view, position, id) -> {
                        String toLonglatSelected = toBusList.get(position).getLocation().getLongLat();
                        String toBusStopName = (String) toBusList.get(position).getName();
                        busStopTo = toBusList.get(position);
                        toInput.setText(toBusStopName);
                        hideKeyboard();
                        toBusAdapter.clear();
                    });
                    toSearchCheck = true;

                },
                error -> { }
        );
    }

    public void jpConfirm(){
        hideKeyboard();
        if (checkFromText() || checkToText()){

            showError("Error: Please make sure the 'From' and 'To' fields are filled.");

        }else if (!fromSearchCheck || !toSearchCheck){

            showError("Please search and select a bus stop.");

        }else{
            JourneySearch();
        }
    }

    public void JourneySearch(){
        JourneyFromStops jfs = new JourneyFromStops(getContext(),busStopFrom,busStopTo);
        jfs.query(response -> {
            Journey jpResult = response.getMessage().getJourney();
            JourneyRoute[] jr = jpResult.getRoutes();
            ArrayList<String> jrToString = new ArrayList<>();
            for(int i = 0; i != jr.length; i++){
                String departure = jr[i].getDepartureTime();
                String arrival = jr[i].getArrivalTime();
                String travelTime = jr[i].getDuration();
                jrToString.add("Departure: " + departure + "\n" + "Arrival: " + arrival + "\n" + "Travel time: " + travelTime);
            }

            final ListView resultList = parentView.findViewById(R.id.jpListView);
            if (jpResult.getRoutes() != null) {

                final ArrayAdapter<String> resultAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, jrToString);
                resultList.setAdapter(resultAdapter);
                resultList.setOnItemClickListener((parent, view, position, id) -> {

                    try {
                        DateFormat df = new SimpleDateFormat("HH:mm:ss");
                        String departureTimeString = jr[position].getDepartureTime() + ":00";
                        Date currentTime = new Date();
                        Date departureTime = df.parse(departureTimeString);
                        String diff = checkDifference(departureTime, currentTime);
                        showError(diff);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                });
                Toast.makeText(parentView.getContext(), "Route found from "+jpResult.getSource(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(parentView.getContext(), "No Route Found, Sorry.", Toast.LENGTH_LONG).show();
            }

        }, error -> {});
    }

    public String checkDifference(Date d1, Date d2){
        String timeRemaining="Your bus departures in: ";

        long diff = d1.getTime() - d2.getTime();

        diff = diff / 1000;

        long hours = diff / (60 * 60) % 24;
        long minutes = diff / 60 % 60;
        if(hours != 0){
            timeRemaining += hours + " hours, ";
            timeRemaining += minutes + " minutes!";
        }else{
            timeRemaining += minutes + " minutes!";
        }


        return timeRemaining;
    }




}
