package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.LruCache;
import android.util.TypedValue;
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


public class DashGridViewSmall extends View implements Target<Drawable> {

    private final int ITEM_SIZE = (int) getResources().getDimension(R.dimen.dashboard_item_size_small);
    private final int AUTHOR_COLOR = ContextCompat.getColor(getContext(), R.color.colorFeaturedItemSmallAuthorText);
    private final int ITEM_LABEL_COLOR = ContextCompat.getColor(getContext(), R.color.colorDashboardItemLabelBackground);


    private  StaticLayout DashAuthor;
    private  Drawable DashIcon;
    private  Drawable DashTextBackground;
    private  GradientDrawable DashPegiBackground;
    private  Drawable DashPegi;



    private static TextPaint DashAuthorPainter;
    private Drawable defIcon;






    {
        TextCache.INSTANCE.changeWidth(ITEM_SIZE);
        defIcon = ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined);
        defIcon.setBounds(0,0,ITEM_SIZE,ITEM_SIZE);

        DashPegi = ContextCompat.getDrawable(getContext(), R.drawable.ng_pegi_everyone);
        DashPegi.setBounds(0,0, ITEM_SIZE/6, ITEM_SIZE/6);

        DashTextBackground = new ColorDrawable(ITEM_LABEL_COLOR);
        DashTextBackground.setBounds(0,0,ITEM_SIZE,ITEM_SIZE/4);

        DashPegiBackground = new GradientDrawable();
        DashPegiBackground.setShape(GradientDrawable.RECTANGLE);
        DashPegiBackground.setCornerRadii(new float[] { 0, 0, 0, 0, 16 ,8 , 0, 0});
        DashPegiBackground.setColor(ITEM_LABEL_COLOR);
        DashPegiBackground.setBounds(0,0, (int) (ITEM_SIZE/(4.5)), (int) (ITEM_SIZE/4.5));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public DashGridViewSmall(Context context) {
        super(context);
        establishState();
    }

    public DashGridViewSmall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishState();
    }

    public DashGridViewSmall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishState();
    }

    public DashGridViewSmall(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishState();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ITEM_SIZE,ITEM_SIZE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DashIcon.draw(canvas);
        canvas.save();
        canvas.translate(0, (int)(ITEM_SIZE*0.75f)+1);
        DashTextBackground.draw(canvas);
        canvas.save();
        DashAuthor.draw(canvas);
        canvas.translate(0,(int)(-ITEM_SIZE*0.75f)-1);
        DashPegiBackground.draw(canvas);
        canvas.translate(2,4);
        DashPegi.draw(canvas);
        canvas.save();
        canvas.restore();
    }






    public void setDashItem(DashGridItemSmall item){
        Glide.
                with(getContext())
                .load(item.getImage())
                .placeholder(defIcon)
                .fallback(defIcon)
                .into(this);
        DashIcon.setBounds(0, 0, ITEM_SIZE, ITEM_SIZE);


        DashAuthor = TextCache.INSTANCE.authorLayoutFor(item.getAuthor());

        requestLayout();
        invalidate();
    }


    private void establishState(){
        DashAuthorPainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashAuthorPainter.setColor(AUTHOR_COLOR);
        DashAuthorPainter.setTextSize(sp(15));


        this.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.grid_item_ripple));
        this.setClickable(true);

    }
    private float sp(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private float dp(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        try {
            DashIcon = placeholder;

        }catch (NullPointerException e){
            DashIcon = new ColorDrawable(Color.BLACK);
        }finally {
            DashIcon.setBounds(0,0,ITEM_SIZE,ITEM_SIZE);
        }

        invalidate();

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        try {
            DashIcon = errorDrawable;

        }catch (NullPointerException e){
            DashIcon = new ColorDrawable(Color.BLACK);
        }
        finally {
            DashIcon.setBounds(0,0,ITEM_SIZE,ITEM_SIZE);
        }

        invalidate();
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        try {
            DashIcon = resource;

        }catch (NullPointerException e){
            DashIcon = new ColorDrawable(Color.BLACK);
        }
        finally {
            DashIcon.setBounds(0,0, ITEM_SIZE, ITEM_SIZE);
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

    private enum TextCache {
        INSTANCE;

        private int width;
        private final LruCache<CharSequence, StaticLayout> authorCache = new LruCache<CharSequence, StaticLayout>(100) {
            @Override
            protected StaticLayout create(CharSequence key) {
                CharSequence authorEllipisized = TextUtils.ellipsize(key, DashAuthorPainter, width, TextUtils.TruncateAt.END);
                return StaticLayout.Builder.obtain(authorEllipisized, 0, authorEllipisized.length(), DashAuthorPainter, width)
                        .setAlignment(Layout.Alignment.ALIGN_CENTER)
                        .build();
            }
        };

        public void changeWidth(int newWidth) {
            if (width != newWidth) {
                width = newWidth;
                authorCache.evictAll();
            }
        }

        public StaticLayout authorLayoutFor(CharSequence text) {
            return authorCache.get(text);
        }
    }

}

