package uk.ac.tees.honeycomb.velocity.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;

/**
 * A singleton class for accessing RequestQueues for Impetus requests.
 * @see ImpetusRequest
 * @author Aidan
 */
public class Impetus {

    private static Impetus instance;
    private RequestQueue requestQueue;
    private final Context context;// BE CAREFUL OF USING ANDROID CONTEXT IN A STATIC CONTEXT.
                                  // POTENTIAL MEMORY LEAK IF DONE WRONG.


    private Impetus(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(this.context.getApplicationContext());
    }

    /**
     * Retrieves a Singleton instance of this class with; setting the context to be used when we
     * create a Volley {@link RequestQueue}.
     * @param c The context to access Impetus from.
     * @return An instance of Impetus.
     */
    public synchronized static Impetus getInstance(Context c){
        // Initialise context before constructor to ensure that we have a volley
        // request queue available.
        if (instance == null)
            instance = new Impetus(c);
        return instance;
    }

    /**
     * Adds a request to the Volley request queue.
     * @param request The request to add.
     * @param <T> The object type of the request.
     */
    public <T> void addRequest(Request<T> request){
        requestQueue.add(request);
    }

    /**
     * Returns Impetus's base URL; does not include the trailing slash.
     * @return The base url for impetus.
     */
    public String getBaseUrl(){
        return context.getSharedPreferences("impetuspreferences.xml",Context.MODE_PRIVATE).getString("impetus_url","http://51.68.214.152:5000");
    }

    /**
     * Attempts to encode the given parameter.
     * Wraps the try/catch given from {@link URLEncoder#encode(String, String)}.
     * @param param The String to encode/
     * @return The UTF-8 encoded string.
     */
    public static String encode(Object param){
        try {
            return URLEncoder.encode(param.toString(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return param.toString();
        }
    }

}
