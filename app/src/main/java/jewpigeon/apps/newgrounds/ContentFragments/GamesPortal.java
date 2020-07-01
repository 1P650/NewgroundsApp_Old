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
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.Dash_Item_Adapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.GridDecorator;

public class GamesPortal extends NG_Fragment {
    View rootView;
    Dashboard BrandGames;
    Dashboard PopularGames;
    Dashboard MonthGames;
    Dashboard YearGames;


    ArrayList<DashItem> BrandGamesArray = new ArrayList<>(Arrays.asList(
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashItem> PopularGamesArray = new ArrayList<>(Arrays.asList(
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashItem> MonthGamesArray = new ArrayList<>(Arrays.asList(
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT")));
    ArrayList<DashItem> YearGamesArray = new ArrayList<>(Arrays.asList(
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT"),
            new DashItem(null, "DEFAULT", "DEFAULT")));
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
        rootView = inflater.inflate(R.layout.content_games, container, false);
        setSeekerView(rootView);

        BrandGames = (Dashboard) findViewById(R.id.games_portal_brand_movies);
        RecyclerView BrandGamesList = (RecyclerView) findViewById(R.id.games_portal_brand_grid);
        Dash_Item_Adapter brand_adapter = new Dash_Item_Adapter(BrandGamesArray);
        BrandGamesList.addItemDecoration(new GridDecorator(8, 3));
        BrandGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        BrandGamesList.setAdapter(brand_adapter);

        PopularGames = (Dashboard) findViewById(R.id.games_portal_popular_movies);
        RecyclerView PopularGamesList = (RecyclerView) findViewById(R.id.games_portal_popular_grid);
        Dash_Item_Adapter popular_adapter = new Dash_Item_Adapter(PopularGamesArray);
        PopularGamesList.addItemDecoration(new GridDecorator(8, 3));
        PopularGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        PopularGamesList.setAdapter(popular_adapter);

        MonthGames = (Dashboard) findViewById(R.id.games_portal_month_movies);
        RecyclerView MonthGamesList = (RecyclerView) findViewById(R.id.games_portal_month_grid);
        Dash_Item_Adapter month_adapter = new Dash_Item_Adapter(MonthGamesArray);
        MonthGamesList.addItemDecoration(new GridDecorator(8, 3));
        MonthGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        MonthGamesList.setAdapter(month_adapter);

        YearGames = (Dashboard) findViewById(R.id.games_portal_year_movies);
        RecyclerView YearGamesList = (RecyclerView) findViewById(R.id.games_portal_year_grid);
        Dash_Item_Adapter year_adapter = new Dash_Item_Adapter(YearGamesArray);
        YearGamesList.addItemDecoration(new GridDecorator(8, 3));
        YearGamesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        YearGamesList.setAdapter(year_adapter);

        return rootView;
    }
}
