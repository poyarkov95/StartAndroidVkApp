package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.common.utils.VkListHelper;
import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemBodyViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemFooterViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemHeaderViewModel;
import startandroid.apoyark.com.startandroidvkapp.rest.api.WallApi;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.GetWallResponse;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.request.WallGetRequestModel;

/**
 * Created by User on 05.05.2018.
 */

public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<GetWallResponse>() {
            @Override
            public void onResponse(Call<GetWallResponse> call, Response<GetWallResponse> response) {
                List<BaseViewModel> list = new ArrayList<>();

                List<WallItem> wallItems = VkListHelper.getWallList(response.body().response);

                for(WallItem wallItem : wallItems){
                    list.add(new NewsItemHeaderViewModel(wallItem));
                    list.add(new NewsItemBodyViewModel(wallItem));
                    list.add(new NewsItemFooterViewModel(wallItem));
                }
                adapter.addItems(list);
            }

            @Override
            public void onFailure(Call<GetWallResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

}
