package uk.ac.tees.honeycomb.velocity.api.entities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * A singleton class for accessing RequestQueues for Impetus requests.
 * @see ImpetusRequest
 */
public class Impetus {

    private static Impetus instance;
    private RequestQueue requestQueue;
    private final Context context;


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
    public static Impetus getInstance(Context c){
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
        return context.getSharedPreferences("impetuspreferences.xml",Context.MODE_PRIVATE).getString("impetus_url","http://152.105.67.114:5000");
    }


    public static String createQueryParams(HashMap<String,String> params){
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> entry : params.entrySet()){
            sb.append(first ? '?' : '&');
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            first = false;
        }
        return sb.toString();
    }

}
