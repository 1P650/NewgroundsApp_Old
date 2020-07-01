package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.R;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Dash_Item_Adapter extends RecyclerView.Adapter<Dash_Item_Adapter.DashHolder> {
    private ArrayList<DashItem> items;
    @NonNull
    @Override
    public DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     /*   View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_dashboard_item, parent, false);
        return new Item(v);*/
        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        DashView itemView = new DashView(parent.getContext());
        itemView.setLayoutParams(params);
        return new DashHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashHolder holder, int position) {
        DashItem item = items.get(position);
      /*  holder.Title.setText(item.getTitle());
        holder.Author.setText(item.getAuthor());
        if(item.getImage() != null){
           Glide.with(holder.Picture.getContext())
                    .load(item.getImage())
                    .into(holder.Picture);

        }*/
       holder.view.setDashItem(item);


    }

    public Dash_Item_Adapter(ArrayList<DashItem> items){
        this.items = items;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

     final class DashHolder extends RecyclerView.ViewHolder {
        private final DashView view;
      /*  private TextView Title;
        private TextView Author;
        private ImageView Picture;
        public DashHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.featured_dashboard_item_name);
            Author = itemView.findViewById(R.id.featured_dashboard_item_author_list);
            Picture = itemView.findViewById(R.id.featured_dashboard_item_picture);


        }*/
      public DashHolder(DashView view){
          super(view);
          this.view = view;

      }
    }


}
