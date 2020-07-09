package jewpigeon.apps.newgrounds.ContentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.GridDecorator;

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
        RecyclerView BrandMoviesList = (RecyclerView) findViewById(R.id.movies_portal_brand_grid);
        DashGridAdapter brand_adapter = new DashGridAdapter(BrandMoviesArray);
        BrandMoviesList.addItemDecoration(new GridDecorator(8, 3));
        BrandMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        BrandMoviesList.setAdapter(brand_adapter);

        PopularMovies = (Dashboard) findViewById(R.id.movies_portal_popular_movies);
        RecyclerView PopularMoviesList = (RecyclerView) findViewById(R.id.movies_portal_popular_grid);
        DashGridAdapter popular_adapter = new DashGridAdapter(PopularMoviesArray);
        PopularMoviesList.addItemDecoration(new GridDecorator(8, 3));
        PopularMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        PopularMoviesList.setAdapter(popular_adapter);

        MonthMovies = (Dashboard) findViewById(R.id.movies_portal_month_movies);
        RecyclerView MonthMoviesList = (RecyclerView) findViewById(R.id.movies_portal_month_grid);
        DashGridAdapter month_adapter = new DashGridAdapter(MonthMoviesArray);
        MonthMoviesList.addItemDecoration(new GridDecorator(8, 3));
        MonthMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        MonthMoviesList.setAdapter(month_adapter);

        YearMovies = (Dashboard) findViewById(R.id.movies_portal_year_movies);
        RecyclerView YearMoviesList = (RecyclerView) findViewById(R.id.movies_portal_year_grid);
        DashGridAdapter year_adapter = new DashGridAdapter(YearMoviesArray);
        YearMoviesList.addItemDecoration(new GridDecorator(8, 3));
        YearMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        YearMoviesList.setAdapter(year_adapter);

        ClassicMovies = (Dashboard) findViewById(R.id.movies_portal_classic_movies);
        RecyclerView ClassicMoviesList = (RecyclerView) findViewById(R.id.movies_portal_classic_grid);
        DashGridAdapter classic_adapter = new DashGridAdapter(ClassicMoviesArray);
        ClassicMoviesList.addItemDecoration(new GridDecorator(8, 3));
        ClassicMoviesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        ClassicMoviesList.setAdapter(classic_adapter);

        return rootView;
    }
}
