package jewpigeon.apps.newgrounds.ContentFragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.AutofitGrid;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ArtItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;

public class ArtPortal extends NG_Fragment {
    View rootView;

    private Dashboard FeaturedArt;
    private AutofitGrid FeaturedGrid;
    private ProgressBar FeaturedGridLoading;
    private NestedScrollView scrollView;

    ArrayList<ArtItem> FeaturedArtArray = new ArrayList<>(Arrays.asList(
            new ArtItem(null, "FOOKING FACEBOOK", "Facebook"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT"),
            new ArtItem(null, "DEFAULT", "DEFAULT")


    ));
    private ItemGenericAdapter adapter;




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
        for (int i = 0; i < FeaturedArtArray.size()/2; i++){
            FeaturedArtArray.get(i).setArtIcon(ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined));
        }

        adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_ART_GRID, FeaturedArtArray);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_art, container, false);
        setSeekerView(rootView);
        FeaturedArt = (Dashboard) findViewById(R.id.art_portal_browser);
        FeaturedGrid = (AutofitGrid) findViewById(R.id.art_portal_browser_grid);
        FeaturedGridLoading = (ProgressBar) findViewById(R.id.art_portal_loading);
        scrollView = (NestedScrollView) findViewById(R.id.art_portal_scroller);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                            scrollY > oldScrollY) {
                      loadDefault();
                    }
                }
            }
        });

        FeaturedGrid.setAdapter(adapter);
        return rootView;
    }

    private void loadDefault() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapter.getItemCount() > 128){ FeaturedGridLoading.setVisibility(View.GONE);return;}
                FeaturedArtArray.addAll(Arrays.asList(
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT"),
                        new ArtItem(null, "DEFAULT","DEFAULT")

                ));
                adapter.notifyDataSetChanged();
            }
        }, 2500);

    }


}



