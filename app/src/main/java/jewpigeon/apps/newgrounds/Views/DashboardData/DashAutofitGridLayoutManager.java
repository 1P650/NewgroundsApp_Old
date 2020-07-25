package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;

public class DashAutofitGridLayoutManager extends GridLayoutManager {

    private int columnWidth;
    private boolean columnWidthChanged = true;
    private Context context;
    public DashAutofitGridLayoutManager(Context context, int columnWidth) {
        super(context, 1);
        setColumnWidth(columnWidth);
        this.context = context;
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
            int spanCount = Math.max(1, DimensionTool.GRID_calcColumsFor(columnWidth,totalSpace));
            setSpanCount(spanCount);
        }


        super.onLayoutChildren(recycler, state);
    }



}