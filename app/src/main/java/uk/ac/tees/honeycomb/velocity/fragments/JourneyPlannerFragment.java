package uk.ac.tees.honeycomb.velocity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.tees.honeycomb.velocity.R;
import uk.ac.tees.honeycomb.velocity.behaviours.JourneyPlanner;

public class JourneyPlannerFragment extends Fragment {

    private JourneyPlanner behaviour;

    public JourneyPlannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journey_planner, container, false);
        behaviour = new JourneyPlanner(view);
        return view;
    }

}
