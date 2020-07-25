package jewpigeon.apps.newgrounds.Fundamental;

import android.os.Bundle;
import android.util.Log;

import com.ncapdevi.fragnav.FragNavController;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.objectbox.Box;

public class NG_Activity extends AppCompatActivity {
    private FragNavController controller;
    private Box<NG_PreferencesData> PreferencesStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferencesStore = NG_BoxStore.get().boxFor(NG_PreferencesData.class);
    }

    public void setUpController(FragNavController controller){
        this.controller = controller;
    }
    public FragNavController getController(){
        return this.controller;
    }
    private Box<NG_PreferencesData> getPreferencesStore(){
        return this.PreferencesStore;
    }
    public NG_PreferencesData getPreferencesFromStore(){
        if (getPreferencesStore().get(1) == null) return null;
        return getPreferencesStore().get(1);
    }

    public void updatePreferences(NG_PreferencesData preferencesData){
        getPreferencesStore().put(preferencesData);
    }

}
