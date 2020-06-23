package jewpigeon.apps.newgrounds.Fundamental;

import android.content.Context;
import android.widget.FrameLayout;

import com.ncapdevi.fragnav.FragNavController;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import jewpigeon.apps.newgrounds.R;

public class NG_Fragment extends Fragment {
    CoordinatorLayout.Behavior behavior;
    public NG_Activity getNGActivity(){
        return ((NG_Activity) getActivity());
    }
    public FragNavController getController(){
        return getNGActivity().getController();
    }
}
