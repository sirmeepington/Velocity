package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.entities.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;

/**
 * An endpoint for impetus.
 * Note: Implementing members <b>MUST</b> have {@code (Context context, String[] params)} as its
 * constructor's parameters.
 * @param <T> The response type from this request.
 */
public abstract class ImpetusEndpoint<T> {
    /**
     * The application context to be passed when using the endpoint.
     */
    protected final Context context;
    protected final String endpoint;

    ImpetusEndpoint(Context context, String endpoint){
        this.context = context;
        this.endpoint = endpoint;
    }

    /**
     * Constructs a full endpoint URL from {@link Impetus#getBaseUrl()} and {@link #endpoint}.
     * @return The full HTTP URL for this endpoint.
     */
    String getFullUrl(){
        return Impetus.getInstance(context).getBaseUrl()+this.endpoint;
    }

    /**
     * Applies the endpoint; constructing and adding a request to the request queue.
     * @param listener The listener to run when a successful response is given.
     * @param errorListener The listener to run when the request is unsuccessful.
     */
    public abstract void query(Response.Listener<ImpetusResponse<T>> listener, Response.ErrorListener errorListener);
}
