package uk.ac.tees.honeycomb.velocity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class QrDetails extends AppCompatActivity {

    TextView qrName, qrDate;
    ImageView qrContent;
    String name, date;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_details);
        qrName = (TextView) findViewById(R.id.detailsName);
        qrDate = (TextView) findViewById(R.id.detailsDate);
        qrContent = (ImageView) findViewById(R.id.detailsImage);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        date = i.getStringExtra("date");
        bm = BitmapFactory.decodeByteArray(
                i.getByteArrayExtra("content"),0,i.getByteArrayExtra("content").length);
        qrContent.setImageBitmap(bm);

        qrName.setText(name);
        qrDate.setText(date);


    }
}
