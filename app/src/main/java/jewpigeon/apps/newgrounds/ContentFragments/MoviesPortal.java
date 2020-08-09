package jewpigeon.apps.newgrounds.ContentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.AutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;

public class MoviesPortal extends NG_Fragment {
    View rootView;
    Dashboard BrandMovies;
    Dashboard PopularMovies;
    Dashboard MonthMovies;
    Dashboard YearMovies;
    Dashboard ClassicMovies;



    ArrayList<GridItem> BrandMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> PopularMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> MonthMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> YearMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> ClassicMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));


    AutofitGrid BrandMoviesList;
    AutofitGrid PopularMoviesList;
    AutofitGrid MonthMoviesList;
    AutofitGrid YearMoviesList;
    AutofitGrid ClassicMoviesList;

    public static MoviesPortal newInstance() {

        Bundle args = new Bundle();

        MoviesPortal fragment = new MoviesPortal();
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

        rootView = inflater.inflate(R.layout.content_movies, container, false);
        setSeekerView(rootView);

        BrandMovies = (Dashboard) findViewById(R.id.movies_portal_brand_movies);
         BrandMoviesList = (AutofitGrid) findViewById(R.id.movies_portal_brand_grid);
        ItemGenericAdapter brand_adapter = new ItemGenericAdapter(BrandMoviesArray);
        BrandMoviesList.setAdapter(brand_adapter);

        PopularMovies = (Dashboard) findViewById(R.id.movies_portal_popular_movies);
         PopularMoviesList = (AutofitGrid) findViewById(R.id.movies_portal_popular_grid);
        ItemGenericAdapter popular_adapter = new ItemGenericAdapter(PopularMoviesArray);
        PopularMoviesList.setAdapter(popular_adapter);

        MonthMovies = (Dashboard) findViewById(R.id.movies_portal_month_movies);
         MonthMoviesList = (AutofitGrid) findViewById(R.id.movies_portal_month_grid);
        ItemGenericAdapter month_adapter = new ItemGenericAdapter(MonthMoviesArray);
        MonthMoviesList.setAdapter(month_adapter);

        YearMovies = (Dashboard) findViewById(R.id.movies_portal_year_movies);
         YearMoviesList = (AutofitGrid) findViewById(R.id.movies_portal_year_grid);
        ItemGenericAdapter year_adapter = new ItemGenericAdapter(YearMoviesArray);
        YearMoviesList.setAdapter(year_adapter);

        ClassicMovies = (Dashboard) findViewById(R.id.movies_portal_classic_movies);
         ClassicMoviesList = (AutofitGrid) findViewById(R.id.movies_portal_classic_grid);
        ItemGenericAdapter classic_adapter = new ItemGenericAdapter(ClassicMoviesArray);
        ClassicMoviesList.setAdapter(classic_adapter);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
