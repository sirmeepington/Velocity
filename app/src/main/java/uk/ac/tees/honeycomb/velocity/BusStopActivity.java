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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busstop_activity);
    }


    public void showBusStop(View view)
    {
        final Spinner choices = (Spinner) findViewById(R.id.spinner_busstop);

final Object type = this;
        final EditText editText = (EditText) findViewById(R.id.busStopInput);
        final String message = editText.getText().toString();

        final StopByName SBN = new StopByName(this,message);
        editText.setHint("Loading");
        editText.setText("");

        SBN.query(
                new Response.Listener<ImpetusResponse<StopByNameResponse>>() {
                    @Override
                    public void onResponse(ImpetusResponse<StopByNameResponse> response) {
                        Log.d("Velocity",response.getMessage().getDataSource());
                        List<NaptanBusStop> stops = response.getMessage().getData();
                        if (stops == null){
                            Log.d("Velocity","No values found.");
                            return;
                        }
                        ArrayList<String> arrayList = new ArrayList<>();
                        for (NaptanBusStop stop : stops) {
                            Log.d("Velocity Result", stop.getName());

                            arrayList.add(stop.getName() + " " + stop.getLocality());

                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((Context) type, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choices.setAdapter(arrayAdapter);
                        choices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                        Log.e("Velocity", "Error"+error);
                    }
                });



//editText.setText(list.get(list.size()).toString());

     /*
        tb.removeAllViews();

      //  TableRow tr = (TableRow) findViewById(R.id.tr1);

        TextView test = new TextView(this);
        test.setText("Time");

        String[] textArray1 = {"8", "5A", "10", "14","62A"};
        String[] textArray2 = {"10:14", "10:30", "10:44", "10:50","10:58"};
        String[] textArray3 = {"Arriva","StageCoach" ,"Arriva","megaBus","National Holidays"};

        TextView[] t3 = new TextView[5];
        TableRow bus = new TableRow(this);


      //  bus.setPaddingRelative(200,0,100,400);
        tb.addView(bus);
        for(int j = 0;j<6;j++) {
            for (int i = 0; i < 5; i++) {
                rows1[i] = new TableRow(this);

                rows2[i] = new TableRow(this);

                t[i] = new TextView(this);
                t2[i] = new TextView(this);
                t3[i] = new TextView(this);
                t[i].setText(textArray1[i]);
                t2[i].setText(textArray2[i]);
                t3[i].setText(textArray3[i]);
               t2[i].setPadding(290, 0, 0, 0);
                t3[i].setPadding(60, 0, 0, 0);
                rows1[i].addView(t[i]);
                // rows1[i].setPadding(100, 10, 250, 0);
                rows1[i].addView(t2[i]);
                rows1[i].addView(t3[i]);
                tb.addView(rows1[i]);


            }
        }*/

    }
}
