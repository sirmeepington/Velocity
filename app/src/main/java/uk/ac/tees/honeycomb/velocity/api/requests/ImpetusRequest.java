package uk.ac.tees.honeycomb.velocity.api.requests;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

/**
 * A web request to be used with Impetus that returns the json data packed into the entity given.
 * @param <T> The return object's type. This is usually a response object.
 * @author Aidan
 */
public class ImpetusRequest<T> extends GsonRequest<T> {

    /**
     * Required user-agent headers for accessing Impetus (User-Agent: Velocity).
     */
    private static final HashMap<String,String> HEADERS;
    static {
        HEADERS = new HashMap<>();
        HEADERS.put("User-Agent","Velocity");
    }

    public ImpetusRequest(String url, TypeToken responseType,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(url, responseType, HEADERS, listener,errorListener);
    }

}
