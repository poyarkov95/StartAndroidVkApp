package startandroid.apoyark.com.startandroidvkapp.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import startandroid.apoyark.com.startandroidvkapp.model.Member;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.BaseItemResponse;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.Full;

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMebmers(@QueryMap Map<String, String> map);
}
