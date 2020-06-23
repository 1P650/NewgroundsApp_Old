package jewpigeon.apps.newgrounds;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.ncapdevi.fragnav.FragNavController;


import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
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

    private BottomNavigationView ContentBottomBar;

    private NavigationView ContentLeftMenu;
    private DrawerLayout ContentDrawerLayout;
    private ActionBarDrawerToggle ContentDrawerToggle;

    private FragNavController ContentFragmentsController;
    private ArrayList<Fragment> ContentFragmentsList = new ArrayList<>(
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

        ContentFragmentsController = new FragNavController(getSupportFragmentManager(), R.id.content_container);
        ContentFragmentsController.setRootFragments(ContentFragmentsList);
        ContentFragmentsController.initialize(FragNavController.TAB6, savedInstanceState);
        super.setUpController(ContentFragmentsController);

        setUpListeners();


    }

    private void establishViews() {

        ContentToolbar = findViewById(R.id.content_toolbar);


        ContentBottomBar = findViewById(R.id.content_bottombar);
        ContentDrawerLayout = findViewById(R.id.content_drawerlayout);
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

        ContentDrawerLayout.setScrimColor(getResources().getColor(R.color.colorLeftMenuShadow));
        ContentDrawerLayout.setDrawerElevation(0);
    }

    private void setUpListeners() {
        ContentBottomBar.setOnNavigationItemSelectedListener(this);
        ContentLeftMenu.setNavigationItemSelectedListener(this);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getController().clearStack();
                getController().switchTab(FragNavController.TAB6);
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContentSearch.getVisibility() == View.GONE){
                    ContentSearch.setVisibility(View.VISIBLE);
                    setMargins(ContentSearch,0,getResources().getDimensionPixelSize(R.dimen.ng_toolbar_top_margin),0,0);
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
    public int getNumberOfRootFragments() {
        return 5;
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
        if (ContentDrawerLayout.isDrawerOpen(ContentLeftMenu))
            ContentDrawerLayout.closeDrawer(ContentLeftMenu);
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
                return false;
        }
    }
}