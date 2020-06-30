package ferox.ip.newgrounds.Views.DashboardData;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class GridDecorator extends RecyclerView.ItemDecoration {
    private int space;
    private int columns_num;
    public GridDecorator(int space, int columns_num) {
        this.space = dpToPx(space);
        this.columns_num = columns_num;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) < columns_num) {
            outRect.bottom = space;
        } else {
            outRect.top = 0;
        }


    }
    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    private int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
