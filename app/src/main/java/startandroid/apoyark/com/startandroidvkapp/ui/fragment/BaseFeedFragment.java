package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.common.BaseAdapter;
import startandroid.apoyark.com.startandroidvkapp.common.manager.MyLinearLayoutManager;
import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;
import startandroid.apoyark.com.startandroidvkapp.mvp.presenter.BaseFeedPresenter;
import startandroid.apoyark.com.startandroidvkapp.mvp.view.BaseFeedView;

/**
 * Created by User on 11.06.2018.
 */

public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView {
    @BindView(R.id.rv_list)
    protected RecyclerView recyclerView;

    protected BaseFeedPresenter baseFeedPresenter;

    protected BaseAdapter adapter;

    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected ProgressBar progressBar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpRecycler();
        setUpAdapter(recyclerView);
        setUpSwipeRefreshLayout();
        baseFeedPresenter = onCreateFeedPresenter();
        baseFeedPresenter.loadStart();
    }

    private void setUpRecycler() {
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(layoutManager.isOnNextPagePosition()) {
                    baseFeedPresenter.loadNext(adapter.getRealItemCount());
                }
            }
        });
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void setUpAdapter(RecyclerView recyclerView) {
        adapter = new BaseAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        progressBar = getBaseActivity().getProgressBar();
        swipeRefreshLayout.setOnRefreshListener(() -> onCreateFeedPresenter().loadRefresh());
    }

    protected abstract BaseFeedPresenter onCreateFeedPresenter();

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return 0;
    }

    @Override
    public void showRefreshing() {

    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showListProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setItems(List<BaseViewModel> items) {
        adapter.setItems(items);
    }

    @Override
    public void addItems(List<BaseViewModel> items) {
        adapter.addItems(items);
    }
}
