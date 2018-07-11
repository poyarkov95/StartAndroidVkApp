package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;

import startandroid.apoyark.com.startandroidvkapp.R;

public class MyPostsFragment extends NewsFeedFragment {
    public MyPostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }
}
