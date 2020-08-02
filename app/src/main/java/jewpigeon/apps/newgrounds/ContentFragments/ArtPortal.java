package jewpigeon.apps.newgrounds.ContentFragments;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.DashAutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashEndlessScrollListener;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashArtItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGenericAdapter;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems.DashGridItemSmall;

public class ArtPortal extends NG_Fragment {
    View rootView;

    private Dashboard FeaturedArt;
    private DashAutofitGrid FeaturedGrid;
    private ProgressBar FeaturedGridLoading;

    ArrayList<DashArtItem> FeaturedArtArray = new ArrayList<>(Arrays.asList(
            new DashArtItem(null, "FOOKING FACEBOOK", "Facebook"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT"),
            new DashArtItem(null, "DEFAULT", "DEFAULT")


    ));
    private DashGenericAdapter adapter;




    public static ArtPortal newInstance() {

        Bundle args = new Bundle();

        ArtPortal fragment = new ArtPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        FeaturedArtArray.get(0).setArtIcon(ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined));
        adapter = new DashGenericAdapter(DashGenericAdapter.PORTAL_ART_GRID, FeaturedArtArray);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_art, container, false);
        setSeekerView(rootView);
        FeaturedArt = (Dashboard) findViewById(R.id.art_portal_browser);
        FeaturedGrid = (DashAutofitGrid) findViewById(R.id.art_portal_browser_grid);
        FeaturedGridLoading = (ProgressBar) findViewById(R.id.art_portal_loading);

        LayoutTransition lt = new LayoutTransition();
        lt.enableTransitionType(LayoutTransition.CHANGING);
        FeaturedArt.setLayoutTransition(lt);

        DashEndlessScrollListener scrollListener = new DashEndlessScrollListener(FeaturedGrid.getManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadDefault();
            }
        };
        FeaturedGrid.setAdapter(adapter);
        FeaturedGrid.addOnScrollListener(scrollListener);
        return rootView;
    }

    private void loadDefault() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapter.getItemCount() > 128){ FeaturedGridLoading.setVisibility(View.GONE);return;}
                FeaturedArtArray.addAll(Arrays.asList(
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT"),
                        new DashArtItem(null, "DEFAULT","DEFAULT")

                ));
                adapter.notifyDataSetChanged();
            }
        }, 3000);

    }
    }



