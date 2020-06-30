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

public class Dash_Item_Adapter extends RecyclerView.Adapter<Dash_Item_Adapter.Item> {
    private ArrayList<DashboardItem> items;
    LayoutInflater inflater;
    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_dashboard_item, parent, false);
        return new Item(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        DashboardItem item = items.get(position);
        holder.Title.setText(item.getTitle());
        holder.Author.setText(item.getAuthor());
        if(item.getImage() != null){
            holder.Picture.setImageDrawable(item.getImage());
        }

    }

    public Dash_Item_Adapter(ArrayList<DashboardItem> items){
        this.items = items;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        private TextView Title;
        private TextView Author;
        private ImageView Picture;
        public Item(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.featured_dashboard_item_name);
            Author = itemView.findViewById(R.id.featured_dashboard_item_author_list);
            Picture = itemView.findViewById(R.id.featured_dashboard_item_picture);


        }
    }


}
