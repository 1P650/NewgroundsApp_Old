package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ForumItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ArtistNewsItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.ForumItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.ArtistNewsItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItemClickListener;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ArtItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.AudioItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ListItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItemSmall;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.ArtItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.AudioItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.ListItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.GridItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.GridItemViewSmall;

public class ItemGenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final byte PORTAL_GRID_REGULAR = 0;
    public static final byte PORTAL_GRID_SMALL = 1;
    public static final byte PORTAL_FEATURED_AUDIO_LIST = 2;
    public static final byte PORTAL_ART_GRID = 3;
    public static final byte PORTAL_AUDIO_LIST = 4;
    public static final byte PORTAL_NEWS_LIST = 5;
    public static final byte PORTAL_FORUMS_LIST = 6;


    private ArrayList items;
    private DashItemClickListener ItemClickListener;
    private int ITEM_VIEW_TYPE;

    public ItemGenericAdapter(int viewType, ArrayList items) {
        this.ITEM_VIEW_TYPE = viewType;
        setItems(items);
    }



    public ItemGenericAdapter(ArrayList items) {
        setItems(items);
    }

    public ItemGenericAdapter() {

    }

    public ArrayList getItems() {
        return items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case PORTAL_GRID_SMALL: {
                itemView = new GridItemViewSmall(parent.getContext());
                return new DashGenericHolder(itemView);
            }
            case PORTAL_FEATURED_AUDIO_LIST: {
                itemView = new ListItemView(parent.getContext());
                return new DashGenericHolder(itemView);
            }
            case PORTAL_ART_GRID:{
                itemView = new ArtItemView(parent.getContext());
                return new DashGenericHolder(itemView);
            }

            case PORTAL_AUDIO_LIST:{
                itemView = new AudioItemView(parent.getContext());
                return new DashGenericHolder(itemView);
            }

            case PORTAL_NEWS_LIST:{
                itemView = new ArtistNewsItemView(parent.getContext());
                return new DashGenericHolder(itemView);
            }

            case PORTAL_FORUMS_LIST:{
                itemView = new ForumItemView(parent.getContext());
                return new DashGenericHolder(itemView);
            }
            default: {
                itemView = new GridItemView(parent.getContext());
                return new DashGenericHolder(itemView);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DashGenericHolder genHolder = (DashGenericHolder) holder;
        switch (holder.getItemViewType()) {

            case PORTAL_GRID_SMALL: {
                GridItemSmall item = (GridItemSmall) items.get(position);
                ((GridItemViewSmall)(genHolder.view)).setDashItem(item);
                break;
            }
            case PORTAL_FEATURED_AUDIO_LIST: {
                ListItem item = (ListItem) items.get(position);
                ((ListItemView)(genHolder.view)).setDashItem(item);
                if (position % 2 == 0) ((ListItemView) genHolder.view).enableBackground();
                break;
            }

            case PORTAL_AUDIO_LIST: {
                AudioItem item = (AudioItem) items.get(position);
                ((AudioItemView) genHolder.view).setDashItem(item);
                if (position % 2 == 0) ((AudioItemView) genHolder.view).enableBackground();
                break;
            }

            case PORTAL_ART_GRID: {
                ArtItem item = (ArtItem) items.get(position);
                ((ArtItemView) genHolder.view).setDashItem(item);
                break;
            }

            case PORTAL_NEWS_LIST:{
                ArtistNewsItem item = (ArtistNewsItem) items.get(position);
                ((ArtistNewsItemView) genHolder.view).setDashItem(item);
                if(position%2==0)  ((ArtistNewsItemView) genHolder.view).enableBackground();

                break;
            }
            case PORTAL_FORUMS_LIST:{
                ForumItem item = (ForumItem) items.get(position);
                ((ForumItemView) genHolder.view).setDashItem(item);
                if(position%2==0)  ((ForumItemView) genHolder.view).enableBackground();

                break;
            }

            default: {
                GridItem item = (GridItem) items.get(position);
                ((GridItemView) genHolder.view).setDashItem(item);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList items) {
        this.items = items;
    }


    @Override
    public int getItemViewType(int position) {
        return ITEM_VIEW_TYPE;

    }
    public void setOnItemClickListener(DashItemClickListener itemClickListener) {
        this.ItemClickListener = itemClickListener;
    }




    private final class DashGenericHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private View view;
        public DashGenericHolder(View view){
            super(view);
            this.view = view;
            this.view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(ItemClickListener != null) ItemClickListener.OnItemClick(view,getAdapterPosition());
        }
    }


}
