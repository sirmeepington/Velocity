package uk.ac.tees.honeycomb.velocity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Locale;

import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;
import uk.ac.tees.honeycomb.velocity.entities.Location;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class JourneyPlannerActivity extends AppCompatActivity {

    ArrayList<String> fromList = new ArrayList<>();
    ArrayList<String> fromListLonglat = new ArrayList<>();
    ArrayList<String> toList = new ArrayList<>();
    ArrayList<String> toListLongLat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_journey_planner);
    }

    public void jpConfirm(View view){
        final EditText fromInput = findViewById(R.id.jpFrom);
        String fromInputText = fromInput.getText().toString();

        final EditText toInput = findViewById(R.id.jpTo);
        String toInputText =  toInput.getText().toString();

        final StopByName fromStop = new StopByName(this, fromInputText);
        final StopByName toStop = new StopByName(this, toInputText);

        final ListView fromDisplayedBusList = findViewById(R.id.jpListView);
        final ListView toDisplayedBusList = findViewById(R.id.jpListView);

        if (checkFromText() || checkToText()){
            AlertDialog.Builder errEmptyFrom = new AlertDialog.Builder(this);
            errEmptyFrom.setMessage("Error: Please make sure the 'From' and 'To' fields are filled.");
            errEmptyFrom.show();
        }else{
            fromInput.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

            fromStop.query(
                    new Response.Listener<ImpetusResponse<StopByNameResponse>>() {
                        @Override
                        public void onResponse(ImpetusResponse<StopByNameResponse> response) {
                            ArrayList<NaptanBusStop> fromBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();


                            for(NaptanBusStop s : fromBusList){
                                fromList.add(s.getName());
                                fromListLonglat.add(makeLonLat(s.getLocation()));
                            }
                            final ArrayAdapter<String> fromBusAdapter = new ArrayAdapter<>(JourneyPlannerActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, fromList);
                            fromDisplayedBusList.setAdapter(fromBusAdapter);
                            fromDisplayedBusList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //saving long and lat of selected bus stop.
                                    String fromLonglatSelected = fromListLonglat.get(position);

                                    //String fromBusStopName = (String) fromDisplayedBusList.getItemAtPosition(position);
                                    Toast.makeText(getApplicationContext(), fromLonglatSelected, Toast.LENGTH_LONG).show();
                                    fromInput.setText(fromList.get(position));
                                    fromBusAdapter.clear();
                                }

                            });


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            toStop.query(
                new Response.Listener<ImpetusResponse<StopByNameResponse>>() {
                    @Override
                    public void onResponse(ImpetusResponse<StopByNameResponse> response) {
                        ArrayList<NaptanBusStop> toBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();

                        for (NaptanBusStop s: toBusList){
                            toList.add(s.getName());
                            toListLongLat.add (makeLonLat(s.getLocation()));
                        }
                        final ArrayAdapter<String> toBusAdapter = new ArrayAdapter<>(JourneyPlannerActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1,toList);
                        toDisplayedBusList.setAdapter(toBusAdapter);
                        toDisplayedBusList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String toLonglatSeleted = toListLongLat.get(position);
                                //String busStopName = (String) toDisplayedBusList.getItemAtPosition(position);
                                Toast.makeText(getApplicationContext(), toLonglatSeleted, Toast.LENGTH_LONG).show();
                                toInput.setText(toList.get(position));
                                toBusAdapter.clear();
                            }
                        });
                    }
                },
                new Response.ErrorListener(){
                    @Override
                        public void onErrorResponse(VolleyError error){

                    }
                }
            );
        }
    }

    private String makeLonLat(Location location){
        return String.format(Locale.ENGLISH,"lonlat:%f,%f", location.getLongitude(), location.getLatitude());
    }

    public void jpSwitch (View view) {
        EditText from = findViewById(R.id.jpFrom);
        String strFrom = from.getText().toString();
        EditText to = findViewById(R.id.jpTo);
        String strTo = to.getText().toString();

        from.setText(strTo);
        to.setText(strFrom);
    }

    private String getEditTextContents(int id){
        EditText txt = findViewById(id);
        return txt.getText().toString();
    }

    public boolean checkFromText(){
        return getEditTextContents(R.id.jpFrom).isEmpty();
    }

    public boolean checkToText(){
        return getEditTextContents(R.id.jpTo).isEmpty();
    }



}