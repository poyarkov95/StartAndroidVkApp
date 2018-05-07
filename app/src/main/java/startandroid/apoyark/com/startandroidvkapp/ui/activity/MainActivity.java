package startandroid.apoyark.com.startandroidvkapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.R;
import startandroid.apoyark.com.startandroidvkapp.consts.ApiConstants;
import startandroid.apoyark.com.startandroidvkapp.presenter.MainPresenter;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.NewsFeedFragment;
import startandroid.apoyark.com.startandroidvkapp.view.MainView;

public class MainActivity extends BaseActivity implements MainView{

    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApplication.getApplicationComponent().inject(this);
        mPresenter.checkAuth();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                mPresenter.checkAuth();

            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedIn() {
        Toast.makeText(this, "User id " + CurrentUser.getId(), Toast.LENGTH_LONG).show();
        setContent(new NewsFeedFragment());
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }
}
