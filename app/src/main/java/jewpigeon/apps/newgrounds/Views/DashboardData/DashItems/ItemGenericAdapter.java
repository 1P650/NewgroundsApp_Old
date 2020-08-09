package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ForumItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.NewsItem;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.ForumItemView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews.NewsItemView;
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
        switch (viewType) {
            case PORTAL_GRID_SMALL: {
                GridItemViewSmall itemView = new GridItemViewSmall(parent.getContext());
                return new DashGridHolder_Small(itemView);
            }
            case PORTAL_FEATURED_AUDIO_LIST: {
                ListItemView itemView = new ListItemView(parent.getContext());
                return new DashGridHolder_Featured_Audio(itemView);
            }
            case PORTAL_ART_GRID:{
                ArtItemView itemView = new ArtItemView(parent.getContext());
                return new DashGridHolder_Art(itemView);
            }

            case PORTAL_AUDIO_LIST:{
                AudioItemView itemView = new AudioItemView(parent.getContext());
                return new DashGridHolder_Audio(itemView);
            }

            case PORTAL_NEWS_LIST:{
                NewsItemView itemView = new NewsItemView(parent.getContext());
                return new DashGridHolder_News(itemView);
            }

            case PORTAL_FORUMS_LIST:{
                ForumItemView itemView = new ForumItemView(parent.getContext());
                return new DashGridHolder_Forums(itemView);
            }
            default: {
                GridItemView itemView = new GridItemView(parent.getContext());
                return new DashGridHolder_Regular(itemView);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {

            case PORTAL_GRID_SMALL: {
                GridItemSmall item = (GridItemSmall) items.get(position);
                ((DashGridHolder_Small) holder).view.setDashItem(item);
                break;
            }
            case PORTAL_FEATURED_AUDIO_LIST: {
                ListItem item = (ListItem) items.get(position);
                ((DashGridHolder_Featured_Audio) holder).view.setDashItem(item);
                if (position % 2 == 0) ((DashGridHolder_Featured_Audio) holder).view.enableBackground();
                break;
            }

            case PORTAL_AUDIO_LIST: {
                AudioItem item = (AudioItem) items.get(position);
                ((DashGridHolder_Audio) holder).view.setDashItem(item);
                if (position % 2 == 0) ((DashGridHolder_Audio) holder).view.enableBackground();
                break;
            }

            case PORTAL_ART_GRID: {
                ArtItem item = (ArtItem) items.get(position);
                ((DashGridHolder_Art) holder).view.setDashItem(item);
                break;
            }

            case PORTAL_NEWS_LIST:{
                NewsItem item = (NewsItem) items.get(position);
                if(position%2==0) ((DashGridHolder_News) holder).view.enableBackground();
                ((DashGridHolder_News) holder).view.setDashItem(item);
                break;
            }
            case PORTAL_FORUMS_LIST:{
                ForumItem item = (ForumItem) items.get(position);
                if(position%2==0) ((DashGridHolder_Forums) holder).view.enableBackground();
                ((DashGridHolder_Forums) holder).view.setDashItem(item);
                break;
            }

            default: {
                GridItem item = (GridItem) items.get(position);
                ((DashGridHolder_Regular) holder).view.setDashItem(item);
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

    private final class DashGridHolder_Regular extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final GridItemView view;

        public DashGridHolder_Regular(GridItemView view) {
            super(view);
            this.view = view;
            this.view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(DashItemClickListener itemClickListener) {
        this.ItemClickListener = itemClickListener;
    }


    private final class DashGridHolder_Small extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final GridItemViewSmall view;

        public DashGridHolder_Small(GridItemViewSmall view) {
            super(view);
            this.view = view;

        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());

        }
    }

    private final class DashGridHolder_Featured_Audio extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ListItemView view;

        public DashGridHolder_Featured_Audio(ListItemView view) {
            super(view);
            this.view = view;

        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());
        }
    }

    private final class DashGridHolder_Art extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ArtItemView view;

        public DashGridHolder_Art(ArtItemView view) {
            super(view);
            this.view = view;

        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());
        }
    }

    private final class DashGridHolder_Audio extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final AudioItemView view;

        public DashGridHolder_Audio(AudioItemView view) {
            super(view);
            this.view = view;

        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());
        }
    }

    private final class DashGridHolder_News extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final NewsItemView view;

        public DashGridHolder_News(NewsItemView view) {
            super(view);
            this.view = view;

        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());
        }
    }

    private final class DashGridHolder_Forums extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ForumItemView view;

        public DashGridHolder_Forums(ForumItemView view) {
            super(view);
            this.view = view;

        }

        @Override
        public void onClick(View view) {
            if (ItemClickListener != null)
                ItemClickListener.OnItemClick(view, getAdapterPosition());
        }
    }


}
