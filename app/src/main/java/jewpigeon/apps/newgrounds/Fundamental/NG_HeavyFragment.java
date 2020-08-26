package jewpigeon.apps.newgrounds.Fundamental;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.R;

public abstract class NG_HeavyFragment extends NG_Fragment {

    private Bundle SavedInstanceState = null;
    private boolean hasInflated = false;
    private ViewStub layoutContainer = null;
    private boolean visible = false;
    private ProgressBar loadingIndicator = null;
    private View stubContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        stubContainer = inflater.inflate(R.layout.heavy_fragment, container, false);
        layoutContainer = stubContainer.findViewById(R.id.generic_heavy_container);
        loadingIndicator = stubContainer.findViewById(R.id.generic_heavy_progressBar);

        layoutContainer.setLayoutResource(getResourceId());
        SavedInstanceState = savedInstanceState;

        if (visible && !hasInflated) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    View inflatedView = layoutContainer.inflate();
                    //setSeekerView(inflatedView);
                    onCreateStubView(inflatedView, SavedInstanceState);
                    afterInflated(stubContainer);

                }
            }, 500);
        }
        return stubContainer;
    }

    public abstract int getResourceId();

    public abstract void onCreateStubView(View rootView, Bundle savedInstanceState);

    public void afterInflated(View origContainer) {
        hasInflated = true;
        if (origContainer != null) {
            loadingIndicator.setVisibility(View.INVISIBLE);
            origContainer.findViewById(R.id.generic_heavy_plug).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        visible = true;
        if (layoutContainer != null && !hasInflated) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    View inflatedView = layoutContainer.inflate();
                    //setSeekerView(inflatedView);
                    onCreateStubView(inflatedView, SavedInstanceState);
                    afterInflated(stubContainer);

                }
            }, 500);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasInflated = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        visible = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hasInflated = false;
    }
}