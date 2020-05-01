package uk.ac.tees.honeycomb.velocity.entities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import uk.ac.tees.honeycomb.velocity.QrDetails;
import uk.ac.tees.honeycomb.velocity.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView tQrName, tQrDate;
    ImageView iQrContent;

    public ViewHolder(@NonNull View itemView, List<Bitmap> qrContent, List<String> qrName, List<String> qrDate) {
        super(itemView);

        itemView.setOnClickListener(v -> {
            Bitmap b = qrContent.get(getAdapterPosition());
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, ba);

            Intent i = new Intent(v.getContext(), QrDetails.class );
            i.putExtra("name", qrName.get(getAdapterPosition()));
            i.putExtra("date", qrDate.get(getAdapterPosition()));
            i.putExtra("content", ba.toByteArray());

            v.getContext().startActivity(i);
        });

        tQrName = itemView.findViewById(R.id.qrCodeName);
        tQrDate = itemView.findViewById(R.id.qrCodeDate);
        iQrContent = itemView.findViewById(R.id.qrCodeImage);
    }
}