package jewpigeon.apps.newgrounds.ContentFragments;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapterSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAudioAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAudioItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItemSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.ItemClickListener;


public class FeaturedPortal extends NG_Fragment implements ItemClickListener {
    View rootView;
    Dashboard FeaturedImage;
    Dashboard FeaturedMovies;
    Dashboard FeaturedGames;
    Dashboard FeaturedArt;
    Dashboard FeaturedAudio;


    /*private int SPACING;*/

    ArrayList<DashGridItem> FeaturedMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));


    private DashGridAdapter movies_adapter;
    private DashGridAdapter games_adapter;
    private DashGridAdapterSmall art_adapter;
    private DashGridAudioAdapter audio_adapter;

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


    ArrayList<DashGridAudioItem> FeaturedAudioArray = new ArrayList<>(Arrays.asList(
            new DashGridAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashGridAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashGridAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashGridAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashGridAudioItem(null, "DEFAULT", "DEFAULT"),
            new DashGridAudioItem(null, "DEFAULT", "DEFAULT")));

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
        movies_adapter = new DashGridAdapter(FeaturedMoviesArray);
        games_adapter = new DashGridAdapter(FeaturedGamesArray);
        art_adapter = new DashGridAdapterSmall(FeaturedArtArray);
        audio_adapter = new DashGridAudioAdapter(FeaturedAudioArray);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.content_featured, container, false);
        setSeekerView(rootView);

        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);
        FeaturedMoviesList = (DashAutofitGrid) findViewById(R.id.featured_movies_grid);
        movies_adapter.setOnItemClickListener(this);
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
        getController().pushFragment(
                GenericFragment.newInstance());
    }
}
