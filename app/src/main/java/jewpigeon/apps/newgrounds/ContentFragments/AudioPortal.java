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
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.AudioItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;

public class AudioPortal extends NG_Fragment {
    View rootView;

    Dashboard FeaturedAudio;
    RecyclerView FeaturedAudioList;
    ItemGenericAdapter adapter;
    private ProgressBar FeaturedListLoading;
    private NestedScrollView scrollView;

    ArrayList<AudioItem> FeaturedAudioArray = new ArrayList<>(Arrays.asList(
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT"),
            new AudioItem(null, "DEFAULT", "DEFAULT", "DEFAULT")
    ));



    public static AudioPortal newInstance() {

        Bundle args = new Bundle();

        AudioPortal fragment = new AudioPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_AUDIO_LIST, FeaturedAudioArray);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_audio, container, false);
        setSeekerView(rootView);
        FeaturedAudio = (Dashboard) findViewById(R.id.audio_portal_browser);
        FeaturedAudioList = (RecyclerView) findViewById(R.id.audio_portal_browser_list);
        FeaturedListLoading = (ProgressBar) findViewById(R.id.audio_portal_loading);
        scrollView = (NestedScrollView) findViewById(R.id.audio_portal_scroller);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        FeaturedAudioList.setLayoutManager(manager);

        scrollView = (NestedScrollView) findViewById(R.id.audio_portal_scroller);
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


        FeaturedAudioList.setAdapter(adapter);
        return rootView;
    }

    private void loadDefault() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapter.getItemCount() > 36){ FeaturedListLoading.setVisibility(View.GONE);return;}
                FeaturedAudioArray.addAll(Arrays.asList(
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT"),
                        new AudioItem(null, "DEFAULT","DEFAULT", "DEFAULT")

                ));
                adapter.notifyDataSetChanged();
            }
        }, 3000);

    }
}
