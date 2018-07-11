package startandroid.apoyark.com.startandroidvkapp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import startandroid.apoyark.com.startandroidvkapp.rest.RestClient;
import startandroid.apoyark.com.startandroidvkapp.rest.api.GroupsApi;
import startandroid.apoyark.com.startandroidvkapp.rest.api.UsersApi;
import startandroid.apoyark.com.startandroidvkapp.rest.api.WallApi;

/**
 * Created by User on 05.05.2018.
 */
@Module
public class RestModule {
    private RestClient mRestClient;

    public RestModule() {
        mRestClient = new RestClient();
    }

    @Provides
    @Singleton
    public RestClient provideRestClient(){
        return mRestClient;
    }

    @Provides
    @Singleton
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Provides
    @Singleton
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }
}
