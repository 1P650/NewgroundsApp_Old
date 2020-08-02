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

public class PassportForgotFragment extends NG_Fragment implements View.OnClickListener {

    private String LABEL = "";

    private EditText UserOrEmail;
    private MaterialButton RecoverAccount;
    private TextView Cancel;

    public static PassportForgotFragment newInstance() {

        Bundle args = new Bundle();

        PassportForgotFragment fragment = new PassportForgotFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LABEL = getResources().getString(R.string.ng_PassportLabel_ForgotPassword);
        try {
            getPassportActivity().setDashboardLabel(LABEL);
        }catch (NullPointerException e){

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.passport_forgot_password, container, false);
        setSeekerView(root);
        establishViews();
        setUpListeners();
        return root;
    }


    private void establishViews() {
        UserOrEmail = (EditText) findViewById(R.id.ForgotPassword_EnterUserorEmail);
        RecoverAccount = (MaterialButton) findViewById(R.id.ForgotPassword_Recover);
        Cancel = (TextView) findViewById(R.id.ForgotPassword_toSignIn);
    }

    private void setUpListeners() {
        RecoverAccount.setOnClickListener(this);
        Cancel.setOnClickListener(this);
    }


    private PassportActivity getPassportActivity() {
        return (PassportActivity) getNGActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ForgotPassword_Recover: {
                if(UserOrEmail.getText().toString().isEmpty()){
                    makeErrorSnackbar("ERROR: Field is empty!");
                }
                else {
                    getActivity().finish();
                }
                break;
            }
            case R.id.ForgotPassword_toSignIn: {
                    getPassportActivity().setDashboardLabel(getPassportActivity().DEF_LABEL);
                    getController().popFragment();

                break;
            }
        }
    }
}
