package uk.ac.tees.honeycomb.velocity.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uk.ac.tees.honeycomb.velocity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QrDetailsFragment extends Fragment {


    public QrDetailsFragment() {
        // Required empty public constructor
    }

    TextView qrName, qrDate;
    ImageView qrContent;
    Bitmap bm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View parentView = inflater.inflate(R.layout.fragment_qr_details, container, false);
        qrName = (TextView) parentView.findViewById(R.id.detailsName);
        qrDate = (TextView) parentView.findViewById(R.id.detailsDate);
        qrContent = (ImageView) parentView.findViewById(R.id.detailsImage);

        return parentView;
    }

}
