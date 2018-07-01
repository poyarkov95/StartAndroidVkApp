package startandroid.apoyark.com.startandroidvkapp.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.GetWallResponse;

/**
 * Created by User on 05.05.2018.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);
}
