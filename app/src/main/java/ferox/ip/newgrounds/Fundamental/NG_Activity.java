package ferox.ip.newgrounds.Fundamental;

import android.view.View;
import android.view.ViewGroup;

import com.ncapdevi.fragnav.FragNavController;

import androidx.appcompat.app.AppCompatActivity;

public class NG_Activity extends AppCompatActivity {
    FragNavController controller;
    public void setUpController(FragNavController controller){
        this.controller = controller;
    }
    public FragNavController getController(){
        return this.controller;
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
