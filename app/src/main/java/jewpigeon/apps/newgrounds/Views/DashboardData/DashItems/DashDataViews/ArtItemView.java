package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.LruCache;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.ArtItem;

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.sp;

public class ArtItemView extends View implements Target<Drawable> {

    private static TextPaint DashTitlePainter;
    private static TextPaint DashAuthorPainter;
    private final Context selfContext = getContext();
    private final Resources selfResources = selfContext.getResources();
    private final int ITEM_HEIGHT = selfResources.getDimensionPixelSize(R.dimen.dashboard_item_size_art);
    private final int ITEM_WIDTH = selfResources.getDimensionPixelSize(R.dimen.dashboard_item_width_art);
    private final int ICON_SIZE = ITEM_WIDTH * 15 / 16;
    private final int BACKGROUND_COLOR = ContextCompat.getColor(selfContext, R.color.colorDashboardItemLabelBackground);
    private final int FOREGROUND_COLOR = ContextCompat.getColor(selfContext, R.color.colorGridRippleEffect);
    private Drawable DashArtIcon;
    private Drawable DashPegi;
    private StaticLayout DashTitle;
    private StaticLayout DashAuthor;
    private Drawable defIcon;
    private GradientDrawable DashBackground;
    private GradientDrawable DashPegiBackground;
    private StateListDrawable DashForeground;
    private GradientDrawable DashForegroundShape;

    {

        TextCache.INSTANCE().changeWidth(ITEM_WIDTH);

        DashBackground = new GradientDrawable();
        DashBackground.setBounds(0, 0, ITEM_WIDTH, ITEM_HEIGHT);
        DashBackground.setColor(BACKGROUND_COLOR);
        DashBackground.setCornerRadius(DimensionTool.dp(8));

        DashPegiBackground = new GradientDrawable();
        DashPegiBackground.setShape(GradientDrawable.RECTANGLE);
        DashPegiBackground.setCornerRadii(new float[]{0, 0, 0, 0, 8, 8, 0, 0});
        DashPegiBackground.setColor(BACKGROUND_COLOR);
        DashPegiBackground.setBounds(0, 0, (ITEM_WIDTH / (8)), (ITEM_WIDTH / 8));


        DashPegi = ContextCompat.getDrawable(selfContext, R.drawable.ng_pegi_everyone);
        DashPegi.setBounds(0, 0, ITEM_WIDTH / 11, ITEM_WIDTH / 11);

        defIcon = ContextCompat.getDrawable(selfContext, R.drawable.ng_icon_undefined);
        defIcon.setBounds(0, 0, ICON_SIZE, ICON_SIZE);


        DashForegroundShape = new GradientDrawable();
        DashForegroundShape.setColor(FOREGROUND_COLOR);
        DashForegroundShape.setBounds(0, 0, ITEM_WIDTH, ITEM_HEIGHT);
        DashForegroundShape.setCornerRadius(DimensionTool.dp(8));

        DashForeground = new StateListDrawable();

        DashForeground.setEnterFadeDuration(50);
        DashForeground.setExitFadeDuration(300);

        DashForeground.addState(new int[]{android.R.attr.state_pressed}, DashForegroundShape);

        DashForeground.setCallback(this);
    }


    public ArtItemView(Context context) {
        super(context);
        establishState();
    }

    public ArtItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishState();
    }

    public ArtItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishState();
    }


    public ArtItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishState();
    }


    public void setDashItem(ArtItem item) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        Glide.
                with(selfContext)
                .load(item.getArtIcon())
                .placeholder(new ColorDrawable(Color.BLACK))
                .fallback(defIcon)
                .apply(requestOptions)
                .into(this);
        DashArtIcon.setBounds(defIcon.copyBounds());
        DashTitle = TextCache.INSTANCE().titleLayoutFor(item.getTitle());
        DashAuthor = TextCache.INSTANCE().authorLayoutFor(item.getAuthor());

        requestLayout();
        invalidate();


    }

    private void establishState() {
        DashTitlePainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashTitlePainter.setColor(ContextCompat.getColor(selfContext, R.color.colorFeaturedItemTitleText));
        DashTitlePainter.setTypeface(Typeface.DEFAULT_BOLD);
        DashTitlePainter.setTextSize(sp(13));

        DashAuthorPainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashAuthorPainter.setColor(ContextCompat.getColor(selfContext, R.color.colorAccent));
        DashAuthorPainter.setTextSize(sp(10));

        this.setClickable(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ITEM_WIDTH, ITEM_HEIGHT);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int ICON_PADDING = (ITEM_WIDTH - ICON_SIZE) / 2;
        int ICON_IN_CENTER = (getWidth() - DashArtIcon.getBounds().width()) /2;
        int TITLE_IN_CENTER = (getWidth() - DashTitle.getWidth()) /2;
        int AUTHOR_IN_CENTER = (getWidth() - DashAuthor.getWidth()) /2;
        int ICON_HEIGHT = DashArtIcon.getBounds().bottom;
        DashBackground.draw(canvas);
        canvas.save();
        canvas.translate(ICON_IN_CENTER, ICON_PADDING);
        DashArtIcon.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(TITLE_IN_CENTER, ICON_PADDING + ICON_HEIGHT);
        DashTitle.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(AUTHOR_IN_CENTER, ICON_PADDING + ICON_HEIGHT + DashTitle.getHeight());
        DashAuthor.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(ICON_IN_CENTER, ICON_PADDING);
        DashPegiBackground.draw(canvas);
        canvas.translate(4, 4);
        DashPegi.draw(canvas);
        canvas.restore();

        canvas.save();
        DashForeground.draw(canvas);
        canvas.restore();


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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        DashForeground.setBounds(0, 0, w, h);
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
            DashArtIcon = placeholder;

        } catch (NullPointerException e) {
            DashArtIcon = new ColorDrawable(Color.BLACK);
        } finally {
            DashArtIcon.setBounds(defIcon.copyBounds());
        }

        invalidate();
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        try {
            DashArtIcon = errorDrawable;

        } catch (NullPointerException e) {
            DashArtIcon = new ColorDrawable(Color.BLACK);
        } finally {
            DashArtIcon.setBounds(defIcon.copyBounds());
        }

        invalidate();
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        try {
            DashArtIcon = resource;

        } catch (NullPointerException e) {
            DashArtIcon = new ColorDrawable(Color.BLACK);
        } finally {
            DashArtIcon.setBounds(defIcon.copyBounds());
        }
        invalidate();
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {

    }

    @Override
    public void getSize(@NonNull SizeReadyCallback cb) {
        cb.onSizeReady(defIcon.getBounds().right, defIcon.getBounds().bottom);
    }

    @Override
    public void removeCallback(@NonNull SizeReadyCallback cb) {

    }

    @Nullable
    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void setRequest(@Nullable Request request) {

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

        public static TextCache INSTANCE() {
            if (instance == null) {
                instance = new TextCache();
                return instance;
            }
            return instance;

        }

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
