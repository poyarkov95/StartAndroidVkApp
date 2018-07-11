package startandroid.apoyark.com.startandroidvkapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.common.manager.MyFragmentManager;
import startandroid.apoyark.com.startandroidvkapp.common.manager.NetworkManager;
import startandroid.apoyark.com.startandroidvkapp.model.Profile;
import startandroid.apoyark.com.startandroidvkapp.rest.api.UsersApi;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.request.UsersGetRequestModel;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.BaseFragment;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.MembersFragment;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.MyPostsFragment;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.NewsFeedFragment;
import startandroid.apoyark.com.startandroidvkapp.view.MainView;

/**
 * Created by User on 05.05.2018.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    UsersApi usersApi;

    @Inject
    NetworkManager networkManager;

    @Inject
    MyFragmentManager fragmentManager;

    public MainPresenter() {
        MainApplication.getApplicationComponent().inject(this);
    }

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedIn();
        }
    }

    public Observable<Profile> getProfileFromNetwork() {
        return usersApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    public Observable<Profile> getProfileFromDb() {
        return Observable.fromCallable(getListFromRealCallable());
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm.copyToRealmOrUpdate(item));
    }

    public Callable<Profile> getListFromRealCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    private void getCurrentUser() {
        networkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if (!CurrentUser.isAuthorized()) {
                        getViewState().startSignIn();
                    }

                    return aBoolean ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> getViewState().showCurrentUser(profile),
                          throwable -> throwable.printStackTrace());
    }

    public void drawerItemClicked(int id) {
        BaseFragment fragment = null;

        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 4:
                fragment = new MembersFragment();
                break;
        }

        if(fragment != null && !fragmentManager.isAlreadyContains(fragment)){
            getViewState().showFragmentFromDrawer(fragment);
        }
    }
}
