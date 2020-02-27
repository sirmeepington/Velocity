package uk.ac.tees.honeycomb.velocity.api.entities;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import com.android.volley.Response;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * A HTTP GET request that returns the JSON response as an object in the type {@link T}.
 * Taken from the Android Volley documentation.
 * (https://developer.android.com/training/volley/request).
 * @param <T> The return type for GSON to convert the JSON to.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Map<String, String> headers;
    private final com.android.volley.Response.Listener<T> listener;
    private final TypeToken clazz;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param headers Map of request headers
     */
    public GsonRequest(String url, TypeToken token, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.headers = headers;
        this.listener = listener;
        this.clazz = token;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @SuppressWarnings("unchecked") // Casting to generic <T> is unchecked due to type erasure.
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    (T) gson.fromJson(json, clazz.getType()),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
