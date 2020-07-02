package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.R;

public class DashSmallGridAdapter extends RecyclerView.Adapter<DashSmallGridAdapter.Item> {
    private ArrayList<DashSmallGridItem> items;

    LayoutInflater inflater;

    @NonNull
    @Override
    public DashSmallGridAdapter.Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_dashboard_item_small, parent, false);
        return new DashSmallGridAdapter.Item(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashSmallGridAdapter.Item holder, int position) {
        DashSmallGridItem item = items.get(position);
        holder.Author.setText(item.getAuthor());
        if (item.getImage() != null) {
            holder.Picture.setImageDrawable(item.getImage());
        }

    }

    public DashSmallGridAdapter(ArrayList<DashSmallGridItem> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        private TextView Author;
        private ImageView Picture;

        public Item(@NonNull View itemView) {
            super(itemView);
            Author = itemView.findViewById(R.id.featured_dashboard_item_author_small);
            Picture = itemView.findViewById(R.id.featured_dashboard_item_picture_small);


        }
    }
}
