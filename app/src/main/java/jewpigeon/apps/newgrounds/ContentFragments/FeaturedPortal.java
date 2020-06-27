package jewpigeon.apps.newgrounds.ContentFragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;

public class FeaturedPortal extends NG_Fragment {
    CoordinatorLayout.Behavior behavior;
    View rootView;
    public static FeaturedPortal newInstance() {
        
        Bundle args = new Bundle();
        
        FeaturedPortal fragment = new FeaturedPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_featured, container, false);
        return rootView;
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
}
