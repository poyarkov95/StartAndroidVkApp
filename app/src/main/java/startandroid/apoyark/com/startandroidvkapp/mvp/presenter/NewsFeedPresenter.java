package startandroid.apoyark.com.startandroidvkapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import startandroid.apoyark.com.startandroidvkapp.CurrentUser;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;
import startandroid.apoyark.com.startandroidvkapp.common.utils.VkListHelper;
import startandroid.apoyark.com.startandroidvkapp.consts.ApiConstants;
import startandroid.apoyark.com.startandroidvkapp.model.WallItem;
import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemBodyViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemFooterViewModel;
import startandroid.apoyark.com.startandroidvkapp.model.view.NewsItemHeaderViewModel;
import startandroid.apoyark.com.startandroidvkapp.mvp.view.BaseFeedView;
import startandroid.apoyark.com.startandroidvkapp.rest.api.WallApi;
import startandroid.apoyark.com.startandroidvkapp.rest.model.response.request.WallGetRequestModel;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {
    @Inject
    WallApi wallApi;

    private boolean enableIdFiltering = false;


    public NewsFeedPresenter() {
        MainApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return wallApi.get(new WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .compose(applyFilter())
                .doOnNext(this::saveToDb)
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));

                    return Observable.fromIterable(baseItems);
                });
}

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealm())
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
    }

    public Callable<List<WallItem>> getListFromRealm(){
        return () -> {
            String[] sortFields = {"date"};
            Sort[] sortOrder = {Sort.DESCENDING};
            Realm realm = Realm.getDefaultInstance();
            RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(realmResults);
        };
    }

    private List<BaseViewModel> parsePojoModel(WallItem wallItem){
        List<BaseViewModel> baseViewModels = new ArrayList<>();
        baseViewModels.add(new NewsItemHeaderViewModel(wallItem));
        baseViewModels.add(new NewsItemBodyViewModel(wallItem));
        baseViewModels.add(new NewsItemFooterViewModel(wallItem));
        return baseViewModels;
    }

    public void setEnableIdFiltering(boolean enableIdFiltering) {
        this.enableIdFiltering = enableIdFiltering;
    }

    protected ObservableTransformer<WallItem, WallItem> applyFilter() {
        if(enableIdFiltering && CurrentUser.getId() != null) {
            return baseItemObservable -> baseItemObservable.filter(wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId())));
        } else {
            return baseItemObservable -> baseItemObservable;
        }
    }
}
