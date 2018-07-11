package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.mvp.presenter.BaseFeedPresenter;
import startandroid.apoyark.com.startandroidvkapp.presenter.MembersPresenter;

public class MembersFragment extends BaseFeedFragment {
    @InjectPresenter
    MembersPresenter membersPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return membersPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_members;
    }
}
