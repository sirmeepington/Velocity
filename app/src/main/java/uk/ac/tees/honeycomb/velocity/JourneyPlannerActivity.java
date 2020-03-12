package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.util.ArrayUtils;


import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class JourneyPlannerActivity extends AppCompatActivity {

    ArrayList<String> fromList = new ArrayList<>();
    ArrayList<String> fromListLonglat = new ArrayList<>();
    ArrayList<String> toList = new ArrayList<>();
    ArrayList<String> toListLongLat = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_planner);
    }

    public void jpConfirm(View view){
        final EditText fromInput = (EditText) findViewById(R.id.jpFrom);
        String fromInputText = fromInput.getText().toString();

        final EditText toInput = (EditText) findViewById(R.id.jpTo);
        String toInputText =  toInput.getText().toString();

        final StopByName fromStop = new StopByName(this, fromInputText);
        final StopByName toStop = new StopByName(this, toInputText);

        final ListView fromDisplayedBusList = (ListView) findViewById(R.id.jpListView);
        final ListView toDisplayedBusList = (ListView) findViewById(R.id.jpListView);

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
                                fromListLonglat.add("Longlat: " + s.getLocation().getLongitude() + ", " + s.getLocation().getLatitude());
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
            toStop.query(new Response.Listener<ImpetusResponse<StopByNameResponse>>() {
                @Override
                public void onResponse(ImpetusResponse<StopByNameResponse> response) {
                    ArrayList<NaptanBusStop> toBusList = (ArrayList<NaptanBusStop>) response.getMessage().getData();

                    for (NaptanBusStop s: toBusList){
                        toList.add(s.getName());
                        toListLongLat.add ("Longlat: " + s.getLocation().getLongitude() + ", " + s.getLocation().getLatitude());
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

    public void jpSwitch (View view) {
        EditText from = (EditText) findViewById(R.id.jpFrom);
        String strFrom = from.getText().toString();
        EditText to = (EditText) findViewById(R.id.jpTo);
        String strTo = to.getText().toString();

        from.setText(strTo);
        to.setText(strFrom);
    }
    public boolean checkFromText(){
        EditText from = (EditText) findViewById(R.id.jpFrom);
        String fromText = from.getText().toString();

        if(fromText.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkToText(){
        EditText to = (EditText) findViewById(R.id.jpTo);
        String toText = to.getText().toString();

        if(toText.isEmpty()){
            return true;
        }else{
            return false;
        }
    }



}