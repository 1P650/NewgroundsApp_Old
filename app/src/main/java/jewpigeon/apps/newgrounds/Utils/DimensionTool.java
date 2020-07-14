package jewpigeon.apps.newgrounds.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.PropertyResourceBundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;

public class DimensionTool {
    public static int GRID_PADDING(Context ctx){
        return ctx.getResources().getDimensionPixelSize(R.dimen.dashboard_margin_corners);
    }

    public static int GRID_ITEM_SIZE(Context ctx){
        return ctx.getResources().getDimensionPixelSize(R.dimen.dashboard_item_size);
    }

    public static int GRID_ITEM_SIZE_SMALL(Context ctx){
        return ctx.getResources().getDimensionPixelSize(R.dimen.dashboard_item_size_small);

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public  static int screenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int screenHeight(Context context){
        return  context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp); // +0.5 for correct rounding to int.
            return noOfColumns;


    }


}
