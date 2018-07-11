package startandroid.apoyark.com.startandroidvkapp.rest.model.response.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.consts.ApiConstants;

/**
 * Created by User on 09.05.2018.
 */

public abstract class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConstants.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();

    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Map<String, String> toMap(){
        Map<String, String> map = new HashMap<>();
        map.put(VKApiConst.VERSION, String.valueOf(getVersion()));
        if(getAccessToken() != null){
            map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());
        }

        onMapCreate(map);
        return map;
    }

    public abstract void onMapCreate(Map<String, String> map);
}
