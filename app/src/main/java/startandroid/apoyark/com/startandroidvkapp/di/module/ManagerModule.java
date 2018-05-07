package startandroid.apoyark.com.startandroidvkapp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import startandroid.apoyark.com.startandroidvkapp.common.manager.MyFragmentManager;

/**
 * Created by User on 05.05.2018.
 */
@Module
public class ManagerModule {
    @Provides
    @Singleton
    public MyFragmentManager provideMyFragmentManager(){
        return new MyFragmentManager();
    }
}
