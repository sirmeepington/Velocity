package uk.ac.tees.honeycomb.velocity.entities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import uk.ac.tees.honeycomb.velocity.MainActivity;
import uk.ac.tees.honeycomb.velocity.QrDetails;
import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.fragments.QrDetailsFragment;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView tQrName, tQrDate;
    ImageView iQrContent,tempImage;
    QrDetailsFragment temp = new QrDetailsFragment();


    public ViewHolder(@NonNull View itemView, List<Bitmap> qrContent, List<String> qrName, List<String> qrDate) {
        super(itemView);

        itemView.setOnClickListener(v -> {


            Bitmap b = qrContent.get(getAdapterPosition());



        });

        tQrName = itemView.findViewById(R.id.qrCodeName);
        tQrDate = itemView.findViewById(R.id.qrCodeDate);
        iQrContent = itemView.findViewById(R.id.qrCodeImage);


    }



}