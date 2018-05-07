package startandroid.apoyark.com.startandroidvkapp.rest.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 05.05.2018.
 */

public class BaseItemResponse<T> {
    public Integer count;
    public List<T> items = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }
}
