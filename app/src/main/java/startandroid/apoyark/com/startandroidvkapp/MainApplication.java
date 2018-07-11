package startandroid.apoyark.com.startandroidvkapp;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
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
        VKSdk.initialize(this);

        initComponent();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        });
    }

    private void initComponent(){
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
