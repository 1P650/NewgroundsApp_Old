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
import jewpigeon.apps.newgrounds.Views.DashAutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;

public class GamesPortal extends NG_Fragment {
    View rootView;
    Dashboard BrandGames;
    Dashboard PopularGames;
    Dashboard MonthGames;
    Dashboard YearGames;


    ArrayList<DashGridItem> BrandGamesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> PopularGamesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> MonthGamesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashGridItem> YearGamesArray = new ArrayList<>(Arrays.asList(
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT"),
            new DashGridItem(null, "DEFAULT", "DEFAULT")));
    public static GamesPortal newInstance() {

        Bundle args = new Bundle();

        GamesPortal fragment = new GamesPortal();
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
        postponeEnterTransition();
        rootView = inflater.inflate(R.layout.content_games, container, false);
        setSeekerView(rootView);


        BrandGames = (Dashboard) findViewById(R.id.games_portal_brand_movies);
        DashAutofitGrid BrandGamesList = (DashAutofitGrid) findViewById(R.id.games_portal_brand_grid);
        DashGenericAdapter brand_adapter = new DashGenericAdapter(BrandGamesArray);
        BrandGamesList.setAdapter(brand_adapter);

        PopularGames = (Dashboard) findViewById(R.id.games_portal_popular_movies);
        DashAutofitGrid PopularGamesList = (DashAutofitGrid) findViewById(R.id.games_portal_popular_grid);
        DashGenericAdapter popular_adapter = new DashGenericAdapter(PopularGamesArray);
        PopularGamesList.setAdapter(popular_adapter);

        MonthGames = (Dashboard) findViewById(R.id.games_portal_month_movies);
        DashAutofitGrid MonthGamesList = (DashAutofitGrid) findViewById(R.id.games_portal_month_grid);
        DashGenericAdapter month_adapter = new DashGenericAdapter(MonthGamesArray);
        MonthGamesList.setAdapter(month_adapter);

        YearGames = (Dashboard) findViewById(R.id.games_portal_year_movies);
        DashAutofitGrid YearGamesList = (DashAutofitGrid) findViewById(R.id.games_portal_year_grid);
        DashGenericAdapter year_adapter = new DashGenericAdapter(BrandGamesArray);
        YearGamesList.setAdapter(year_adapter);

        startPostponedEnterTransition();
        return rootView;
    }
}
