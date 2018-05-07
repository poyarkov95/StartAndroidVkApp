package startandroid.apoyark.com.startandroidvkapp.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 05.05.2018.
 */

public class Full<T> {
    @SerializedName("response")
    @Expose
    public T response;
}
