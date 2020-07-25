package jewpigeon.apps.newgrounds.ContentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.DashAutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;

public class MoviesPortal extends NG_Fragment {
    View rootView;
    Dashboard BrandMovies;
    Dashboard PopularMovies;
    Dashboard MonthMovies;
    Dashboard YearMovies;
    Dashboard ClassicMovies;

    ArrayList<DashGridItem> BrandMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> PopularMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> MonthMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> YearMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> ClassicMoviesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));

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
        DashAutofitGrid BrandMoviesList = (DashAutofitGrid) findViewById(R.id.movies_portal_brand_grid);
        DashGenericAdapter brand_adapter = new DashGenericAdapter(BrandMoviesArray);
        BrandMoviesList.setAdapter(brand_adapter);

        PopularMovies = (Dashboard) findViewById(R.id.movies_portal_popular_movies);
        DashAutofitGrid PopularMoviesList = (DashAutofitGrid) findViewById(R.id.movies_portal_popular_grid);
        DashGenericAdapter popular_adapter = new DashGenericAdapter(PopularMoviesArray);
        PopularMoviesList.setAdapter(popular_adapter);

        MonthMovies = (Dashboard) findViewById(R.id.movies_portal_month_movies);
        DashAutofitGrid MonthMoviesList = (DashAutofitGrid) findViewById(R.id.movies_portal_month_grid);
        DashGenericAdapter month_adapter = new DashGenericAdapter(MonthMoviesArray);
        MonthMoviesList.setAdapter(month_adapter);

        YearMovies = (Dashboard) findViewById(R.id.movies_portal_year_movies);
        DashAutofitGrid YearMoviesList = (DashAutofitGrid) findViewById(R.id.movies_portal_year_grid);
        DashGenericAdapter year_adapter = new DashGenericAdapter(YearMoviesArray);
        YearMoviesList.setAdapter(year_adapter);

        ClassicMovies = (Dashboard) findViewById(R.id.movies_portal_classic_movies);
        DashAutofitGrid ClassicMoviesList = (DashAutofitGrid) findViewById(R.id.movies_portal_classic_grid);
        DashGenericAdapter classic_adapter = new DashGenericAdapter(ClassicMoviesArray);
        ClassicMoviesList.setAdapter(classic_adapter);

        return rootView;
    }
}
