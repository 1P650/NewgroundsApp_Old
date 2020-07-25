package jewpigeon.apps.newgrounds.Fundamental;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import io.objectbox.BoxStore;
import retrofit2.Retrofit;

public class NG_Application extends Application {
    private Retrofit NG_Connector;
    private static NG_Application NG;

    
    @Override
    public void onCreate() {
        super.onCreate();
        NG = this;
        NG_BoxStore.init(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static NG_Application getSelf(){
        return NG;
    }


}
