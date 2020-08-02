package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.content.Context;
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

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.sp;

public class DashArtItemView extends View implements Target<Drawable> {



    private final int ITEM_HEIGHT = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_item_size_art);
    private final int ITEM_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_item_width_art);

    private final int BACKGROUND_COLOR = ContextCompat.getColor(getContext(), R.color.colorDashboardItemLabelBackground);

    private Drawable DashArtIcon;
    private  Drawable DashPegi;
    private StaticLayout DashTitle;
    private StaticLayout DashAuthor;


    private static TextPaint DashTitlePainter;
    private static TextPaint DashAuthorPainter;



    private Drawable defIcon;
    private GradientDrawable DashBackground;
    private GradientDrawable DashPegiBackground;
    private StateListDrawable DashForeground;

    {

        TextCache.INSTANCE().changeWidth(ITEM_WIDTH);

        DashBackground = new GradientDrawable();
        DashBackground.setBounds(0,0,ITEM_WIDTH, ITEM_HEIGHT);
        DashBackground.setColor(BACKGROUND_COLOR);
        DashBackground.setCornerRadius(16);

        DashPegiBackground = new GradientDrawable();
        DashPegiBackground.setShape(GradientDrawable.RECTANGLE);
        DashPegiBackground.setCornerRadii(new float[] { 0, 0, 0, 0, 8 ,8 , 0, 0});
        DashPegiBackground.setColor(BACKGROUND_COLOR);
        DashPegiBackground.setBounds(0,0, (int) (ITEM_WIDTH/(8)), (int) (ITEM_WIDTH/8));

        DashPegi = ContextCompat.getDrawable(getContext(), R.drawable.ng_pegi_everyone);
        DashPegi.setBounds(0,0, ITEM_WIDTH/11, ITEM_WIDTH/11);

        defIcon = ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined);
        defIcon.setBounds(0,0, ITEM_WIDTH*5/6, ITEM_WIDTH*5/6);

        DashForeground = new StateListDrawable();

        DashForeground.setEnterFadeDuration(150);
        DashForeground.setExitFadeDuration(300);

        DashForeground.addState(new int[]{android.R.attr.state_pressed}, ContextCompat.getDrawable(getContext(), R.color.colorGridRippleEffect));

        DashForeground.setCallback(this);
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
    protected void onDraw(Canvas canvas) {
        DashBackground.draw(canvas);
        canvas.save();
        canvas.translate((getWidth() - defIcon.getBounds().width())/2, 26);
        DashArtIcon.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate((getWidth() - DashTitle.getWidth())/2, 26 + DashArtIcon.getBounds().bottom);
        DashTitle.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate((getWidth() - DashAuthor.getWidth())/2, 26+ DashArtIcon.getBounds().bottom + DashTitle.getHeight());
        DashAuthor.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate((getWidth() - defIcon.getBounds().width())/2, 26);
        DashPegiBackground.draw(canvas);
        canvas.translate(4, 4);
        DashPegi.draw(canvas);
        canvas.restore();

        canvas.save();
        DashForeground.draw(canvas);
        canvas.restore();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ITEM_WIDTH, ITEM_HEIGHT);
    }



    public DashArtItemView(Context context) {
        super(context);
        establishState();
    }

    public DashArtItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishState();
    }

    public DashArtItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishState();
    }

    public DashArtItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishState();
    }

    private void establishState() {
        DashTitlePainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashTitlePainter.setColor(ContextCompat.getColor(getContext(), R.color.colorFeaturedItemTitleText));
        DashTitlePainter.setTypeface(Typeface.DEFAULT_BOLD);
        DashTitlePainter.setTextSize(sp(getContext(), 13));

        DashAuthorPainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashAuthorPainter.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        DashAuthorPainter.setTextSize(sp(getContext(), 10));

        this.setClickable(true);
    }

    public void setDashItem(DashArtItem item){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        Glide.
                with(getContext())
                .load(item.getArtIcon())
                .placeholder(defIcon)
                .fallback(defIcon)
                .apply(requestOptions)
                .into(this);
        DashArtIcon.setBounds(0,0, ITEM_WIDTH*6/7, ITEM_WIDTH*6/7);
        DashTitle = TextCache.INSTANCE().titleLayoutFor(item.getTitle());
        DashAuthor = TextCache.INSTANCE().authorLayoutFor(item.getAuthor());

        requestLayout();
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


    private static class TextCache{
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
