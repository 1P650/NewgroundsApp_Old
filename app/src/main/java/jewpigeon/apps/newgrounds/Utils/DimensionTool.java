package jewpigeon.apps.newgrounds.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import jewpigeon.apps.newgrounds.R;

public class DimensionTool {

    public static int GRID_CORNERS(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.dashboard_margin_corners);
    }


    /*   public static int dpToPx(int dp) {
           return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
       }
       public static int pxToDp(int px) {
           return (int) (px / Resources.getSystem().getDisplayMetrics().density);
       }
   */
    public static int screenWidth() {

        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int screenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int GRID_calcColumsFor(int columnWidth, Context context) {
        int parentDim = screenWidth() - GRID_CORNERS(context) * 2;
        int columnNum = parentDim / columnWidth;
        int parentDimMinSpacing = parentDim - 25 * columnNum;
        if ((parentDimMinSpacing / columnWidth < columnNum) && columnWidth < dp(198))
            columnNum--;
        return columnNum;
    }

    public static int GRID_calcColumsFor(int columnWidth, int width) {
        int parentDim = width;
        int columnNum = parentDim / columnWidth;
        int parentDimMinSpacing = parentDim - 25 * columnNum;
        if ((parentDimMinSpacing / columnWidth < columnNum) && columnWidth < dp(198))
            columnNum--;
        return columnNum;
    }


    public static int GRID_calcColumsFor(int columnWidth, int width,  Context context) {
        int parentDim = width - GRID_CORNERS(context) * 2;
        int columnNum = parentDim / columnWidth;
        int parentDimMinSpacing = parentDim - 25 * columnNum;
        if ((parentDimMinSpacing / columnWidth < columnNum) && columnWidth < dp(198))
            columnNum--;
        return columnNum;
    }


    public static int GRID_calcSpacing(int columnWidth, int columnNum, Context context) {
        return ((screenWidth() - GRID_CORNERS(context) * 2 - ((columnWidth) * columnNum)) / (columnNum + 1));
    }

    public static int GRID_calcSpacing(int columnWidth, int columnNum, int width) {
        return (width - ((columnWidth) * columnNum)) / (columnNum + 1);
    }

    public static int GRID_calcSpacing(int columnWidth, int columnNum, int unspecWidth, Context context) {
        return ((unspecWidth - GRID_CORNERS(context) * 2 - ((columnWidth) * columnNum)) / (columnNum + 1));
    }


    public static float sp(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static float dp(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }


}
