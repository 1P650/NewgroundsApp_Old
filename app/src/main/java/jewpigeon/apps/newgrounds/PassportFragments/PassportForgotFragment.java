package jewpigeon.apps.newgrounds.PassportFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;

public class PassportForgotFragment extends NG_Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.passport_forgot_password, container,false);
        setSeekerView(root);
        return root;
    }

    public static PassportForgotFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PassportForgotFragment fragment = new PassportForgotFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
