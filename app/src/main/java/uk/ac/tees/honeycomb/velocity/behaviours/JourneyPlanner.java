package uk.ac.tees.honeycomb.velocity.behaviours;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.entities.Location;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;


public class JourneyPlanner implements Behaviour {

    private final View parentView;
    private HashMap<String,String> from = new HashMap<>();
    private HashMap<String,String> to = new HashMap<>();

    public JourneyPlanner(View parentView){
        this.parentView = parentView;
        createListeners(parentView);
    }

    private void createListeners(View view){
        Button switchButton = view.findViewById(R.id.jpSwitch);
        switchButton.setOnClickListener(v -> switchValues());

        Button confirmButton = view.findViewById(R.id.jpConfirm);
        confirmButton.setOnClickListener(v -> jpConfirm());

    }

    private Context getContext(){
        return parentView.getContext();
    }

    private <T> T getCollectionAtIndex(Collection<T> coll, int index){
        for(int i = 0; i < index; i++){
            coll.iterator().next();
        }
        return coll.iterator().next();
    }

    public void jpConfirm(){
        final EditText fromInput = parentView.findViewById(R.id.jpFrom);
        String fromInputText = fromInput.getText().toString();

        final EditText toInput = parentView.findViewById(R.id.jpTo);
        String toInputText =  toInput.getText().toString();

        final StopByName fromStop = new StopByName(getContext(), fromInputText);
        final StopByName toStop = new StopByName(getContext(), toInputText);

        final ListView fromDisplayedBusList = parentView.findViewById(R.id.jpListView);
        final ListView toDisplayedBusList = parentView.findViewById(R.id.jpListView);

        if (checkFromText() || checkToText()){
            AlertDialog.Builder errEmptyFrom = new AlertDialog.Builder(getContext());
            errEmptyFrom.setMessage("Error: Please make sure the 'From' and 'To' fields are filled.");
            errEmptyFrom.show();
        }else{
            fromInput.requestFocus();

            fromStop.query(
                    response -> {
                        ArrayList<NaptanBusStop> fromBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();


                        for(NaptanBusStop s : fromBusList){
                            from.put(s.getName(),makeLonLat(s.getLocation()));
                        }
                        final ArrayAdapter<String> fromBusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>(from.keySet()));
                        fromDisplayedBusList.setAdapter(fromBusAdapter);
                        fromDisplayedBusList.setOnItemClickListener((parent, view12, position, id) -> {
                            //saving long and lat of selected bus stop.
                            String fromLonglatSelected = getCollectionAtIndex(from.values(),position);

                            //String fromBusStopName = (String) fromDisplayedBusList.getItemAtPosition(position);
                            Toast.makeText(getContext().getApplicationContext(), fromLonglatSelected, Toast.LENGTH_LONG).show();
                            fromInput.setText(getCollectionAtIndex(from.keySet(),position));
                            fromBusAdapter.clear();
                        });


                    },
                    error -> { }
            );
            toStop.query(
                    response -> {
                        ArrayList<NaptanBusStop> toBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();

                        for (NaptanBusStop s: toBusList){
                            to.put(s.getName(),makeLonLat(s.getLocation()));
                        }
                        final ArrayAdapter<String> toBusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,new ArrayList<String>(to.keySet()));
                        toDisplayedBusList.setAdapter(toBusAdapter);
                        toDisplayedBusList.setOnItemClickListener((parent, view1, position, id) -> {
                            String toLonglatSeleted = getCollectionAtIndex(to.values(),position);
                            Toast.makeText(getContext().getApplicationContext(), toLonglatSeleted, Toast.LENGTH_LONG).show();
                            toInput.setText(getCollectionAtIndex(to.keySet(),position));
                            toBusAdapter.clear();
                        });
                    },
                    error -> { }
            );
        }
    }

    private String makeLonLat(Location location){
        return String.format(Locale.ENGLISH,"lonlat:%f,%f", location.getLongitude(), location.getLatitude());
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




}
