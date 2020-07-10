package jewpigeon.apps.newgrounds.Utils;

import android.content.res.Resources;

public class MeasureConverter {

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
