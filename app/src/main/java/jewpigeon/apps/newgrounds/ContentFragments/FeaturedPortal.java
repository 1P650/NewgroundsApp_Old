package jewpigeon.apps.newgrounds.ContentFragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.voice.VoiceInteractionSession;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

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

import jewpigeon.apps.newgrounds.Utils.DimensionTool;
import jewpigeon.apps.newgrounds.Views.DashAutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashAutofitGridLayoutManager;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridDecorator;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapterSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAudioAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAudioItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItemSmall;

import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapter;


public class FeaturedPortal extends NG_Fragment {
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

/*    int COLUMNS;
    int COLUMNS_SMALL;*/

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


    /*    dmTool = new DimensionTool(getContext());

      SPACING = ((dmTool.screenWidth() - dmTool.GRID_PADDING*2) - (dmTool.GRID_ITEM_SIZE+30)*3)/2;
      Log.i("MYTAG","" + SPACING);*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //postponeEnterTransition();
        rootView = inflater.inflate(R.layout.content_featured, container, false);
        setSeekerView(rootView);

        int ITEM_SIZE_NORMAL = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_item_size);
        int ITEM_SIZE_SMALL = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_item_size_small);
        int COLUMNS_NORMAL = DimensionTool.GRID_calcColumsFor(ITEM_SIZE_NORMAL,getContext());
        int COLUMNS_NORMAL_SPACING = DimensionTool.GRID_calcSpacing(ITEM_SIZE_NORMAL, COLUMNS_NORMAL, getContext());
        int COLUMNS_SMALL = DimensionTool.GRID_calcColumsFor(ITEM_SIZE_SMALL,getContext());
        int COLUMNS_SMALL_SPACING = DimensionTool.GRID_calcSpacing(ITEM_SIZE_SMALL, COLUMNS_SMALL, getContext());

        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);

        DashAutofitGrid FeaturedMoviesList = (DashAutofitGrid) findViewById(R.id.featured_movies_grid);

        FeaturedMoviesList.setAdapter(movies_adapter);

       // FeaturedMoviesList.setLayoutManager(new DashAutofitGridLayoutManager(getContext(), ITEM_SIZE_NORMAL));
       // FeaturedMoviesList.addItemDecoration(new DashGridDecorator(COLUMNS_NORMAL, 30, COLUMNS_NORMAL_SPACING));

        FeaturedGames = (Dashboard) findViewById(R.id.featured_games);
        DashAutofitGrid FeaturedGamesList = (DashAutofitGrid) findViewById(R.id.featured_games_grid);


        FeaturedGamesList.setAdapter(games_adapter);
        //FeaturedGamesList.setLayoutManager(new DashAutofitGridLayoutManager(getContext(), ITEM_SIZE_NORMAL));
        //FeaturedGamesList.addItemDecoration(new DashGridDecorator(COLUMNS_NORMAL, 30,COLUMNS_NORMAL_SPACING));



        FeaturedArt = (Dashboard) findViewById(R.id.featured_art);
        DashAutofitGrid FeaturedArtList = (DashAutofitGrid) findViewById(R.id.featured_art_grid);

        FeaturedArtList.setAdapter(art_adapter);
        //FeaturedArtList.setLayoutManager(new DashAutofitGridLayoutManager(getContext(), ITEM_SIZE_SMALL));
        //FeaturedArtList.addItemDecoration(new DashGridDecorator(COLUMNS_SMALL, 30,COLUMNS_SMALL_SPACING));



        FeaturedAudio = (Dashboard) findViewById(R.id.featured_audio);
        RecyclerView FeaturedAudioList = (RecyclerView) findViewById(R.id.featured_audio_list);

        FeaturedAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedAudioList.setAdapter(audio_adapter);
        //startPostponedEnterTransition();
        return rootView;
    }

}
