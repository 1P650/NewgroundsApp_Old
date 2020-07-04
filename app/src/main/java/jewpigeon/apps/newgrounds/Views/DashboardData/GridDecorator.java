package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class GridDecorator extends RecyclerView.ItemDecoration {
    private int space;
    private int columns_num;
    private int size;
    private byte HAVE_SIZE = 0x0;

    public GridDecorator(int space, int columns_num) {
        this.space = dpToPx(space);
        this.columns_num = columns_num;
    }

    public GridDecorator(int space, int columns_num, int size) {
        this.space = dpToPx(space);
        this.columns_num = columns_num;
        this.size = size;
        this.HAVE_SIZE = 0x1;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(HAVE_SIZE == 0x0){
            if (parent.getChildLayoutPosition(view) < columns_num) {
                outRect.bottom = space;
            } else {
                outRect.top = 0;
            }
        }
        else{
            Log.i("MYTAG", parent.getChildLayoutPosition(view) + "");
            if ((size - parent.getChildLayoutPosition(view) ) > columns_num) {
                outRect.bottom = space;
            } else {
                outRect.top = 0;
            }
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
