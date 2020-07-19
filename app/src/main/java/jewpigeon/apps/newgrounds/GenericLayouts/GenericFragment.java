package jewpigeon.apps.newgrounds.GenericLayouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;

public class GenericFragment extends NG_Fragment {
    public static GenericFragment newInstance() {

        Bundle args = new Bundle();

        GenericFragment fragment = new GenericFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generic_fragment, container,false);
        setSeekerView(view);
        return view;
    }


}
