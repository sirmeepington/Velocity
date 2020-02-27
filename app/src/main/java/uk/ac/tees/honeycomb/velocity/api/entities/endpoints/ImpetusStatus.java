package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StatusResponse;

/**
 * Gets the status of Impetus services. Useful for running before the app is fully launched to get
 * an idea of what to expect from services.
 * @author Aidan
 */
public class ImpetusStatus extends Endpoint<StatusResponse> {

    public ImpetusStatus(Context context){
        super(context,"/impetus/status");
    }

    @Override
    public void query(Response.Listener<ImpetusResponse<StatusResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<StatusResponse>> request = new ImpetusRequest<>(
                Impetus.getInstance(context).getBaseUrl()+endpoint,
                new TypeToken<ImpetusResponse<StatusResponse>>(){},
                listener,
                errorListener
        );
        Impetus.getInstance(context).addRequest(request);
    }
}
