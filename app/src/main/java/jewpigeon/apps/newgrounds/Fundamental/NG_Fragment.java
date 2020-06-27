package jewpigeon.apps.newgrounds.Fundamental;

import com.ncapdevi.fragnav.FragNavController;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

public class NG_Fragment extends Fragment {
    CoordinatorLayout.Behavior behavior;

    public NG_Activity getNGActivity() {
        return ((NG_Activity) getActivity());
    }

    public FragNavController getController() {
        return getNGActivity().getController();
    }
}
