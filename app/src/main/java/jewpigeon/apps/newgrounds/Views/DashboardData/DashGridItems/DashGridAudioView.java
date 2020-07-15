package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;

public class DashGridAudioView extends View {

    private Drawable AudioIcon;
    private StaticLayout Title;
    private StaticLayout Author;
    private Drawable Background;

    private final int TitleColor = ContextCompat.getColor(getContext(), R.color.colorFeaturedItemTitleText);
    private final int AuthorColor = ContextCompat.getColor(getContext(), R.color.colorFeaturedItemAuthorText);

    private final int ITEM_WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int ITEM_HEIGHT = (int) getResources().getDimension(R.dimen.dashboard_iconSize);

    private static TextPaint DashTitlePainter;
    private static TextPaint DashAuthorPainter;
    private Drawable defaultIcon;


    public DashGridAudioView(Context context) {
        super(context);
    }

    public DashGridAudioView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DashGridAudioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DashGridAudioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
