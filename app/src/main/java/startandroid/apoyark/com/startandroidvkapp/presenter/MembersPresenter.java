package startandroid.apoyark.com.startandroidvkapp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.consts.ApiConstants;
import startandroid.apoyark.com.startandroidvkapp.model.Member;
import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.MemberViewModel;
import startandroid.apoyark.com.startandroidvkapp.mvp.presenter.BaseFeedPresenter;
import startandroid.apoyark.com.startandroidvkapp.mvp.view.BaseFeedView;
import startandroid.apoyark.com.startandroidvkapp.rest.api.GroupsApi;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.request.GroupsGetMembersRequestModel;

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView> {
    @Inject
    GroupsApi usersApi;

    public MembersPresenter() {
        MainApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return usersApi.getMebmers(new GroupsGetMembersRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(baseItemResponseFull -> Observable.fromIterable(baseItemResponseFull.response.getItems()))
                .doOnNext(this::saveToDb)
                .map(MemberViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(member -> new MemberViewModel(member));
    }

    public Callable<List<Member>> getListFromRealmCallable() {
        return () -> {
            String [] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Member> results = realm.where(Member.class).findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }
}
