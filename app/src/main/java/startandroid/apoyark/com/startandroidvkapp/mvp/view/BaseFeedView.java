package startandroid.apoyark.com.startandroidvkapp.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import startandroid.apoyark.com.startandroidvkapp.model.view.BaseViewModel;

public interface BaseFeedView extends MvpView {
    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void showError(String message);

    void setItems(List<BaseViewModel> items);

    void addItems(List<BaseViewModel> items);
}
