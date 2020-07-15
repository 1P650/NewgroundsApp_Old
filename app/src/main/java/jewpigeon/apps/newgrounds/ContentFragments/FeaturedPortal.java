package jewpigeon.apps.newgrounds.ContentFragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
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
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridDecorator;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapterSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItemSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridViewSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.Dash_ItemList_Adapter;

import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapter;

import jewpigeon.apps.newgrounds.Views.DashboardData.GridDecorator;

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.GRID_ITEM_SIZE;
import static jewpigeon.apps.newgrounds.Utils.DimensionTool.GRID_PADDING;
import static jewpigeon.apps.newgrounds.Utils.DimensionTool.pxToDp;
import static jewpigeon.apps.newgrounds.Utils.DimensionTool.screenHeight;
import static jewpigeon.apps.newgrounds.Utils.DimensionTool.screenWidth;


public class FeaturedPortal extends NG_Fragment {
    View rootView;
    Dashboard FeaturedImage;
    Dashboard FeaturedMovies;
    Dashboard FeaturedGames;
    Dashboard FeaturedArt;
    Dashboard FeaturedAudio;


    /*private int SPACING;*/

    ArrayList<DashGridItem> FeaturedMoviesArray = new ArrayList<>(Arrays.asList(
    new DashGridItem(null, "Elon Musk's first day on mars", "chrisNG"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT"),
    new DashGridItem(null, "DEFAULT", "DEFAULT")));


    private DashGridAdapter movies_adapter;
    private DashGridAdapter games_adapter;
    private DashGridAdapterSmall art_adapter;
    private Dash_ItemList_Adapter audio_adapter;

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
        art_adapter = new DashGridAdapterSmall(FeaturedArtArray);
        audio_adapter = new Dash_ItemList_Adapter(FeaturedAudioArray);

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

/*        COLUMNS = DimensionTool.calculateNoOfColumns(this.getContext(), pxToDp((int) getContext().getResources().getDimension(R.dimen.dashboard_item_size)));
        COLUMNS_SMALL = DimensionTool.calculateNoOfColumns(this.getContext(),pxToDp((int) getContext().getResources().getDimension(R.dimen.dashboard_item_size_small)) -1);
        if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            SPACING = ((screenWidth(getContext()) - GRID_PADDING(getContext())*2) - (GRID_ITEM_SIZE(getContext())+GRID_ITEM_SIZE(getContext())/10)*COLUMNS)/2;
        }
        else {
            SPACING = ((screenHeight(getContext()) - GRID_PADDING(getContext())*2) - (GRID_ITEM_SIZE(getContext())-GRID_ITEM_SIZE(getContext())/10)*COLUMNS)/2;
        }*/


        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);

        RecyclerView FeaturedMoviesList = (RecyclerView) findViewById(R.id.featured_movies_grid);

        FeaturedMoviesList.setAdapter(movies_adapter);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(metrics);
        FeaturedMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeaturedMoviesList.addItemDecoration(new DashGridDecorator(3, 12, 16,  true));

        FeaturedGames = (Dashboard) findViewById(R.id.featured_games);
        RecyclerView FeaturedGamesList = (RecyclerView) findViewById(R.id.featured_games_grid);


        FeaturedGamesList.setAdapter(games_adapter);
        FeaturedGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeaturedGamesList.addItemDecoration(new DashGridDecorator(3, 12,16,  true));



        FeaturedArt = (Dashboard) findViewById(R.id.featured_art);
        RecyclerView FeaturedArtList = (RecyclerView) findViewById(R.id.featured_art_grid);

        FeaturedArtList.setAdapter(art_adapter);
        FeaturedArtList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        FeaturedArtList.addItemDecoration(new DashGridDecorator(4, 12,16,true));



        FeaturedAudio = (Dashboard) findViewById(R.id.featured_audio);
        RecyclerView FeaturedAudioList = (RecyclerView) findViewById(R.id.featured_audio_list);

        FeaturedAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedAudioList.setAdapter(audio_adapter);
        //startPostponedEnterTransition();
        return rootView;
    }



}
