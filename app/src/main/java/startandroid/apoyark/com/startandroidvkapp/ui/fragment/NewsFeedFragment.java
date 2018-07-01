package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.mvp.presenter.BaseFeedPresenter;
import startandroid.apoyark.com.startandroidvkapp.mvp.presenter.NewsFeedPresenter;
import startandroid.apoyark.com.startandroidvkapp.rest.api.WallApi;

/**
 * Created by User on 05.05.2018.
 */

public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter presenter;

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
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return presenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

}
