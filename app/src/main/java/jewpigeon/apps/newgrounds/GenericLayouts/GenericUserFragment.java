package jewpigeon.apps.newgrounds.GenericLayouts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;



import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_HeavyFragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.AutofitGridLayout;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItemSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ListItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;

public class GenericUserFragment extends NG_HeavyFragment {
    private View rootView;


    private TabLayout UserTabs;

    private Dashboard UserMovies;
    private Dashboard UserFavoriteMovies;

    private Dashboard UserGames;
    private Dashboard UserFavoriteGames;

    private Dashboard UserArt;
    private Dashboard UserFavoriteArt;

    private Dashboard UserAudio;
    private Dashboard UserFavoriteAudio;

    private Dashboard UserMetaInfo;



    private AutofitGridLayout UserMoviesGrid;
    private AutofitGridLayout UserFavoriteMoviesGrid;

    private AutofitGridLayout UserGamesGrid;
    private AutofitGridLayout UserFavoriteGamesGrid;

    private AutofitGridLayout UserArtGrid;
    private AutofitGridLayout UserFavoriteArtGrid;

    private RecyclerView UserAudioList;
    private RecyclerView UserFavoriteAudioList;


    ArrayList<GridItem> UserMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> UserFavoriteMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));

    private ItemGenericAdapter userMoviesAdapter;
    private ItemGenericAdapter userFavMoviesAdapter;

    ArrayList<GridItem> UserGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> UserFavoriteGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));

    private ItemGenericAdapter userGamesAdapter;
    private ItemGenericAdapter userFavGamesAdapter;

    ArrayList<GridItemSmall> UserArtArray = new ArrayList<>(Arrays.asList(
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT")));

    ArrayList<GridItemSmall> UserFavoriteArtArray = new ArrayList<>(Arrays.asList(
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT")));

    private ItemGenericAdapter userArtAdapter;
    private ItemGenericAdapter userFavArtAdapter;

    ArrayList<ListItem> UserAudioArray = new ArrayList<>(Arrays.asList(
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT")));

    ArrayList<ListItem> UserFavoriteAudioArray = new ArrayList<>(Arrays.asList(
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT")));

    private ItemGenericAdapter userAudioAdapter;
    private ItemGenericAdapter userFavAudioAdapter;


    public static GenericUserFragment newInstance() {

        Bundle args = new Bundle();

        GenericUserFragment fragment = new GenericUserFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


        userMoviesAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_REGULAR, UserMoviesArray);
        userFavMoviesAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_REGULAR, UserFavoriteMoviesArray);

        userGamesAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_REGULAR, UserGamesArray);
        userFavGamesAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_REGULAR, UserFavoriteGamesArray);

        userArtAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_SMALL, UserArtArray);
        userFavArtAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_SMALL, UserFavoriteArtArray);

        userAudioAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_FEATURED_AUDIO_LIST, UserAudioArray);
        userFavAudioAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_FEATURED_AUDIO_LIST, UserFavoriteAudioArray);


    }

    public void setupTabView(){
        CharSequence[] labels = new CharSequence[]{"FANS", "NEWS", "ART", "AUDIO", "FAVES", "REVIEWS", "POSTS", "LINKS"};
        CharSequence[] counts = new CharSequence[]{"1", "0", "3", "6", "0", "0", "0", "0"};
        for (int i = 0; i < UserTabs.getTabCount(); i++) {
            UserTabs.getTabAt(i).setCustomView(R.layout.generic_usertab_layout);
            TextView label = (TextView) UserTabs.getTabAt(i).getCustomView().findViewById(R.id.generic_userTab_label);
            TextView count = UserTabs.getTabAt(i).getCustomView().findViewById(R.id.generic_userTab_count);
            label.setText(labels[i]);
            count.setText(counts[i]);
        }
    }
/*    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.generic_user_fragment, container,false);
        setSeekerView(rootView);
        postponeEnterTransition();

        UserTabs = (TabLayout) findViewById(R.id.generic_account_tabs);
        setupTabView();

        UserMovies = (Dashboard) findViewById(R.id.generic_account_movies);
        UserFavoriteMovies = (Dashboard) findViewById(R.id.generic_account_favMovies);

        UserMoviesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_movies_grid);
        UserFavoriteMoviesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_favMovies_grid);

        UserGames = (Dashboard) findViewById(R.id.generic_account_games);
        UserFavoriteGames = (Dashboard) findViewById(R.id.generic_account_favGames);

        UserGamesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_games_grid);
        UserFavoriteGamesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_favGames_grid);

        UserArt = (Dashboard) findViewById(R.id.generic_account_art);
        UserFavoriteArt = (Dashboard) findViewById(R.id.generic_account_favArt);

        UserArtGrid = (AutofitGridLayout) findViewById(R.id.generic_account_art_grid);
        UserFavoriteArtGrid = (AutofitGridLayout) findViewById(R.id.generic_account_favArt_grid);

        UserAudio = (Dashboard) findViewById(R.id.generic_account_audio);
        UserFavoriteAudio = (Dashboard) findViewById(R.id.generic_account_favAudio);

        UserAudioList = (RecyclerView) findViewById(R.id.generic_account_audio_list);
        UserFavoriteAudioList = (RecyclerView) findViewById(R.id.generic_account_favAudio_list);
        startPostponedEnterTransition();
        return rootView;
    }*/

    @Override
    public int getResourceId() {
        return R.layout.generic_user_fragment;
    }

    @Override
    public void onCreateStubView(View rootView, Bundle savedInstanceState) {
        setSeekerView(rootView);

        UserTabs = (TabLayout) findViewById(R.id.generic_account_tabs);
        setupTabView();

        UserMovies = (Dashboard) findViewById(R.id.generic_account_movies);
        UserFavoriteMovies = (Dashboard) findViewById(R.id.generic_account_favMovies);

        UserMoviesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_movies_grid);
        UserFavoriteMoviesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_favMovies_grid);

        UserGames = (Dashboard) findViewById(R.id.generic_account_games);
        UserFavoriteGames = (Dashboard) findViewById(R.id.generic_account_favGames);

        UserGamesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_games_grid);
        UserFavoriteGamesGrid = (AutofitGridLayout) findViewById(R.id.generic_account_favGames_grid);

        UserArt = (Dashboard) findViewById(R.id.generic_account_art);
        UserFavoriteArt = (Dashboard) findViewById(R.id.generic_account_favArt);

        UserArtGrid = (AutofitGridLayout) findViewById(R.id.generic_account_art_grid);
        UserFavoriteArtGrid = (AutofitGridLayout) findViewById(R.id.generic_account_favArt_grid);

        UserAudio = (Dashboard) findViewById(R.id.generic_account_audio);
        UserFavoriteAudio = (Dashboard) findViewById(R.id.generic_account_favAudio);

        UserAudioList = (RecyclerView) findViewById(R.id.generic_account_audio_list);
        UserFavoriteAudioList = (RecyclerView) findViewById(R.id.generic_account_favAudio_list);

        UserMoviesGrid.setAdapter(userMoviesAdapter);
        UserFavoriteMoviesGrid.setAdapter(userFavMoviesAdapter);

        UserGamesGrid.setAdapter(userGamesAdapter);
        UserFavoriteGamesGrid.setAdapter(userFavGamesAdapter);

        UserArtGrid.setAdapter(userArtAdapter);
        UserFavoriteArtGrid.setAdapter(userFavArtAdapter);

        UserAudioList.setAdapter(userAudioAdapter);
        UserAudioList.setLayoutManager(new LinearLayoutManager(getContext()));

        UserFavoriteAudioList.setAdapter(userFavAudioAdapter);
        UserFavoriteAudioList.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserMoviesGrid.setAdapter(userMoviesAdapter);
        UserFavoriteMoviesGrid.setAdapter(userFavMoviesAdapter);

        UserGamesGrid.setAdapter(userGamesAdapter);
        UserFavoriteGamesGrid.setAdapter(userFavGamesAdapter);

        UserArtGrid.setAdapter(userArtAdapter);
        UserFavoriteArtGrid.setAdapter(userFavArtAdapter);

        UserAudioList.setAdapter(userAudioAdapter);
        UserAudioList.setLayoutManager(new LinearLayoutManager(getContext()));

        UserFavoriteAudioList.setAdapter(userFavAudioAdapter);
        UserFavoriteAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
    }*/
}
