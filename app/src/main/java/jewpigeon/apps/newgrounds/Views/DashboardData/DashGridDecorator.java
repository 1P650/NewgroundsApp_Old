package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.dpToPx;

public class DashGridDecorator extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int verticalSpasing;
    private int horizontalSpasing;
    private boolean includeEdge;

    public DashGridDecorator(int spanCount, int verticalSpasing, int horizontalSpasing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.verticalSpasing = dpToPx(verticalSpasing);
        this.horizontalSpasing = dpToPx(horizontalSpasing);
        this.includeEdge = includeEdge;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        if (includeEdge) {
            outRect.left = horizontalSpasing - column * horizontalSpasing / spanCount;
            outRect.right = (column + 1) * horizontalSpasing / spanCount;

            if (position < spanCount) { // top edge
                outRect.top = verticalSpasing;
            }
            outRect.bottom = verticalSpasing; // item bottom
        } else {
            outRect.left = column * horizontalSpasing / spanCount;
            outRect.right = horizontalSpasing - (column + 1) * horizontalSpasing / spanCount;
            if (position >= spanCount) {
                outRect.top = verticalSpasing;
            }
        }
    }




}