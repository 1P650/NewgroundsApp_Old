package jewpigeon.apps.newgrounds.ContentFragments;

import android.os.Bundle;
import android.util.Log;
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
import jewpigeon.apps.newgrounds.GenericLayouts.GenericFragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.DashAutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashAudioItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItemSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItemClickListener;


public class FeaturedPortal extends NG_Fragment implements DashItemClickListener {
    View rootView;
    Dashboard FeaturedImage;
    Dashboard FeaturedMovies;
    Dashboard FeaturedGames;
    Dashboard FeaturedArt;
    Dashboard FeaturedAudio;


    ArrayList<DashGridItem> FeaturedMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));


    private DashGenericAdapter movies_adapter;
    private DashGenericAdapter games_adapter;
    private DashGenericAdapter art_adapter;
    private DashGenericAdapter audio_adapter;

    private DashAutofitGrid FeaturedMoviesList;
    private DashAutofitGrid FeaturedGamesList;
    private DashAutofitGrid FeaturedArtList;
    private RecyclerView FeaturedAudioList;


    ArrayList<DashGridItem> FeaturedGamesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));

    ArrayList<DashGridItemSmall> FeaturedArtArray = new ArrayList<>(Arrays.asList(
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT"),
            new DashGridItemSmall(null, "DEFAULT")));


    ArrayList<DashAudioItem> FeaturedAudioArray = new ArrayList<>(Arrays.asList(
            new DashAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashAudioItem(null, "DEFAULT", "DEFAULT")));

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
        movies_adapter = new DashGenericAdapter(DashGenericAdapter.PORTAL_GRID_REGULAR, FeaturedMoviesArray);
        games_adapter = new DashGenericAdapter(DashGenericAdapter.PORTAL_GRID_REGULAR, FeaturedGamesArray);
        art_adapter = new DashGenericAdapter(DashGenericAdapter.PORTAL_GRID_SMALL, FeaturedArtArray);
        audio_adapter = new DashGenericAdapter(DashGenericAdapter.PORTAL_AUDIO_LIST, FeaturedAudioArray);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.content_featured, container, false);
        setSeekerView(rootView);

        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);
        FeaturedMoviesList = (DashAutofitGrid) findViewById(R.id.featured_movies_grid);
        FeaturedMoviesList.setAdapter(movies_adapter);


        FeaturedGames = (Dashboard) findViewById(R.id.featured_games);
        FeaturedGamesList = (DashAutofitGrid) findViewById(R.id.featured_games_grid);
        FeaturedGamesList.setAdapter(games_adapter);

        FeaturedArt = (Dashboard) findViewById(R.id.featured_art);
        FeaturedArtList = (DashAutofitGrid) findViewById(R.id.featured_art_grid);
        FeaturedArtList.setAdapter(art_adapter);

        FeaturedAudio = (Dashboard) findViewById(R.id.featured_audio);
        FeaturedAudioList = (RecyclerView) findViewById(R.id.featured_audio_list);
        FeaturedAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedAudioList.setAdapter(audio_adapter);

        return rootView;
    }

    @Override
    public void OnItemClick(View view, int position) {
        DashGridItem item = FeaturedMoviesArray.get(position);
        getController().pushFragment(GenericFragment.newInstance());

    }
}
