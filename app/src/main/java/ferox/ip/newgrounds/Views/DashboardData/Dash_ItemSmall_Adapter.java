package ferox.ip.newgrounds.Views.DashboardData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ferox.ip.newgrounds.R;

public class Dash_ItemSmall_Adapter extends RecyclerView.Adapter<Dash_ItemSmall_Adapter.Item> {
    private ArrayList<DashboardItemSmall> items;

    LayoutInflater inflater;

    @NonNull
    @Override
    public Dash_ItemSmall_Adapter.Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_dashboard_item_small, parent, false);
        return new Dash_ItemSmall_Adapter.Item(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Dash_ItemSmall_Adapter.Item holder, int position) {
        DashboardItemSmall item = items.get(position);
        holder.Author.setText(item.getAuthor());
        if (item.getImage() != null) {
            holder.Picture.setImageDrawable(item.getImage());
        }

    }

    public Dash_ItemSmall_Adapter(ArrayList<DashboardItemSmall> items) {
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
