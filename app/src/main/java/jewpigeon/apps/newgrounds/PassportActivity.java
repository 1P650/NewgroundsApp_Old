package jewpigeon.apps.newgrounds;

import androidx.fragment.app.Fragment;
import jewpigeon.apps.newgrounds.ContentFragments.ArtPortal;
import jewpigeon.apps.newgrounds.ContentFragments.AudioPortal;
import jewpigeon.apps.newgrounds.ContentFragments.CommunityPortal;
import jewpigeon.apps.newgrounds.ContentFragments.FeaturedPortal;
import jewpigeon.apps.newgrounds.ContentFragments.GamesPortal;
import jewpigeon.apps.newgrounds.ContentFragments.MoviesPortal;
import jewpigeon.apps.newgrounds.Fundamental.NG_Activity;
import jewpigeon.apps.newgrounds.PassportFragments.PassportForgotFragment;
import jewpigeon.apps.newgrounds.PassportFragments.PassportSignInFragment;
import jewpigeon.apps.newgrounds.PassportFragments.PassportSignUpFragment;
import jewpigeon.apps.newgrounds.Views.Dashboard;

import android.os.Bundle;

import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class PassportActivity extends NG_Activity {
    private Dashboard PassportDashboard;
    private FragNavController PassportController;
    private ArrayList<Fragment> PassportFragmentsList = new ArrayList<Fragment>(
            Arrays.asList(
                    PassportSignInFragment.newInstance()
            ));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
        establishViews();
        if(PassportController == null) {
            PassportController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.passport_content)
                    .rootFragments(PassportFragmentsList)
                    .selectedTabIndex(FragNavController.TAB1)
                    .defaultTransactionOptions(
                            FragNavTransactionOptions.newBuilder()
                            .customAnimations(R.anim.ng_frag_enter_anim, R.anim.ng_frag_leave_anim)
                            .build())
                    .build();
            setUpController(PassportController);
        }

    }

    @Override
    public void onBackPressed() {
        if(!PassportController.isRootFragment()){
            PassportController.popFragment();
            setDashboardTitle("Sign In");
        }
        else super.onBackPressed();
    }

    private void establishViews() {
        PassportDashboard = findViewById(R.id.passport_dashboard);
        setDashboardTitle("Sign in");
    }

    public void setDashboardTitle(String title){
        PassportDashboard.setLabelTitle(title);
    }
}