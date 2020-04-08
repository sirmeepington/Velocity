package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.JourneyResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;
import uk.ac.tees.honeycomb.velocity.stops.BusStop;

public class JourneyFromStops extends Endpoint<JourneyResponse> {

    public JourneyFromStops(Context context, BusStop from, BusStop to){
        super(context,"api/journey?from="+from.getLocation().getLongLat()+"&to="+to.getLocation().getLongLat());
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
