package startandroid.apoyark.com.startandroidvkapp.view;

import com.arellomobile.mvp.MvpView;

import startandroid.apoyark.com.startandroidvkapp.model.Profile;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.BaseFragment;

/**
 * Created by User on 05.05.2018.
 */

public interface MainView extends MvpView {
    void startSignIn();

    void signedIn();

    void showCurrentUser(Profile profile);

    void showFragmentFromDrawer(BaseFragment baseFragment);
}
