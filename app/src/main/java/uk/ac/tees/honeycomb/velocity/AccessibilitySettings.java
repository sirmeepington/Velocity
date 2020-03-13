package uk.ac.tees.honeycomb.velocity;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
