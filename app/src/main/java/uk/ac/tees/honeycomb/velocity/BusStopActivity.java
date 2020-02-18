package uk.ac.tees.honeycomb.velocity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.HashMap;

public class BusStopActivity extends AppCompatActivity {
    //Implement interface BusStopActivity

    private String id = new String();
    private String busStopName = new String();

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



        CardView ct = (CardView) findViewById(R.id.cd);
        String[] textArray = {"One", "Two", "Three", "Four"};

        TextView[] t = new TextView[10];

for(int i=0;i<10;i++) {

    t[i] = new TextView(this);
    t[i].setText(message + i);
    t[i].setBackgroundColor(Color.RED);
    ct.addView(t[i]);

}

    }

}
