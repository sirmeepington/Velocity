package uk.ac.tees.honeycomb.velocity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BusStopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busstop_activity);
    }

    private void addingToCD(){

    }

    public void showBusStop(View view)
    {
        EditText editText = (EditText) findViewById(R.id.busStopInput);
        String message = editText.getText().toString();



        TableLayout tb = (TableLayout) findViewById(R.id.Table);
        tb.removeAllViews();
        TableRow[] rows1 = new TableRow[5];
        TableRow[] rows2 = new TableRow[5];
      //  TableRow tr = (TableRow) findViewById(R.id.tr1);

        TextView test = new TextView(this);
        test.setText("Time");

        String[] textArray1 = {"Tower", "TCollegeroad", "Priston piece", "bustop1","bustop2"};
        String[] textArray2 = {"10:14", "10:30", "10:44", "10:50","10:58"};
        TextView[] t = new TextView[5];
        TextView[] t2 = new TextView[5];
        TableRow bus = new TableRow(this);

        TextView bust = new TextView(this);
        TextView timet = new TextView(this);
        timet.setText("Time");
        bust.setText("BusStop");


        bus.addView(timet);
        bus.addView(bust);
        bus.setPaddingRelative(200,0,100,400);
        tb.addView(bus);
        for (int i = 0; i < 5; i++) {
            rows1[i] = new TableRow(this);

            rows2[i] = new TableRow(this);

            t[i] = new TextView(this);
            t2[i] = new TextView(this);
            t[i].setText(textArray1[i]);
            t2[i].setText(textArray2[i]);
            rows1[i].addView(t[i]);
            rows1[i].setPadding(100, 10, 250, 0);
            rows1[i].addView(t2[i]);

            tb.addView(rows1[i]);


        }

    }
}
