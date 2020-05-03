package uk.ac.tees.honeycomb.velocity.entities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import uk.ac.tees.honeycomb.velocity.QrDetails;
import uk.ac.tees.honeycomb.velocity.R;

public class RecViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater layoutInflater;
    List<String> qrName;
    List<String> qrDate;
    List<Bitmap> qrContent;

    public RecViewAdapter(Context context, List<String> qrName, List<String> qrDate, List<Bitmap>qrContent){
        this.layoutInflater = LayoutInflater.from(context);
        this.qrName = qrName;
        this.qrDate = qrDate;
        this.qrContent = qrContent;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.qrcodelistitem, parent, false);
        return new ViewHolder(view,qrContent,qrName,qrDate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = qrName.get(position);
        String date = qrDate.get(position);
        Bitmap content = qrContent.get(position);
        holder.tQrName.setText(name);
        holder.tQrDate.setText(date);
        holder.iQrContent.setImageBitmap(content);
    }

    @Override
    public int getItemCount() {
        return qrName.size();
    }



}
