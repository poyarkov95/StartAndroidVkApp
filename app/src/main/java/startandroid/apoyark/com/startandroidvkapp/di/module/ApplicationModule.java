package startandroid.apoyark.com.startandroidvkapp.di.module;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 05.05.2018.
 */
@Module
public class ApplicationModule {
    private Application mApplication;

    public  ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Singleton
    @Provides
    public Context provideContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflated(){
        return (LayoutInflater)mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    Typeface provideGoogleFontTypeFace(Context context){
        return Typeface.createFromAsset(context.getAssets(), "MaterialIcons-Regular.ttf");
    }
}
