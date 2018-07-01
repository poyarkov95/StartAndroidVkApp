package startandroid.apoyark.com.startandroidvkapp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import startandroid.apoyark.com.startandroidvkapp.common.manager.MyFragmentManager;
import startandroid.apoyark.com.startandroidvkapp.common.manager.NetworkManager;

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

    @Provides
    @Singleton
    public NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
