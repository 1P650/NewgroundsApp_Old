package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.LruCache;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.GridItem;

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.sp;


public class GridItemView extends View implements Target<Drawable> {

    private StaticLayout DashTitle;
    private StaticLayout DashAuthor;
    private Drawable DashIcon;
    private Drawable DashTextBackground;


    private Context selfContext = getContext();

    private final int ITEM_SIZE = (int) getResources().getDimension(R.dimen.dashboard_item_size);
    private final int LABEL_HEIGHT = ITEM_SIZE / 3;
    private final int ICON_HEIGHT = ITEM_SIZE * 2 / 3;


    private final int TitleColor = ContextCompat.getColor(selfContext, R.color.colorFeaturedItemTitleText);
    private final int AuthorColor = ContextCompat.getColor(selfContext, R.color.colorItemAuthorText);
    private final int LabelBackgroundColor = ContextCompat.getColor(selfContext, R.color.colorDashboardItemLabelBackground);

    private static TextPaint DashTitlePainter;
    private static TextPaint DashAuthorPainter;

    private Drawable defIcon;
    private StateListDrawable DashForeground;

    {
        TextCache.INSTANCE().changeWidth(ITEM_SIZE);

        defIcon = ContextCompat.getDrawable(selfContext, R.drawable.ng_icon_undefined_cut);
        defIcon.setBounds(0, 0, ITEM_SIZE, ICON_HEIGHT);

        DashTextBackground = new ColorDrawable(LabelBackgroundColor);
        DashTextBackground.setBounds(0, 0, ITEM_SIZE, LABEL_HEIGHT);

        DashForeground = new StateListDrawable();

        DashForeground.setEnterFadeDuration(150);
        DashForeground.setExitFadeDuration(300);

        DashForeground.addState(new int[]{android.R.attr.state_pressed}, ContextCompat.getDrawable(selfContext, R.color.colorGridRippleEffect));

        DashForeground.setCallback(this);

    }


    public GridItemView(Context context) {
        super(context);
        establishState();
    }

    public GridItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishState();
    }

    public GridItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishState();
    }

    public GridItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishState();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ITEM_SIZE, ITEM_SIZE);
    }


    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        return super.verifyDrawable(who) || (who == DashForeground);
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        DashForeground.jumpToCurrentState();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DashIcon.draw(canvas);
        canvas.save();
        canvas.translate(0, ICON_HEIGHT);
        DashTextBackground.draw(canvas);
        canvas.save();
        DashTitle.draw(canvas);
        canvas.translate(0, DashTitle.getHeight());
        DashAuthor.draw(canvas);
        canvas.translate(0, -(DashTitle.getHeight() + ICON_HEIGHT));
        canvas.save();
        DashForeground.draw(canvas);
        canvas.restore();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        DashForeground.setBounds(0, 0, w, h);
    }

    public void setDashItem(GridItem item) {
        Glide.
                with(getContext())
                .load(item.getImage())
                .placeholder(new ColorDrawable(Color.BLACK))
                .fallback(defIcon)
                .into(this);


        DashTitle = TextCache.INSTANCE().titleLayoutFor(item.getTitle());
        DashAuthor = TextCache.INSTANCE().authorLayoutFor(item.getAuthor());


        requestLayout();
        invalidate();
    }


    private void establishState() {
        DashTitlePainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashTitlePainter.setColor(TitleColor);
        DashTitlePainter.setTextSize(sp(13));

        DashAuthorPainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashAuthorPainter.setColor(AuthorColor);
        DashAuthorPainter.setTextSize(sp(10));

        this.setClickable(true);

    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        DashForeground.setState(getDrawableState());
        invalidate();
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        try {
            DashIcon = placeholder;

        } catch (NullPointerException e) {
            DashIcon = new ColorDrawable(Color.BLACK);
        } finally {
            DashIcon.setBounds(0, 0, ITEM_SIZE, ICON_HEIGHT);
        }

        invalidate();

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        try {
            DashIcon = errorDrawable;

        } catch (NullPointerException e) {
            DashIcon = new ColorDrawable(Color.BLACK);
        } finally {
            DashIcon.setBounds(0, 0, ITEM_SIZE, ICON_HEIGHT);
        }

        invalidate();
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        try {
            DashIcon = resource;

        } catch (NullPointerException e) {
            DashIcon = new ColorDrawable(Color.BLACK);
        } finally {
            DashIcon.setBounds(0, 0, ITEM_SIZE, ICON_HEIGHT);
        }
        invalidate();
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
    }

    @Override
    public void getSize(@NonNull SizeReadyCallback cb) {
        cb.onSizeReady(ITEM_SIZE, ITEM_SIZE);
    }

    @Override
    public void removeCallback(@NonNull SizeReadyCallback cb) {

    }

    @Override
    public void setRequest(@Nullable Request request) {
    }
    @Nullable
    @Override
    public Request getRequest() {
        return null;
    }
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    private static class TextCache {
        private static TextCache instance;

        public static TextCache INSTANCE() {
            if (instance == null) {
                instance = new TextCache();
                return instance;
            }
            return instance;

        }

        private int width;
        private final LruCache<CharSequence, StaticLayout> titleCache = new LruCache<CharSequence, StaticLayout>(100) {
            @Override
            protected StaticLayout create(CharSequence key) {
                CharSequence titleEllipisized = TextUtils.ellipsize(key, DashTitlePainter, width, TextUtils.TruncateAt.END);
                return StaticLayout.Builder.obtain(titleEllipisized, 0, titleEllipisized.length(), DashTitlePainter, width)
                        .setAlignment(Layout.Alignment.ALIGN_CENTER)
                        .setMaxLines(1)
                        .build();

            }

        };
        private final LruCache<CharSequence, StaticLayout> authorCache = new LruCache<CharSequence, StaticLayout>(100) {
            @Override
            protected StaticLayout create(CharSequence key) {
                CharSequence authorEllipsized = TextUtils.ellipsize(key, DashTitlePainter, width * 1.2f, TextUtils.TruncateAt.END);
                return StaticLayout.Builder.obtain(authorEllipsized, 0, authorEllipsized.length(), DashAuthorPainter, width)
                        .setAlignment(Layout.Alignment.ALIGN_CENTER)
                        .setMaxLines(1)
                        .build();
            }
        };

        public void changeWidth(int newWidth) {
            if (width != newWidth) {
                width = newWidth;
                titleCache.evictAll();
                authorCache.evictAll();
            }
        }

        public StaticLayout titleLayoutFor(CharSequence text) {
            return titleCache.get(text);
        }

        public StaticLayout authorLayoutFor(CharSequence text) {
            return authorCache.get(text);
        }
    }

}
