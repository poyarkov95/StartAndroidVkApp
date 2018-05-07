package startandroid.apoyark.com.startandroidvkapp.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.BaseItemResponse;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.Full;

/**
 * Created by User on 05.05.2018.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<Full<BaseItemResponse>> get(@Query("owner_id") String ownerId,

                                     @Query("access_token") String accessToken,

                                     @Query("extended") Integer extended,

                                     @Query("v") String version);
}
