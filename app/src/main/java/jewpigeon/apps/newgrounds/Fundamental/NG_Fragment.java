package jewpigeon.apps.newgrounds.Fundamental;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.ncapdevi.fragnav.FragNavController;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(behavior != null)
            return;

        FrameLayout layout =(FrameLayout) getNGActivity().findViewById(R.id.content_container);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

        behavior = params.getBehavior();
        params.setBehavior(null);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(behavior == null)
            return;

        FrameLayout layout =(FrameLayout) getNGActivity().findViewById(R.id.content_container);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

        params.setBehavior(behavior);

        layout.setLayoutParams(params);

        behavior = null;
    }

    public View findViewById(int id){
        return rootView.findViewById(id);
    }
}
