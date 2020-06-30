package ferox.ip.newgrounds.ContentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ferox.ip.newgrounds.Fundamental.NG_Fragment;
import ferox.ip.newgrounds.R;
import ferox.ip.newgrounds.Views.Dashboard;
import ferox.ip.newgrounds.Views.DashboardData.Dash_ItemList_Adapter;
import ferox.ip.newgrounds.Views.DashboardData.Dash_ItemSmall_Adapter;
import ferox.ip.newgrounds.Views.DashboardData.DashboardItem;
import ferox.ip.newgrounds.Views.DashboardData.Dash_Item_Adapter;
import ferox.ip.newgrounds.Views.DashboardData.DashboardItemSmall;
import ferox.ip.newgrounds.Views.DashboardData.GridDecorator;


public class FeaturedPortal extends NG_Fragment {
    View rootView;
    Dashboard FeaturedImage;
    Dashboard FeaturedMovies;
    Dashboard FeaturedGames;
    Dashboard FeaturedArt;
    Dashboard FeaturedAudio;
    Dashboard ArtistNews;

    ArrayList<DashboardItem> FeaturedMoviesArray = new ArrayList<>(Arrays.asList(
    new DashboardItem(null, "DEFAULT", "DEFAULT"),
    new DashboardItem(null, "DEFAULT", "DEFAULT"),
    new DashboardItem(null, "DEFAULT", "DEFAULT"),
    new DashboardItem(null, "DEFAULT", "DEFAULT"),
    new DashboardItem(null, "DEFAULT", "DEFAULT"),
    new DashboardItem(null, "DEFAULT", "DEFAULT")));

    ArrayList<DashboardItem> FeaturedGamesArray = new ArrayList<>(Arrays.asList(
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT")));

    ArrayList<DashboardItemSmall> FeaturedArtArray = new ArrayList<>(Arrays.asList(
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT"),
            new DashboardItemSmall(null, "DEFAULT")));

    ArrayList<DashboardItem> FeaturedAudioArray = new ArrayList<>(Arrays.asList(
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT"),
            new DashboardItem(null, "DEFAULT", "DEFAULT")));
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_featured, container, false);
        setSeekerView(rootView);

        FeaturedImage = (Dashboard) findViewById(R.id.featured_picture);

        FeaturedMovies = (Dashboard) findViewById(R.id.featured_movies);
        RecyclerView FeaturedMoviesList = (RecyclerView) findViewById(R.id.featured_movies_grid);
        Dash_Item_Adapter movies_adapter = new Dash_Item_Adapter(FeaturedMoviesArray);
        FeaturedMoviesList.addItemDecoration(new GridDecorator(8, 3));
        FeaturedMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeaturedMoviesList.setAdapter(movies_adapter);

        FeaturedGames = (Dashboard) findViewById(R.id.featured_games);
        RecyclerView FeaturedGamesList = (RecyclerView) findViewById(R.id.featured_games_grid);
        Dash_Item_Adapter games_adapter = new Dash_Item_Adapter(FeaturedGamesArray);
        FeaturedGamesList.addItemDecoration(new GridDecorator(8, 3));
        FeaturedGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FeaturedGamesList.setAdapter(games_adapter);

        FeaturedArt = (Dashboard) findViewById(R.id.featured_art);
        RecyclerView FeaturedArtList = (RecyclerView) findViewById(R.id.featured_art_grid);
        Dash_ItemSmall_Adapter art_adapter = new Dash_ItemSmall_Adapter(FeaturedArtArray);
        FeaturedArtList.addItemDecoration(new GridDecorator(8, 4));
        FeaturedArtList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        FeaturedArtList.setAdapter(art_adapter);

        FeaturedAudio = (Dashboard) findViewById(R.id.featured_audio);
        RecyclerView FeaturedAudioList = (RecyclerView) findViewById(R.id.featured_audio_list);
        Dash_ItemList_Adapter audio_adapter = new Dash_ItemList_Adapter(FeaturedAudioArray);
        FeaturedAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturedAudioList.setAdapter(audio_adapter);

        return rootView;
    }


}
