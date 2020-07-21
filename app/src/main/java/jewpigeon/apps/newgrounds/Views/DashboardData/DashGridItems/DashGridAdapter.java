package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItemClickListener;

public class DashGridAdapter extends RecyclerView.Adapter<DashGridAdapter.DashHolder> {
    private ArrayList<DashGridItem> items;
    private DashItemClickListener ItemClickListener;
    @NonNull
    @Override
    public DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        DashGridView itemView = new DashGridView(parent.getContext());

        return new DashHolder(itemView);
    }

    public void setOnItemClickListener(DashItemClickListener itemClickListener){
        this.ItemClickListener = itemClickListener;
    }



    @Override
    public void onBindViewHolder(@NonNull DashHolder holder, int position) {
        DashGridItem item = items.get(position);
        holder.view.setDashItem(item);


    }

    public DashGridAdapter(ArrayList<DashGridItem> items){
        this.items = items;
    }

    public void setItems(ArrayList<DashGridItem> items){
        this.items = items;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

     final class DashHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final DashGridView view;
      public DashHolder(DashGridView view){
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
