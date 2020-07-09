package jewpigeon.apps.newgrounds.ContentFragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridDecorator;
import jewpigeon.apps.newgrounds.Views.DashboardData.Dash_ItemList_Adapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashSmallGridAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashSmallGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.GridDecorator;


public class FeaturedPortal extends NG_Fragment {
    View rootView;
    Dashboard FeaturedImage;
    Dashboard FeaturedMovies;
    Dashboard FeaturedGames;
    Dashboard FeaturedArt;
    Dashboard FeaturedAudio;

    ArrayList<DashGridItem> FeaturedMoviesArray = new ArrayList<>(Arrays.asList(
    new DashGridItem(null, "Elon Musk's first day on mars", "chrisNG"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT")));


    private DashGridAdapter movies_adapter;
    private DashGridAdapter games_adapter;
    private DashSmallGridAdapter art_adapter;
    private Dash_ItemList_Adapter audio_adapter;

    ArrayList<DashGridItem> FeaturedGamesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));

    ArrayList<DashSmallGridItem> FeaturedArtArray = new ArrayList<>(Arrays.asList(
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT"),
            new DashSmallGridItem(null, "DEFAULT")));

    ArrayList<DashGridItem> FeaturedAudioArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    public static FeaturedPortal newInstance() {
        
        Bundle args = new Bundle();
        
        FeaturedPortal fragment = new FeaturedPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FeaturedMoviesArray.get(0).setImage(ContextCompat.getDrawable(getContext(), R.drawable.elon_musk));
        setRetainInstance(true);
        movies_adapter = new DashGridAdapter(FeaturedMoviesArray);
        games_adapter = new DashGridAdapter(FeaturedGamesArray);
        art_adapter = new DashSmallGridAdapter(FeaturedArtArray);
        audio_adapter = new Dash_ItemList_Adapter(FeaturedAudioArray);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition();
        rootView = inflater.inflate(R.layout.content_featured, container, false);
        setSeekerView(rootView);


        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);

        RecyclerView FeaturedMoviesList = (RecyclerView) findViewById(R.id.featured_movies_grid);

        FeaturedMoviesList.setAdapter(movies_adapter);
        FeaturedMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeaturedMoviesList.addItemDecoration(new DashGridDecorator(3, 12,16,  true));


        FeaturedGames = (Dashboard) findViewById(R.id.featured_games);
        RecyclerView FeaturedGamesList = (RecyclerView) findViewById(R.id.featured_games_grid);


        FeaturedGamesList.setAdapter(games_adapter);
        FeaturedGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeaturedGamesList.addItemDecoration(new DashGridDecorator(3, 12,16,  true));



        FeaturedArt = (Dashboard) findViewById(R.id.featured_art);
        RecyclerView FeaturedArtList = (RecyclerView) findViewById(R.id.featured_art_grid);
        DashSmallGridAdapter art_adapter = new DashSmallGridAdapter(FeaturedArtArray);

        FeaturedArtList.setAdapter(art_adapter);
        FeaturedArtList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        FeaturedArtList.addItemDecoration(new DashGridDecorator(4, 12,16,true));



        FeaturedAudio = (Dashboard) findViewById(R.id.featured_audio);
        RecyclerView FeaturedAudioList = (RecyclerView) findViewById(R.id.featured_audio_list);

        FeaturedAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedAudioList.setAdapter(audio_adapter);
      startPostponedEnterTransition();
        return rootView;
    }



}
