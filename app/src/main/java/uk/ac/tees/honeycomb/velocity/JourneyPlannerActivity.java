package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

public class JourneyPlannerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_planner);
    }

    public void jpConfirm(View view){

        if(checkFromText() == false) {
            final ListView journey = (ListView) findViewById(R.id.busStops);

            String[] busStops = {"BusStop1", "busStop2"};
            Time[] busStopTimes = {};
            ArrayAdapter<String> bsAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busStops);
            journey.setAdapter(bsAdp);
            journey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int serviceNo = position;
                    String busStopName = (String) journey.getItemAtPosition(position);

                    Toast.makeText(getApplicationContext(), serviceNo + " " + busStopName, Toast.LENGTH_LONG).show();
                }
            });
        }else{
            AlertDialog.Builder errEmptyFrom = new AlertDialog.Builder(this);
            errEmptyFrom.setMessage("Error: Please make sure the 'From' field contains text.");
            errEmptyFrom.show();
            }
        }


    public void jpSwitch(View view) {
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

}
