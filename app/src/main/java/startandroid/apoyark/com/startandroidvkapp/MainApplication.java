package startandroid.apoyark.com.startandroidvkapp;

import android.app.Application;

import com.vk.sdk.VKSdk;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import startandroid.apoyark.com.startandroidvkapp.di.component.ApplicationComponent;

import startandroid.apoyark.com.startandroidvkapp.di.component.DaggerApplicationComponent;
import startandroid.apoyark.com.startandroidvkapp.di.module.ApplicationModule;

/**
 * Created by User on 05.05.2018.
 */

public class MainApplication extends Application {


    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        VKSdk.initialize(this);

        initComponent();
    }

    private void initComponent(){
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
