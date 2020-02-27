package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import uk.ac.tees.honeycomb.velocity.api.entities.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.ImpetusRequest;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByNameResponse;

public class StopByName extends ImpetusEndpoint<StopByNameResponse> {

    public StopByName(Context context, String name){
        super(context, "/api/stop/name?name="+name);
    }

    @Override
    public void query(Response.Listener<ImpetusResponse<StopByNameResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<StopByNameResponse>> request = new ImpetusRequest<>(
            Impetus.getInstance(context).getBaseUrl()+endpoint,
                new TypeToken<ImpetusResponse<StopByNameResponse>>(){},
            listener,
            errorListener
        );
        Impetus.getInstance(context).addRequest(request);
    }
}
