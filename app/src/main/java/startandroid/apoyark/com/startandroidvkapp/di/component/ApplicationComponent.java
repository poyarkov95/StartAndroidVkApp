package startandroid.apoyark.com.startandroidvkapp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import startandroid.apoyark.com.startandroidvkapp.di.module.ApplicationModule;
import startandroid.apoyark.com.startandroidvkapp.di.module.ManagerModule;
import startandroid.apoyark.com.startandroidvkapp.di.module.RestModule;
import startandroid.apoyark.com.startandroidvkapp.ui.activity.BaseActivity;
import startandroid.apoyark.com.startandroidvkapp.ui.activity.MainActivity;
import startandroid.apoyark.com.startandroidvkapp.ui.fragment.NewsFeedFragment;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.NewsItemBodyHolder;
import startandroid.apoyark.com.startandroidvkapp.ui.holder.NewsItemFooterHolder;

/**
 * Created by User on 05.05.2018.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    void inject(MainActivity activity);

    void inject(NewsFeedFragment fragment);

    void inject(NewsItemBodyHolder holder);

    void inject(NewsItemFooterHolder holder);
}
