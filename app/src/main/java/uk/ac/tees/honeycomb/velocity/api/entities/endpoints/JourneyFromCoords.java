package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.JourneyResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;

/**
 * The API endpoint for the Journey Planner feature; allowing a journey to be fetched from
 * two coordinates.
 * The coordinates given must be in the format of `lonlat:longitude,latitude` where both the
 * longitude and latitude are decimal coordinates to 5 decimal places (6 s.f).
 * Returns a {@link JourneyResponse} with the relevant data.
 * To get a journey from two gathered stops use {@link JourneyFromStops}.
 *
 * @see JourneyFromStops
 * @see JourneyResponse
 * @see Endpoint
 *
 * @author Aidan
 * @since 07/04/20
 */
public class JourneyFromCoords extends Endpoint<JourneyResponse> {

    /**
     * Creates a JourneyFromCoords endpoint accessor with the two coordinates given.
     * @param context The application context. This is usually {@code this}.
     * @param from The starting coordinate in the `lonlat` format.
     * @param to The ending coordinate in the `lonlat` format.
     */
    public JourneyFromCoords(Context context, String from, String to){
        super(context,"/api/journey?from="+from+"&to="+to);
    }

    @Override
    public void query(Response.Listener<ImpetusResponse<JourneyResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<JourneyResponse>> request = new ImpetusRequest<>(
                getFullUrl(),
                new TypeToken<ImpetusResponse<JourneyResponse>>(){},
                listener,
                errorListener
        );
        Impetus.getInstance(context).addRequest(request);
    }
}
