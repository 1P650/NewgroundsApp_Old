package ferox.ip.newgrounds.Fundamental;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;

public class NG_Application extends Application {
    Retrofit NG_Connector;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
