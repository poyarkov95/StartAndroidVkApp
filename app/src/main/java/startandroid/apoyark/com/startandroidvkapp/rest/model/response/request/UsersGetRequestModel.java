package startandroid.apoyark.com.startandroidvkapp.rest.model.response.request;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

import startandroid.apoyark.com.startandroidvkapp.consts.ApiConstants;

public class UsersGetRequestModel extends BaseRequestModel {

    public UsersGetRequestModel(String userId) {
        this.userId = userId;
    }

    @SerializedName(VKApiConst.USER_IDS)
    String userId;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_USER_FIELDS;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.USER_IDS, getUserId());
        map.put(VKApiConst.FIELDS, getFields());
    }
}
