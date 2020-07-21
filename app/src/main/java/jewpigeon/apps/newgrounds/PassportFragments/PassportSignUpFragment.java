package jewpigeon.apps.newgrounds.PassportFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.PassportActivity;
import jewpigeon.apps.newgrounds.R;


public class PassportSignUpFragment extends NG_Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPassportActivity().setDashboardTitle("Sign up");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.passport_sign_up, container, false);
        setSeekerView(root);
        return root;
    }

    public static PassportSignUpFragment newInstance() {

        Bundle args = new Bundle();

        PassportSignUpFragment fragment = new PassportSignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private PassportActivity getPassportActivity(){
        return (PassportActivity) getNGActivity();
    }
}
