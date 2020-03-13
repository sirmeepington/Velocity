package uk.ac.tees.honeycomb.velocity.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import uk.ac.tees.honeycomb.velocity.R;


public class StopTimetableFragment extends Fragment {

    public StopTimetableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stop_timetable, container,false);
        Button button = view.findViewById(R.id.confirm_busstop);
        button.setOnClickListener((view1) -> redirectButton(view1));

        return inflater.inflate(R.layout.fragment_stop_timetable, container, false);
    }

    public void redirectButton(View view) {
        final EditText busStopInput = getView().findViewById(R.id.busStopInput);

        //if (validatePostCode(busStopInput.getText().toString())) {
        //    showBusStopsViaPostCode(view);
        //} else {
        //    showBusStopViaName(view);
        //}
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
