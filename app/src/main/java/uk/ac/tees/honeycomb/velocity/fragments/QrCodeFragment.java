package uk.ac.tees.honeycomb.velocity.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.entities.RecViewAdapter;

public class QrCodeFragment extends Fragment {

    static ImageView qrCodeImage;

    RecyclerView rv;
    RecViewAdapter adapter;

    public static ArrayList<String> qrName;
    public static ArrayList<String> qrDate;
    public static ArrayList<Bitmap> qrImage;

    private static View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_qrcode, container,false);

        loadData();

        adapter = new RecViewAdapter(parentView.getContext(), qrName, qrDate, qrImage);

        rv = parentView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(parentView.getContext()));
        rv.setAdapter(adapter);

        return parentView;
    }


    public static void generateQRCode(String content, String title){
        //qr code

        int dim = 300;
        Bitmap bm;
        QRGEncoder qrge = new QRGEncoder(content, QRGContents.Type.TEXT,dim);
        qrge.setColorBlack(Color.BLACK);
        qrge.setColorWhite(Color.WHITE);
        try{
            bm = qrge.getBitmap();
            qrImage.add(bm);
            qrCodeImage.setImageBitmap(bm);

        }catch(Exception e){
             //
        }
        //date
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        String date = df.format(d);

        //add to arraylists

        qrName.add(title);
        qrDate.add(date);

        saveData();

        //Arraylists go here
        //1 of type string and 2 of type Image.
    }



    public void onResume() {

        super.onResume();
        //adapter.notifyDataSetChanged();
        saveData();
    }

    public static void saveData(){
        SharedPreferences sharedPreferences = parentView.getContext().getSharedPreferences("Shared preferences", Context.MODE_PRIVATE); //no app can change shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String jsonName = gson.toJson(qrName);

        String jsonDate = gson.toJson(qrDate);

        ArrayList<String> imageToString = new ArrayList<>();

        //temp solution

        for(int i = 0; i < qrImage.size(); i++)
        {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Bitmap bm = qrImage.get(i);
            bm.compress(Bitmap.CompressFormat.PNG, 100, output);
            byte[] b = output.toByteArray();
            String temp = Base64.encodeToString(b, Base64.DEFAULT);
            imageToString.add(temp);
        }

        String jsonImage = gson.toJson(imageToString);

        editor.putString("QRName", jsonName);
        editor.putString("QRDate", jsonDate);
        editor.putString("QRImage", jsonImage);

        editor.apply(); //save to shared preferences
    }

    public void loadData(){
        SharedPreferences sharedPreferences = parentView.getContext().getSharedPreferences("Shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String jsonName = sharedPreferences.getString("QRName", null);
        String jsonDate = sharedPreferences.getString("QRDate", null);
        String jsonImage = sharedPreferences.getString("QRImage", null);

        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        qrName = gson.fromJson(jsonName, type);
        qrDate = gson.fromJson(jsonDate, type);

        ArrayList<String> backToImage = gson.fromJson(jsonImage, type);

        qrImage = new ArrayList<>();

        if(backToImage != null){
            for(int i = 0; i < backToImage.size(); i++)
            {
                byte[] encodeByte = Base64.decode(backToImage.get(i), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                qrImage.add(i, bitmap);
            }
        }

        if(qrDate == null){
            qrDate = new ArrayList<>();
        }

        if(qrName == null){
            qrName = new ArrayList<>();
        }
    }
}
