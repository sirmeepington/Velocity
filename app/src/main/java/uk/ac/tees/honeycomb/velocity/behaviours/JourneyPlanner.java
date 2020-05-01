package uk.ac.tees.honeycomb.velocity.behaviours;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.JourneyFromStops;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Journey;
import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.JourneyRoute;
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

            AlertDialog.Builder errEmptyFrom = new AlertDialog.Builder(getContext());
            errEmptyFrom.setMessage("Error: Please make sure the 'From' and 'To' fields are filled.");
            errEmptyFrom.show();

        }else if (!fromSearchCheck || !toSearchCheck){

            AlertDialog.Builder errNoSearch = new AlertDialog.Builder(getContext());
            errNoSearch.setMessage("Please search and select a bus stop.");
            errNoSearch.show();

        }else{
            JourneySearch();
        }
    }

    private void JourneySearch(){
        JourneyFromStops jfs = new JourneyFromStops(getContext(),busStopFrom,busStopTo);
        AlertDialog.Builder temp = new AlertDialog.Builder(getContext());
        temp.setMessage("fuck you");
        temp.show();
        jfs.query(response -> {
            Journey jpResult = (Journey) response.getMessage().getJourney();




            final ListView resultList = parentView.findViewById(R.id.jpListView);
            final ArrayAdapter<JourneyRoute> resultAdapter = new ArrayAdapter<JourneyRoute>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, jpResult.getRoutes());
            resultList.setAdapter(resultAdapter);
            resultList.setOnItemClickListener((parent, view, position, id) -> {

            });

        }, error -> {});
    }




}
