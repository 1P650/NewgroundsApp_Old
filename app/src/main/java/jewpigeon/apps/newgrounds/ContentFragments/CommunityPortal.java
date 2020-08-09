package jewpigeon.apps.newgrounds.ContentFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Fundamental.NG_Fragment;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.Dashboard;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ForumItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.NewsItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.ItemGenericAdapter;

public class CommunityPortal extends NG_Fragment {
    View rootView;

    private Dashboard ArtistNews;
    private Dashboard Forums;

    private ArrayList<NewsItem> ArtistNewsArray = new ArrayList<NewsItem>(Arrays.asList(
            new NewsItem(null, "DEFAULT", "DEFAULT"),
            new NewsItem(null, "DEFAULT", "DEFAULT"),
            new NewsItem(null, "DEFAULT", "DEFAULT")
    ));

    private ArrayList<ForumItem> ForumsArray = new ArrayList<>(Arrays.asList(
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0),
      new ForumItem(null, null, "DEFAULT", "DEFAULT", new Date(0, 0, 0), 0)


    ));

    private ItemGenericAdapter ArtistNewsAdapter;
    private ItemGenericAdapter ForumsAdapter;


    public static CommunityPortal newInstance() {

        Bundle args = new Bundle();

        CommunityPortal fragment = new CommunityPortal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArtistNewsAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_NEWS_LIST, ArtistNewsArray);
        ForumsAdapter = new ItemGenericAdapter(ItemGenericAdapter.PORTAL_FORUMS_LIST, ForumsArray);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_community, container, false);
        setSeekerView(rootView);

        ArtistNews = (Dashboard) findViewById(R.id.community_artistNews);
        Forums = (Dashboard) findViewById(R.id.community_Forums);

        RecyclerView ArtistNewsList = (RecyclerView) findViewById(R.id.community_artistNews_list);
        ArtistNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        ArtistNewsList.setAdapter(ArtistNewsAdapter);
        RecyclerView ForumsList = (RecyclerView) findViewById(R.id.community_Forums_list);
        ForumsList.setLayoutManager(new LinearLayoutManager(getContext()));
        ForumsList.setAdapter(ForumsAdapter);





        return rootView;
    }
}
