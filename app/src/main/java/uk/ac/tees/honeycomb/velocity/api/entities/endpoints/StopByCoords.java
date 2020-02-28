package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByCoordsResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;

/**
 * Gets bus stops from coordinates specified.
 * @author Aidan
 */
public class StopByCoords extends Endpoint<StopByCoordsResponse> {

    public StopByCoords(Context context, double latitude, double longitude) {
        super(context, "/api/stop/coords?lat="+latitude+"&long="+longitude);
    }

    @Override
    public void query(Response.Listener<ImpetusResponse<StopByCoordsResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<StopByCoordsResponse>> request = new ImpetusRequest<>(
            getFullUrl(),
            new TypeToken<ImpetusResponse<StopByCoordsResponse>>(){},
            listener,
            errorListener
        );

        Impetus.getInstance(context).addRequest(request);

    }
}
