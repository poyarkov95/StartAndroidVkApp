package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemBodyViewModel;
import startandroid.apoyark.com.startandroidvkapp.rest.api.WallApi;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.BaseItemResponse;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.Full;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.GetWallResponse;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.request.WallGetRequestModel;
import startandroid.apoyark.com.startandroidvkapp.ui.common.BaseAdapter;

/**
 * Created by User on 05.05.2018.
 */

public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi mWallApi;

    RecyclerView mRecyclerView;

    BaseAdapter mAdapter;

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
        setUpRecyclerView(getView());
        setUpAdapter(mRecyclerView);

        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<GetWallResponse>() {
            @Override
            public void onResponse(Call<GetWallResponse> call, Response<GetWallResponse> response) {
                List<NewsItemBodyViewModel> list = new ArrayList<>();

                for(WallItem item : response.body().response.getItems()){
                    list.add(new NewsItemBodyViewModel(item));
                }
                mAdapter.addItems(list);
            }

            @Override
            public void onFailure(Call<GetWallResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    private void setUpRecyclerView(View rootView){
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.rv_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpAdapter(RecyclerView rv){
        mAdapter = new BaseAdapter();

        rv.setAdapter(mAdapter);
    }
}
