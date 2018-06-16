package startandroid.apoyark.com.startandroidvkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.common.BaseAdapter;

/**
 * Created by User on 11.06.2018.
 */

public abstract class BaseFeedFragment extends BaseFragment {
    protected RecyclerView recyclerView;

    protected BaseAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecycler(view);
        setUpAdapter(recyclerView);
    }

    private void setUpRecycler(View rootView){
        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpAdapter(RecyclerView recyclerView){
        adapter = new BaseAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return 0;
    }
}
