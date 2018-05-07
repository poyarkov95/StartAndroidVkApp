package startandroid.apoyark.com.startandroidvkapp.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.common.manager.MyFragmentManager;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.BaseFragment;

/**
 * Created by User on 05.05.2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    MyFragmentManager mFragmentManager;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout parent = (FrameLayout)findViewById(R.id.main_wrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);

        MainApplication.getApplicationComponent().inject(this);
    }

    @LayoutRes
    protected abstract int getMainContentLayout();

    public void fragmentOnScreen(BaseFragment baseFragment){
        setToolbarTitle(baseFragment.createToolbarTitle(this));
    }

    public void setToolbarTitle(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    public void setContent(BaseFragment fragment){
        mFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    public void addContent(BaseFragment fragment){
        mFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    public boolean removeCurrentFragment(){
        return mFragmentManager.removeCurrentFragment(this);
    }

    public boolean removeFragment(BaseFragment fragment){
        return mFragmentManager.removeFragment(this, fragment);
    }

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}
