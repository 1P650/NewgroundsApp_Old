package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

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
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = horizontalSpasing - column * horizontalSpasing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * horizontalSpasing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = verticalSpasing;
            }
            outRect.bottom = verticalSpasing; // item bottom
        } else {
            outRect.left = column * horizontalSpasing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = horizontalSpasing - (column + 1) * horizontalSpasing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = verticalSpasing; // item top
            }
        }
    }

    private int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


}