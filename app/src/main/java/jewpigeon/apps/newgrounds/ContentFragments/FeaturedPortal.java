package jewpigeon.apps.newgrounds.ContentFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.GenericLayouts.GenericArtFragment;
import jewpigeon.apps.newgrounds.GenericLayouts.GenericMovieFragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.AutofitGridLayout;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ListItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ArtistNewsItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItemSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItemClickListener;
import jewpigeon.apps.newgrounds.Views.Dashboard;


public class FeaturedPortal extends NG_Fragment {
    View rootView;
    Dashboard FeaturedImage;
    Dashboard FeaturedMovies;
    Dashboard FeaturedGames;
    Dashboard FeaturedArt;
    Dashboard FeaturedAudio;
    Dashboard FeaturedArtistNews;

    private ItemGenericAdapter movies_adapter;
    private ItemGenericAdapter games_adapter;
    private ItemGenericAdapter art_adapter;
    private ItemGenericAdapter audio_adapter;
    private ItemGenericAdapter artistNews_adapter;

    private AutofitGridLayout FeaturedMoviesList;
    private AutofitGridLayout FeaturedGamesList;
    private AutofitGridLayout FeaturedArtList;
    private RecyclerView FeaturedAudioList;
    private RecyclerView FeaturedArtistNewsList;

    ArrayList<GridItem> FeaturedMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT","DEFAULT"),
            new GridItem(null, "DEFAULT","DEFAULT"),
            new GridItem(null, "DEFAULT","DEFAULT"),
            new GridItem(null, "DEFAULT","DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")
    ));

    private ArrayList<ArtistNewsItem> ArtistNewsArray = new ArrayList<ArtistNewsItem>(Arrays.asList(
            new ArtistNewsItem(null, "DEFAULT", "DEFAULT"),
            new ArtistNewsItem(null, "DEFAULT", "DEFAULT"),
            new ArtistNewsItem(null, "DEFAULT", "DEFAULT")
    ));

    ArrayList<GridItem> FeaturedGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")
    ));

    ArrayList<GridItemSmall> FeaturedArtArray = new ArrayList<>(Arrays.asList(
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT"),
            new GridItemSmall(null, "DEFAULT")));


    ArrayList<ListItem> FeaturedAudioArray = new ArrayList<>(Arrays.asList(
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT"),
            new ListItem(null, "DEFAULT", "DEFAULT")));

    public static FeaturedPortal newInstance() {

        Bundle args = new Bundle();

        FeaturedPortal fragment = new FeaturedPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        movies_adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_REGULAR, FeaturedMoviesArray);
        games_adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_REGULAR, FeaturedGamesArray);
        art_adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_GRID_SMALL, FeaturedArtArray);
        audio_adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_FEATURED_AUDIO_LIST, FeaturedAudioArray);
        artistNews_adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_NEWS_LIST, ArtistNewsArray);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition();
        rootView = inflater.inflate(R.layout.content_featured, container, false);
        setSeekerView(rootView);

        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);
        FeaturedMoviesList = (AutofitGridLayout) findViewById(R.id.featured_movies_grid);

        FeaturedGames = (Dashboard) findViewById(R.id.featured_games);
        FeaturedGamesList = (AutofitGridLayout) findViewById(R.id.featured_games_grid);


        FeaturedArt = (Dashboard) findViewById(R.id.featured_art);
        FeaturedArtList = (AutofitGridLayout) findViewById(R.id.featured_art_grid);


        FeaturedAudio = (Dashboard) findViewById(R.id.featured_audio);
        FeaturedAudioList = (RecyclerView) findViewById(R.id.featured_audio_list);

        FeaturedArtistNews = (Dashboard) findViewById(R.id.featured_artist_news);
        FeaturedArtistNewsList = (RecyclerView) findViewById(R.id.featured_artistNews_list);


        startPostponedEnterTransition();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movies_adapter.setOnItemClickListener(new DashItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                new Handler().postDelayed(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        getController().pushFragment(
                                GenericMovieFragment.newInstance(bundle)
                        );
                    }
                }, 300);


            }
        });

        art_adapter.setOnItemClickListener(new DashItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                new Handler().postDelayed(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        getController().pushFragment(
                                GenericArtFragment.newInstance(bundle)
                        );
                    }
                }, 300);
            }
        });

        FeaturedMoviesList.setAdapter(movies_adapter);

        FeaturedGamesList.setAdapter(games_adapter);

        FeaturedArtList.setAdapter(art_adapter);

        FeaturedAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedAudioList.setAdapter(audio_adapter);

        FeaturedArtistNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedArtistNewsList.setAdapter(artistNews_adapter);

    }
}
