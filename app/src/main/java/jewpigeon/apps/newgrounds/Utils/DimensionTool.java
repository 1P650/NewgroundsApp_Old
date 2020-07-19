package jewpigeon.apps.newgrounds.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import jewpigeon.apps.newgrounds.R;

public class DimensionTool {


    private static int GRID_CORNERS(Context context){
        return context.getResources().getDimensionPixelSize(R.dimen.dashboard_margin_corners);
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

  public static int GRID_calcColumsFor(int columnWidth, Context context){
      int parentDim = screenWidth(context) - GRID_CORNERS(context) * 2;
      int columnNum =  parentDim / columnWidth;
      return columnNum;
  }


  public static int GRID_calcSpacing(int columnWidth, int columnNum,  Context context){
      return ((screenWidth(context) - GRID_CORNERS(context)*2 - ((columnWidth)*columnNum))/(columnNum+1));
  }


    public static float sp(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static float dp(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


}
