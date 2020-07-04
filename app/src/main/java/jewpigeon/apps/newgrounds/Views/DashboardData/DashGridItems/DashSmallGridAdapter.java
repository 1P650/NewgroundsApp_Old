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

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DashSmallGridAdapter extends RecyclerView.Adapter<DashSmallGridAdapter.DashHolder> {
    private ArrayList<DashSmallGridItem> items;

    LayoutInflater inflater;

    @NonNull
    @Override
    public DashSmallGridAdapter.DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        DashGridViewSmall itemView = new DashGridViewSmall(parent.getContext());
        itemView.setLayoutParams(params);
        return new DashSmallGridAdapter.DashHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashSmallGridAdapter.DashHolder holder, int position) {
        DashSmallGridItem item = items.get(position);
        holder.view.setDashItem(item);

    }

    public DashSmallGridAdapter(ArrayList<DashSmallGridItem> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    final class DashHolder extends RecyclerView.ViewHolder {
        private final DashGridViewSmall view;
        public DashHolder(DashGridViewSmall view){
            super(view);
            this.view = view;

        }
    }
}
