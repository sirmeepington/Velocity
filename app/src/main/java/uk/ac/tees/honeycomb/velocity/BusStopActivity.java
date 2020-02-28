package uk.ac.tees.honeycomb.velocity;

import android.graphics.Typeface;
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



        TableLayout tb = (TableLayout) findViewById(R.id.score_table);
        tb.removeAllViews();
        TableRow[] rows1 = new TableRow[5];
        TableRow[] rows2 = new TableRow[5];
      //  TableRow tr = (TableRow) findViewById(R.id.tr1);

        TextView test = new TextView(this);
        test.setText("Time");

        String[] textArray1 = {"8", "5A", "10", "14","62A"};
        String[] textArray2 = {"10:14", "10:30", "10:44", "10:50","10:58"};
        String[] textArray3 = {"Arriva","StageCoach" ,"Arriva","megaBus","National Holidays"};
        TextView[] t = new TextView[5];
        TextView[] t2 = new TextView[5];
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
        }

    }
}
