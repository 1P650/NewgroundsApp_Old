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
import jewpigeon.apps.newgrounds.Views.AutofitGridLayout;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;

public class GamesPortal extends NG_Fragment {
    View rootView;
    Dashboard BrandGames;
    Dashboard PopularGames;
    Dashboard MonthGames;
    Dashboard YearGames;


    ArrayList<GridItem> BrandGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> PopularGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> MonthGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<GridItem> YearGamesArray = new ArrayList<>(Arrays.asList(
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT"),
            new GridItem(null, "DEFAULT", "DEFAULT")));
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
        AutofitGridLayout BrandGamesList = (AutofitGridLayout) findViewById(R.id.games_portal_brand_grid);
        ItemGenericAdapter brand_adapter = new ItemGenericAdapter(BrandGamesArray);
        BrandGamesList.setAdapter(brand_adapter);

        PopularGames = (Dashboard) findViewById(R.id.games_portal_popular_movies);
        AutofitGridLayout PopularGamesList = (AutofitGridLayout) findViewById(R.id.games_portal_popular_grid);
        ItemGenericAdapter popular_adapter = new ItemGenericAdapter(PopularGamesArray);
        PopularGamesList.setAdapter(popular_adapter);

        MonthGames = (Dashboard) findViewById(R.id.games_portal_month_movies);
        AutofitGridLayout MonthGamesList = (AutofitGridLayout) findViewById(R.id.games_portal_month_grid);
        ItemGenericAdapter month_adapter = new ItemGenericAdapter(MonthGamesArray);
        MonthGamesList.setAdapter(month_adapter);

        YearGames = (Dashboard) findViewById(R.id.games_portal_year_movies);
        AutofitGridLayout YearGamesList = (AutofitGridLayout) findViewById(R.id.games_portal_year_grid);
        ItemGenericAdapter year_adapter = new ItemGenericAdapter(BrandGamesArray);
        YearGamesList.setAdapter(year_adapter);

        startPostponedEnterTransition();
        return rootView;
    }
}
