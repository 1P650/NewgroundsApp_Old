package jewpigeon.apps.newgrounds.Fundamental;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.ncapdevi.fragnav.FragNavController;

import java.util.Objects;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import jewpigeon.apps.newgrounds.R;

public class NG_Fragment extends Fragment {
    CoordinatorLayout.Behavior behavior;
    View rootView;

    public NG_Activity getNGActivity() {
        return ((NG_Activity) getActivity());
    }

    public FragNavController getController() {
        return getNGActivity().getController();
    }

    public void setSeekerView(View view){
        this.rootView = view;
    }

    public View getSeekerView(){
          return rootView;
    }

    public View findViewById(int id){
        return rootView.findViewById(id);
    }

    public void makeErrorSnackbar(String s) {
        Snackbar errorSnackbar = Snackbar.make(getSeekerView(), s, Snackbar.LENGTH_SHORT);
        errorSnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        errorSnackbar.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPassportError));
        errorSnackbar.setBackgroundTint(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        errorSnackbar.show();

    }
}
