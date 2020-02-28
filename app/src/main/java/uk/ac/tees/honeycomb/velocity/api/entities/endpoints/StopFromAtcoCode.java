package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopFromAtcoCodeResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;

/**
 * Gathers a bus stop that matches the ATCO code given.
 * @author Aidan
 */
public class StopFromAtcoCode extends Endpoint<StopFromAtcoCodeResponse> {

    public StopFromAtcoCode(Context context, String atcoCode){
        super(context, "/api/stop/code?atco="+atcoCode);
    }

    /**
     * Requests a stop whose ATCO code matches the input from Impetus.
     * @param listener The listener to run when a successful response is given.
     * @param errorListener The listener to run when the request is unsuccessful.
     */
    @Override
    public void query(Response.Listener<ImpetusResponse<StopFromAtcoCodeResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<StopFromAtcoCodeResponse>> request = new ImpetusRequest<>(
                getFullUrl(),
                new TypeToken<ImpetusResponse<StopFromAtcoCodeResponse>>(){},
                listener,
                errorListener
        );

        Impetus.getInstance(context).addRequest(request);
    }
}
