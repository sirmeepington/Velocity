package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;

/**
 * Gets the stops that have the name Town name, Locality name, or Stop name.
 * @author Aidan
 */
public class StopByName extends Endpoint<StopByNameResponse> {

    public StopByName(Context context, String name){
        super(context, "/api/stop/name?name="+Impetus.encode(name));
    }

    /**
     * Queries the StopByName endpoint by creating and adding the request to the queue.
     * @param listener The listener to run when a successful response is given.
     * @param errorListener The listener to run when the request is unsuccessful.
     */
    @Override
    public void query(Response.Listener<ImpetusResponse<StopByNameResponse>> listener,
                      Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<StopByNameResponse>> request = new ImpetusRequest<>(
            getFullUrl(),
            new TypeToken<ImpetusResponse<StopByNameResponse>>(){},
            listener,
            errorListener
        );
        Impetus.getInstance(context).addRequest(request);
    }
}
