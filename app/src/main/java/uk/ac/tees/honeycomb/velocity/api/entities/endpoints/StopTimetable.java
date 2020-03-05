package uk.ac.tees.honeycomb.velocity.api.entities.endpoints;

import android.content.Context;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;

import uk.ac.tees.honeycomb.velocity.api.Impetus;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.BusStopTimetableResponse;
import uk.ac.tees.honeycomb.velocity.api.entities.responses.ImpetusResponse;
import uk.ac.tees.honeycomb.velocity.api.requests.ImpetusRequest;

/**
 * Retrieves the {@link StopTimetable} for a bus stop from its ATCO code.
 */
public class StopTimetable extends Endpoint<BusStopTimetableResponse> {

    public StopTimetable(Context context, String atcoCode){
        // toUpperCase required due to TransportAPI.
        super(context, "/api/stop/timetable?atco="+atcoCode.toUpperCase());
    }

    @Override
    public void query(Response.Listener<ImpetusResponse<BusStopTimetableResponse>> listener, Response.ErrorListener errorListener) {
        ImpetusRequest<ImpetusResponse<BusStopTimetableResponse>> request = new ImpetusRequest<>(
          getFullUrl(),
          new TypeToken<ImpetusResponse<BusStopTimetableResponse>>(){},
          listener,
          errorListener
        );
        Impetus.getInstance(context).addRequest(request);
    }
}
