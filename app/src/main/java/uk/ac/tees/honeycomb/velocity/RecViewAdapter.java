package uk.ac.tees.honeycomb.velocity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    List<String> qrName;
    List<String> qrDate;
    List<Bitmap> qrContent;

    RecViewAdapter(Context context, List<String> qrName, List<String> qrDate, List<Bitmap>qrContent){
        this.layoutInflater = LayoutInflater.from(context);
        this.qrName = qrName;
        this.qrDate = qrDate;
        this.qrContent = qrContent;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.qrcodelistitem, parent, false);
        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tQrName, tQrDate;
        ImageView iQrContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap b;
                    b = qrContent.get(getAdapterPosition());
                    ByteArrayOutputStream ba = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.JPEG, 100, ba);




                    Intent i = new Intent(v.getContext(),QrDetails.class );
                    i.putExtra("name", qrName.get(getAdapterPosition()));
                    i.putExtra("date", qrDate.get(getAdapterPosition()));
                    i.putExtra("content", ba.toByteArray());
                    v.getContext().startActivity(i);

                }
            });

            tQrName = itemView.findViewById(R.id.qrCodeName);
            tQrDate = itemView.findViewById(R.id.qrCodeDate);
            iQrContent = itemView.findViewById(R.id.qrCodeImage);
        }
    }
}
