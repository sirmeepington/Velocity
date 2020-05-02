package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.StopByPostcodeResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;

/**
 * An endpoint to gather bus stops around the location of a postcode. Not ON the postcode's street.
 * Uses postcodes.io to gather postcode information; and as such the data sources includes both
 * postcodes.io and NapTAN.
 * @author Aidan
 */
public class StopByPostcode extends Endpoint<StopByPostcodeResponse> {

    public StopByPostcode(Context context, String postcode){
        super(context, "/api/stop/postcode?postcode="+Impetus.encode(postcode));
    }

    /**
     * Requests the postcode endpoint.
     * @param listener The listener to run when a successful response is given.
     * @param errorListener The listener to run when the request is unsuccessful.
     */
    @Override
    public void query(Response.Listener<ImpetusResponse<StopByPostcodeResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<StopByPostcodeResponse>> request = new ImpetusRequest<>(
            getFullUrl(),
            new TypeToken<ImpetusResponse<StopByPostcodeResponse>>(){},
            listener,
            errorListener
        );
        Impetus.getInstance(context).addRequest(request);
    }
}
