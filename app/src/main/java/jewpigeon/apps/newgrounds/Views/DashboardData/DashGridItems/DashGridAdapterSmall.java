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

public class DashGridAdapterSmall extends RecyclerView.Adapter<DashGridAdapterSmall.DashHolder> {
    private ArrayList<DashGridItemSmall> items;

    LayoutInflater inflater;

    @NonNull
    @Override
    public DashGridAdapterSmall.DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        DashGridViewSmall itemView = new DashGridViewSmall(parent.getContext());
        itemView.setLayoutParams(params);
        return new DashGridAdapterSmall.DashHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashGridAdapterSmall.DashHolder holder, int position) {
        DashGridItemSmall item = items.get(position);
        holder.view.setDashItem(item);

    }

    public DashGridAdapterSmall(ArrayList<DashGridItemSmall> items) {
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
