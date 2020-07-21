package jewpigeon.apps.newgrounds.PassportFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.PassportActivity;
import jewpigeon.apps.newgrounds.R;


public class PassportSignUpFragment extends NG_Fragment implements View.OnClickListener {


    private String LABEL = "";

    private EditText Username;
    private EditText Password;
    private EditText PasswordRetyped;
    private EditText Email;

    private CheckBox RecieveEmail;

    private MaterialButton CreateAccount;

    private TextView toSignIn;


    public static PassportSignUpFragment newInstance() {

        Bundle args = new Bundle();

        PassportSignUpFragment fragment = new PassportSignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LABEL = getContext().getResources().getString(R.string.ng_PassportLabel_SignUp);
        try {
            getPassportActivity().setDashboardLabel(LABEL);
        }catch (NullPointerException e){

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.passport_sign_up, container, false);
        setSeekerView(root);
        establishViews();
        setUpListeners();
        return root;
    }

    private void establishViews() {
        Username = (EditText) findViewById(R.id.SignUp_Username);
        Password = (EditText) findViewById(R.id.SignUp_Password);
        PasswordRetyped = (EditText) findViewById(R.id.SignUp_RetypePassword);
        Email = (EditText) findViewById(R.id.SignUp_Email);

        CreateAccount = (MaterialButton) findViewById(R.id.SignUp_CreateAccount);

        RecieveEmail = (CheckBox) findViewById(R.id.SignUp_RecieveEmail);

        toSignIn = (TextView) findViewById(R.id.SignUp_toSignIn);
    }

    private void setUpListeners() {
        CreateAccount.setOnClickListener(this);
        toSignIn.setOnClickListener(this);
    }


    private PassportActivity getPassportActivity() {
        return (PassportActivity) getNGActivity();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.SignUp_CreateAccount: {
                if (checkFields() && checkPasswordEqual())
                    getActivity().finish();
                    break;
            }
            case R.id.SignUp_toSignIn: {
                getPassportActivity().setDashboardLabel(getPassportActivity().DEF_LABEL);
                getController().popFragment();
                break;
            }
        }

    }


    private boolean checkFields() {
        if (
                Username.getText().toString().isEmpty()
                        || Password.getText().toString().isEmpty()
                        || PasswordRetyped.getText().toString().isEmpty()
                        || Email.getText().toString().isEmpty()
        ) {
            makeErrorSnackbar("ERROR: Not all fields are filled!");
            return false;
        }
        return true;
    }

    private boolean checkPasswordEqual() {
        if (Password.getText().toString().equals(PasswordRetyped.getText().toString())) return true;
        makeErrorSnackbar("ERROR: Passwords are not equal!");
        return false;
    }



}
