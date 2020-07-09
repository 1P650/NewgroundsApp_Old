package jewpigeon.apps.newgrounds;

import android.content.ContentResolver;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;


import java.util.ArrayList;
import java.util.Arrays;



import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import jewpigeon.apps.newgrounds.ContentFragments.ArtPortal;
import jewpigeon.apps.newgrounds.ContentFragments.AudioPortal;
import jewpigeon.apps.newgrounds.ContentFragments.CommunityPortal;
import jewpigeon.apps.newgrounds.ContentFragments.FeaturedPortal;
import jewpigeon.apps.newgrounds.ContentFragments.GamesPortal;
import jewpigeon.apps.newgrounds.ContentFragments.MoviesPortal;
import jewpigeon.apps.newgrounds.Fundamental.NG_Activity;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;

public class ContentActivity extends NG_Activity implements
        FragNavController.RootFragmentListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    private Toolbar ContentToolbar;
    private ImageButton HomeButton;
    private ImageButton SearchButton;
    private SearchView ContentSearch;
    private AppBarLayout contentAppBarLayout;



    private BottomNavigationView ContentBottomBar;
    private HideBottomViewOnScrollBehavior contentBottomBarBehaviour;

    private NavigationView ContentLeftMenu;
    private AdvanceDrawerLayout ContentDrawerLayout;
    private ActionBarDrawerToggle ContentDrawerToggle;

    private FragNavController ContentFragmentsController;
    private ArrayList<Fragment> ContentFragmentsList = new ArrayList<Fragment>(
            Arrays.asList(
                    MoviesPortal.newInstance(),
                    AudioPortal.newInstance(),
                    ArtPortal.newInstance(),
                    GamesPortal.newInstance(),
                    CommunityPortal.newInstance(),
                    FeaturedPortal.newInstance()
            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        establishViews();
        if(ContentFragmentsController == null){
            ContentFragmentsController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.content_container)
                    .rootFragments(ContentFragmentsList)
                    .selectedTabIndex(FragNavController.TAB6)
                    .defaultTransactionOptions(FragNavTransactionOptions.newBuilder().customAnimations(R.anim.ng_frag_enter_anim, R.anim.ng_frag_leave_anim).build())
                    .build();
            super.setUpController(ContentFragmentsController);
        }

        setUpListeners();


    }

    private void establishViews() {

        ContentToolbar = findViewById(R.id.content_toolbar);
        contentAppBarLayout = findViewById(R.id.content_appbarlayout);
        

        ContentBottomBar = findViewById(R.id.content_bottombar);
        contentBottomBarBehaviour = (HideBottomViewOnScrollBehavior) ((CoordinatorLayout.LayoutParams) ContentBottomBar.getLayoutParams()).getBehavior();;
        ContentBottomBar.getMenu().getItem(0).setCheckable(false);

        ContentDrawerLayout = (AdvanceDrawerLayout) findViewById(R.id.content_drawerlayout);
        ContentLeftMenu = findViewById(R.id.content_left_menu);

        HomeButton = findViewById(R.id.NG_appbar_home);
        SearchButton = findViewById(R.id.NG_appbar_search);
        ContentSearch = findViewById(R.id.content_search);


        setSupportActionBar(ContentToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ContentDrawerToggle = new ActionBarDrawerToggle(this, ContentDrawerLayout, ContentToolbar, 0, 0);
        ContentDrawerLayout.addDrawerListener(ContentDrawerToggle);

        ContentDrawerToggle.setDrawerIndicatorEnabled(true);
        ContentDrawerToggle.syncState();

        ContentDrawerLayout.useCustomBehavior(GravityCompat.START);
        ContentDrawerLayout.setViewScrimColor(GravityCompat.START, getResources().getColor(R.color.colorLeftMenuShadow));
        ContentDrawerLayout.setDrawerElevation(GravityCompat.START, 20);




    }

    private void setUpListeners() {
        ContentBottomBar.setOnNavigationItemSelectedListener(this);
        ContentLeftMenu.setNavigationItemSelectedListener(this);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getController().switchTab(FragNavController.TAB6);
            }
        });

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContentSearch.getVisibility() == View.GONE){
                    ContentSearch.setVisibility(View.VISIBLE);
                    ContentSearch.setIconified(false);
                }
                else{
                    ContentSearch.setIconified(true);
                    ContentSearch.setVisibility(View.GONE);

                }

            }
        });
        ContentSearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ContentSearch.setVisibility(View.GONE);
                return false;
            }
        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ContentDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration conf) {
        super.onConfigurationChanged(conf);
        ContentDrawerToggle.onConfigurationChanged(conf);
    }



    @Override
    public Fragment getRootFragment(int identifier) {
        switch (identifier) {
            case FragNavController.TAB1:
                return MoviesPortal.newInstance();
            case FragNavController.TAB2:
                return AudioPortal.newInstance();
            case FragNavController.TAB3:
                return ArtPortal.newInstance();
            case FragNavController.TAB4:
                return GamesPortal.newInstance();
            case FragNavController.TAB5:
                return CommunityPortal.newInstance();
            case FragNavController.TAB6:
                return FeaturedPortal.newInstance();
        }
        throw new IllegalArgumentException("No such index");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ContentFragmentsController.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (ContentDrawerLayout.isDrawerVisible(ContentLeftMenu))
            ContentDrawerLayout.closeDrawer(ContentLeftMenu);
        if(ContentSearch.getVisibility() == View.VISIBLE && ContentSearch.isIconified()) ContentSearch.setVisibility(View.GONE);
        else if (!HandleBackpressed()) {
            super.onBackPressed();
        }

    }

    private boolean HandleBackpressed() {
        if (getController().isRootFragment()) {
            if (getController().getCurrentFrag() instanceof FeaturedPortal) {
                getController().clearStack();
                return false;
            } else {
                getController().switchTab(FragNavController.TAB6);
                return true;
            }
        } else {
            getController().popFragment();
            return true;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        contentAppBarLayout.setExpanded(true, true);
        item.setCheckable(true);
        if (ContentDrawerLayout.isDrawerOpen(ContentLeftMenu)) ContentDrawerLayout.closeDrawer(ContentLeftMenu);
        contentBottomBarBehaviour.slideUp(ContentBottomBar);


        switch (item.getItemId()) {
            case R.id.bottom_content_movies:

                getController().switchTab(FragNavController.TAB1);
                return true;
            case R.id.bottom_content_audio:

                getController().switchTab(FragNavController.TAB2);
                return true;
            case R.id.bottom_content_art:
                getController().switchTab(FragNavController.TAB3);
                return true;
            case R.id.bottom_content_games:

                getController().switchTab(FragNavController.TAB4);
                return true;
            case R.id.bottom_content_community:

                getController().switchTab(FragNavController.TAB5);
                return true;


            case R.id.leftmenu_content_movies:

                ContentBottomBar.setSelectedItemId(R.id.bottom_content_movies);
                getController().switchTab(FragNavController.TAB1);
                return true;
            case R.id.leftmenu_content_audio:
                ContentBottomBar.setSelectedItemId(R.id.bottom_content_audio);
                getController().switchTab(FragNavController.TAB2);
                return true;
            case R.id.leftmenu_content_art:
                ContentBottomBar.setSelectedItemId(R.id.bottom_content_art);
                getController().switchTab(FragNavController.TAB3);
                return true;
            case R.id.leftmenu_content_games:
                ContentBottomBar.setSelectedItemId(R.id.bottom_content_games);
                getController().switchTab(FragNavController.TAB4);
                return true;
            case R.id.leftmenu_content_community:
                ContentBottomBar.setSelectedItemId(R.id.bottom_content_community);
                getController().switchTab(FragNavController.TAB5);
                return true;


            default:
                ContentBottomBar.setSelected(false);
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (ContentFragmentsController != null) {

            getController().onSaveInstanceState(outState);

        }
    }


}