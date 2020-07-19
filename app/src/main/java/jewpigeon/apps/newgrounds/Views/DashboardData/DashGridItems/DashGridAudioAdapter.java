package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class DashGridAudioAdapter extends RecyclerView.Adapter<DashGridAudioAdapter.DashHolder> {
    private ArrayList<DashGridAudioItem> items;

    LayoutInflater inflater;

    @NonNull
    @Override
    public DashGridAudioAdapter.DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        DashGridAudioView itemView = new DashGridAudioView(parent.getContext());
        itemView.setLayoutParams(params);
        return new DashGridAudioAdapter.DashHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashGridAudioAdapter.DashHolder holder, int position) {
        DashGridAudioItem item = items.get(position);
        holder.view.setDashItem(item);
        if(position % 2 == 0) holder.view.enableBackground();

    }

    public DashGridAudioAdapter(ArrayList<DashGridAudioItem> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    final class DashHolder extends RecyclerView.ViewHolder {
        private final DashGridAudioView view;
        public DashHolder(DashGridAudioView view){
            super(view);
            this.view = view;

        }
    }
}