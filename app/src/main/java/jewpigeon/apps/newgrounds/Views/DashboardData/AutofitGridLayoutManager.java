package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;
import jewpigeon.apps.newgrounds.Views.AutofitGridLayout;

public class AutofitGridLayoutManager extends GridLayoutManager {

    private int columnWidth;
    private AutofitGridLayout grid;
    private AutofitGridDecorator gridDecorator;
    private boolean columnWidthChanged = true;

    public AutofitGridLayoutManager(Context context, int columnWidth) {
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

        if (columnWidthChanged && columnWidth > 0) {
            int totalSpace;
            totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            int spanCount = Math.max(1, DimensionTool.GRID_calcColumsFor(columnWidth, totalSpace));
            setSpanCount(spanCount);
        }


        super.onLayoutChildren(recycler, state);
    }

}

