package jewpigeon.apps.newgrounds;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import jewpigeon.apps.newgrounds.Fundamental.NG_Activity;
import jewpigeon.apps.newgrounds.PassportFragments.PassportSignInFragment;
import jewpigeon.apps.newgrounds.Views.Dashboard;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class PassportActivity extends NG_Activity {
    private Dashboard PassportDashboard;
    private FragNavController PassportController;
    public String DEF_LABEL = "";
    private ArrayList<Fragment> PassportFragmentsList = new ArrayList<Fragment>(
            Arrays.asList(
                    PassportSignInFragment.newInstance()
            ));
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
        DEF_LABEL = this.getResources().getString(R.string.ng_PassportLabel_SignIn);
        establishViews();
        if(PassportController == null) {
            PassportController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.passport_content)
                    .rootFragments(PassportFragmentsList)
                    .selectedTabIndex(FragNavController.TAB1)
                    .defaultTransactionOptions(
                            FragNavTransactionOptions.newBuilder()
                                    .transition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .build())
                    .build();
            setUpController(PassportController);
        }

    }

    @Override
    public void onBackPressed() {
        if(!PassportController.isRootFragment()){
            PassportController.popFragment();
            setDashboardLabel(DEF_LABEL);
        }
        else super.onBackPressed();
    }

    private void establishViews() {
        PassportDashboard = findViewById(R.id.passport_dashboard);
        LayoutTransition lt = new LayoutTransition();
        lt.enableTransitionType(LayoutTransition.CHANGING);
        PassportDashboard.setLayoutTransition(lt);
    }

    public void setDashboardLabel(String title){
        PassportDashboard.setLabelTitle(title);
    }
}