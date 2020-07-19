package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashAutofitGridLayoutManager extends GridLayoutManager {

    private int columnWidth;
    private boolean columnWidthChanged = true;
    public DashAutofitGridLayoutManager(Context context, int columnWidth) {
        super(context, 1);
        setColumnWidth(columnWidth);
    }
    public void setColumnWidth(int newColumnWidth) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth;
            columnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if  (columnWidthChanged && columnWidth > 0) {
            int totalSpace;
            totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            int spanCount = Math.max(1, totalSpace / columnWidth);
            setSpanCount(spanCount);
        }


        super.onLayoutChildren(recycler, state);
    }



}