package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.JourneyResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;
import uk.ac.tees.honeycomb.velocity.entities.Location;
import uk.ac.tees.honeycomb.velocity.stops.BusStop;

/**
 * The API endpoint for the Journey Planner feature; allowing a journey to be fetched from
 * the coordinates given by two {@link BusStop} objects.
 * The coordinate used are gathered via {@link Location#getLongLat()}; from the {@link BusStop}s
 * provided.
 * Returns a {@link JourneyResponse} with the relevant data.
 * To get a journey from two formatted coordinates use {@link JourneyFromCoords}.
 *
 * @see JourneyFromCoords
 * @see JourneyResponse
 * @see Endpoint
 *
 * @author Aidan
 * @since 07/04/20
 */
public class JourneyFromStops extends Endpoint<JourneyResponse> {

    /**
     * Creates a JourneyFromCoords endpoint accessor with the two {@link BusStop}s given.
     * @param context The application context. This is usually {@code this}.
     * @param from The starting {@link BusStop}; whose location is queried.
     * @param to The ending {@link BusStop}; whose location is queried.
     */
    public JourneyFromStops(Context context, BusStop from, BusStop to){
        super(context,"/api/journey?from="+from.getLocation().getLongLat()+"&to="+to.getLocation().getLongLat());
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
