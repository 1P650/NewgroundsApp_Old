package jewpigeon.apps.newgrounds.Fundamental;

import android.view.View;
import android.view.ViewGroup;

import com.ncapdevi.fragnav.FragNavController;

import androidx.appcompat.app.AppCompatActivity;
import jewpigeon.apps.newgrounds.PassportActivity;

public class NG_Activity extends AppCompatActivity {
    FragNavController controller;
    public void setUpController(FragNavController controller){
        this.controller = controller;
    }
    public FragNavController getController(){
        return this.controller;
    }
}
