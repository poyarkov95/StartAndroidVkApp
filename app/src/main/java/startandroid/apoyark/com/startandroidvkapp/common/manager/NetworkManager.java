package startandroid.apoyark.com.startandroidvkapp.common.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.internal.Util;
import startandroid.apoyark.com.startandroidvkapp.MainApplication;

public class NetworkManager {

    @Inject
    Context context;

    public static final String TAG = "NetworkManager";

    public NetworkManager() {
        MainApplication.getApplicationComponent().inject(this);
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected() || Util.isEmulator());
    }

    public Callable<Boolean> isVkReachableCallable() {
        return () -> {
            try {
                if(!isOnline()) {
                    return false;
                }

                URL url = new URL("https://api.vk.com");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setConnectTimeout(2000);
                connection.connect();

                return true;
            } catch (Exception e){
                return false;
            }
        };
    }

    public Observable<Boolean> getNetworkObservable() {
        return Observable.fromCallable(isVkReachableCallable());
    }
}
