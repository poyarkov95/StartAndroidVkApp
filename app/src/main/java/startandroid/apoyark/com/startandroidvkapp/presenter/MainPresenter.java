package startandroid.apoyark.com.startandroidvkapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.view.MainView;

/**
 * Created by User on 05.05.2018.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void checkAuth(){
        if(!CurrentUser.isAuthorised()){
            getViewState().startSignIn();
        } else {
            getViewState().signedIn();
        }
    }
}
