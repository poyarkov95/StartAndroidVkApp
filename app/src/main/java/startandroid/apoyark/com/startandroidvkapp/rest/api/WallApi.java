package startandroid.apoyark.com.startandroidvkapp.rest.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.BaseItemResponse;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.Full;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.GetWallResponse;

/**
 * Created by User on 05.05.2018.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<GetWallResponse> get(@QueryMap Map<String, String> map);
}
