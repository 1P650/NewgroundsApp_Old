package jewpigeon.apps.newgrounds.PassportFragments;

import android.os.Bundle;
import android.telephony.AccessNetworkConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.Fundamental.NG_BoxStore;
import jewpigeon.apps.newgrounds.Fundamental.NG_Bus;
import jewpigeon.apps.newgrounds.Fundamental.NG_Events;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.Fundamental.NG_PreferencesData;
import jewpigeon.apps.newgrounds.PassportActivity;
import jewpigeon.apps.newgrounds.R;

public class PassportSignInFragment extends NG_Fragment implements View.OnClickListener {
    private EditText Username;
    private EditText Password;

    private MaterialButton LoginByNewgrounds;
    private MaterialButton LoginByFacebook;
    private MaterialButton LoginByGoogle;

    private TextView toForgotPassport;
    private TextView toSignUp;

    private String LABEL = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LABEL = getContext().getResources().getString(R.string.ng_PassportLabel_SignIn);
        try {
            getPassportActivity().setDashboardLabel(LABEL);
        }catch (NullPointerException e){

        }
    }


    public static PassportSignInFragment newInstance() {

        Bundle args = new Bundle();

        PassportSignInFragment fragment = new PassportSignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.passport_sign_in, container, false);
        setSeekerView(root);

        establishViews();
        setUpListeners();


        return root;
    }

    private void establishViews() {
        Username = (EditText) findViewById(R.id.SignIn_User);
        Password = (EditText) findViewById(R.id.SignIn_Password);

        LoginByNewgrounds = (MaterialButton) findViewById(R.id.SignIn_LoginByNG);
        LoginByFacebook = (MaterialButton) findViewById(R.id.SignIn_LoginByFacebook);
        LoginByGoogle = (MaterialButton) findViewById(R.id.SignIn_LoginByGoogle);

        toForgotPassport = (TextView) findViewById(R.id.SignIn_toForgotPassword);
        toSignUp = (TextView) findViewById(R.id.SignIn_toSignUp);
    }

    private void setUpListeners() {
        toSignUp.setOnClickListener(this);
        toForgotPassport.setOnClickListener(this);
        LoginByNewgrounds.setOnClickListener(this);
        LoginByGoogle.setOnClickListener(this);
        LoginByFacebook.setOnClickListener(this);
        LoginByNewgrounds.setOnClickListener(this);

    }


    private PassportActivity getPassportActivity() {
        return (PassportActivity) getNGActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SignIn_toSignUp: {
                getController().pushFragment(PassportSignUpFragment.newInstance());
                break;
            }
            case R.id.SignIn_toForgotPassword: {
                getController().pushFragment(PassportForgotFragment.newInstance());
                break;
            }
            case R.id.SignIn_LoginByNG: {
                if (checkFields()) {
                    NG_PreferencesData preferencesData = getNGActivity().getPreferencesFromStore();
                    if(preferencesData == null) preferencesData = new NG_PreferencesData();
                    preferencesData.setLogged(true);
                    getNGActivity().updatePreferences(preferencesData);
                    NG_Bus.get().post(new NG_Events.NG_UserLoggedIn());
                    getActivity().finish();
                }
                break;
            }

            case R.id.SignIn_LoginByGoogle:
            case R.id.SignIn_LoginByFacebook: {
                makeErrorSnackbar("ERROR: Not supported");
                break;
            }
        }
    }

    private boolean checkFields() {
        if (Username.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
            makeErrorSnackbar("ERROR: Not all fields are filled!");
            return false;
        }
        return true;
    }
}
