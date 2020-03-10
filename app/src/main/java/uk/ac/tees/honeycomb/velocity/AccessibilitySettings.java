package uk.ac.tees.honeycomb.velocity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AccessibilitySettings extends AppCompatActivity
{

    private static final AccessibilitySettings instance = new AccessibilitySettings();
    private float textSize = 14;

    private AccessibilitySettings(){}

    public static AccessibilitySettings getInstance()
    {
        return instance;
    }

    public float getTextSize()
    {
        return textSize;
    }

    public void setTextSize(Float size)
    {

        if(size < 12 || size > 64)
        {
            TextView text = (TextView) findViewById(R.id.yeetus);
            text.setText("Error");
        }
        else
        {
            this.textSize = size;
        }
    }

}
