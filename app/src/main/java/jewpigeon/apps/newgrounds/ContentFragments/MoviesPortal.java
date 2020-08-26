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
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.GenericLayouts.GenericMovieFragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.AutofitGridLayout;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItemClickListener;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;

public class MoviesPortal extends NG_Fragment {
    View rootView;
    Dashboard BrandMovies;
    Dashboard PopularMovies;
    Dashboard MonthMovies;
    Dashboard YearMovies;
    Dashboard ClassicMovies;

    ItemGenericAdapter brand_adapter;
    ItemGenericAdapter popular_adapter;
    ItemGenericAdapter month_adapter;
    ItemGenericAdapter classic_adapter;
    ItemGenericAdapter year_adapter;


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
            new GridItem(null, "DEFAULT", "DEFAULT")
    ));
    ArrayList<GridItem> ClassicMoviesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")
    )
    );


    AutofitGridLayout BrandMoviesList;
    AutofitGridLayout PopularMoviesList;
    AutofitGridLayout MonthMoviesList;
    AutofitGridLayout YearMoviesList;
    AutofitGridLayout ClassicMoviesList;

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
        BrandMoviesList = (AutofitGridLayout) findViewById(R.id.movies_portal_brand_grid);
        brand_adapter = new ItemGenericAdapter(BrandMoviesArray);
        BrandMoviesList.setAdapter(brand_adapter);

        PopularMovies = (Dashboard) findViewById(R.id.movies_portal_popular_movies);
        PopularMoviesList = (AutofitGridLayout) findViewById(R.id.movies_portal_popular_grid);
        popular_adapter = new ItemGenericAdapter(PopularMoviesArray);
        PopularMoviesList.setAdapter(popular_adapter);

        MonthMovies = (Dashboard) findViewById(R.id.movies_portal_month_movies);
        MonthMoviesList = (AutofitGridLayout) findViewById(R.id.movies_portal_month_grid);
        month_adapter = new ItemGenericAdapter(MonthMoviesArray);
        MonthMoviesList.setAdapter(month_adapter);

        YearMovies = (Dashboard) findViewById(R.id.movies_portal_year_movies);
        YearMoviesList = (AutofitGridLayout) findViewById(R.id.movies_portal_year_grid);
        year_adapter = new ItemGenericAdapter(YearMoviesArray);
        YearMoviesList.setAdapter(year_adapter);

        ClassicMovies = (Dashboard) findViewById(R.id.movies_portal_classic_movies);
        ClassicMoviesList = (AutofitGridLayout) findViewById(R.id.movies_portal_classic_grid);
        classic_adapter = new ItemGenericAdapter(ClassicMoviesArray);
        ClassicMoviesList.setAdapter(classic_adapter);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        brand_adapter.setOnItemClickListener(new DashItemClickListener() {
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

        popular_adapter.setOnItemClickListener(new DashItemClickListener() {
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

        month_adapter.setOnItemClickListener(new DashItemClickListener() {
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


        year_adapter.setOnItemClickListener(new DashItemClickListener() {
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

        classic_adapter.setOnItemClickListener(new DashItemClickListener() {
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
    }
}


