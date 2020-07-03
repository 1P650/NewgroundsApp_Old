package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DashGridAdapter extends RecyclerView.Adapter<DashGridAdapter.DashHolder> {
    private ArrayList<DashGridItem> items;
    @NonNull
    @Override
    public DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        DashGridView itemView = new DashGridView(parent.getContext());
        itemView.setLayoutParams(params);
        return new DashHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashHolder holder, int position) {
        DashGridItem item = items.get(position);
        holder.view.setDashItem(item);


    }

    public DashGridAdapter(ArrayList<DashGridItem> items){
        this.items = items;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

     final class DashHolder extends RecyclerView.ViewHolder {
        private final DashGridView view;
      public DashHolder(DashGridView view){
          super(view);
          this.view = view;

      }
    }


}
