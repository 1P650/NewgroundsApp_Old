package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItemClickListener;

public class DashGenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final byte PORTAL_GRID_REGULAR = 0;
    public static final byte PORTAL_GRID_SMALL = 1;
    public static final byte PORTAL_AUDIO_LIST = 2;


    private ArrayList items;
    private DashItemClickListener ItemClickListener;
    private int ITEM_VIEW_TYPE;

    public DashGenericAdapter(int viewType, ArrayList items) {
        this.ITEM_VIEW_TYPE = viewType;
        setItems(items);
    }

    public DashGenericAdapter(ArrayList items) {
        setItems(items);
    }

    public DashGenericAdapter() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case PORTAL_GRID_SMALL: {
                DashGridViewSmall itemView = new DashGridViewSmall(parent.getContext());
                return new DashGridHolder_Small(itemView);
            }
            case PORTAL_AUDIO_LIST: {
                DashAudioView itemView = new DashAudioView(parent.getContext());
                return new DashGridHolder_Audio(itemView);
            }
            default: {
                DashGridView itemView = new DashGridView(parent.getContext());
                return new DashGridHolder_Regular(itemView);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {

            case PORTAL_GRID_SMALL: {
                DashGridItemSmall item = (DashGridItemSmall) items.get(position);
                ((DashGridHolder_Small) holder).view.setDashItem(item);
                break;
            }
            case PORTAL_AUDIO_LIST: {
                DashAudioItem item = (DashAudioItem) items.get(position);
                ((DashGridHolder_Audio) holder).view.setDashItem(item);
                if (position % 2 == 0) ((DashGridHolder_Audio) holder).view.enableBackground();
                break;
            }

            default: {
                DashGridItem item = (DashGridItem) items.get(position);
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
        private final DashGridView view;

        public DashGridHolder_Regular(DashGridView view) {
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
        private final DashGridViewSmall view;

        public DashGridHolder_Small(DashGridViewSmall view) {
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
        private final DashAudioView view;

        public DashGridHolder_Audio(DashAudioView view) {
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
