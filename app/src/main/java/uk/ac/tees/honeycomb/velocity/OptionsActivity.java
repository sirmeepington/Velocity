package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    AccessibilitySettings copy = AccessibilitySettings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_options);

        Intent intent = getIntent();

        TextView editText = findViewById(R.id.yeetus);
        editText.setTextSize(copy.getTextSize());

    }

    public void confirm(View view)
    {
        EditText editText = (EditText) findViewById(R.id.inputText);

        boolean validation = true;
        float edtTxt = copy.getTextSize();
        try
        {
            edtTxt = Float.parseFloat(editText.getText().toString());
        }
        catch (NumberFormatException e)
        {
            validation = false;
        }

        if(validation != false) {
            copy.setTextSize(edtTxt);
            finish();
            startActivity(getIntent());
        }
    }


}
