package jewpigeon.apps.newgrounds.PassportFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.PassportActivity;
import jewpigeon.apps.newgrounds.R;

public class PassportSignInFragment extends NG_Fragment {
    private EditText Username;
    private EditText Password;

    private MaterialButton LoginByNewgrounds;
    private MaterialButton LoginByFacebook;
    private MaterialButton LoginByGoogle;

    private TextView ForgotPassport;
    private TextView SignUp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.passport_sign_in, container, false);
        setSeekerView(root);

        Username = (EditText) findViewById(R.id.SignIn_User);
        Password = (EditText) findViewById(R.id.SignIn_Password);

        LoginByNewgrounds = (MaterialButton) findViewById(R.id.SignIn_LoginByNG);
        LoginByFacebook = (MaterialButton) findViewById(R.id.SignIn_LoginByFacebook);
        LoginByGoogle = (MaterialButton) findViewById(R.id.SignIn_LoginByGoogle);

        ForgotPassport = (TextView) findViewById(R.id.SignIn_toForgotPassword);
        SignUp = (TextView) findViewById(R.id.SignIn_toSignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getController().pushFragment(PassportSignUpFragment.newInstance());
            }
        });

        ForgotPassport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getController().pushFragment(PassportForgotFragment.newInstance());
            }
        });

        return root;
    }

    public static PassportSignInFragment newInstance() {

        Bundle args = new Bundle();

        PassportSignInFragment fragment = new PassportSignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private PassportActivity getPassportActivity(){
        return (PassportActivity) getNGActivity();
    }
}
