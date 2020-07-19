package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.dpToPx;
import static jewpigeon.apps.newgrounds.Utils.DimensionTool.sp;

public class DashGridDecorator extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int verticalSpasing;
    private int horizontalSpasing;
    private boolean includeEdge;

    public DashGridDecorator(int spanCount, int verticalSpasing, int horizontalSpasing) {
        this.spanCount = spanCount;
        this.verticalSpasing = verticalSpasing;
        this.horizontalSpasing = (horizontalSpasing);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int index = parent.getChildAdapterPosition(view);
        int column = index % spanCount;
        //int totalItem = parent.getAdapter().getItemCount();
        outRect.left = horizontalSpasing - column * horizontalSpasing / spanCount;
        outRect.right = (column + 1) * horizontalSpasing / spanCount;
        if (index < spanCount) {
            outRect.top = verticalSpasing;
        }
            outRect.bottom = verticalSpasing;


    }
}