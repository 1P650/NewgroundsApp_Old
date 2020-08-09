package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems.NewsItem;


public class NewsItemView extends ViewGroup {

    private final int ITEM_HEIGHT = (int) DimensionTool.dp(84);
    private final int ICON_SIZE = ITEM_HEIGHT *8/9;
    private Drawable DashBackground;
    private final int TitleColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
    private final int FromColor = ContextCompat.getColor(getContext(), R.color.colorFeaturedItemAuthorText);
    private final int DashBackgroundEnabledColor = ContextCompat.getColor(getContext(), R.color.colorFeaturedAudioItemBackground);


    private ImageView userIcon;
    private TextView fromUser;
    private TextView newsTitle;

    private boolean isBackgroundEnabled = false;



    private Drawable defIcon;
    private StateListDrawable DashForeground;

    {
        defIcon = ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined_circle);
        DashBackground = new ColorDrawable(DashBackgroundEnabledColor);

        DashForeground = new StateListDrawable();

        DashForeground.setEnterFadeDuration(150);
        DashForeground.setExitFadeDuration(300);

        DashForeground.addState(new int[]{android.R.attr.state_pressed}, ContextCompat.getDrawable(getContext(), R.color.colorGridRippleEffect));

        DashForeground.setCallback(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        DashForeground.setBounds(0, 0, w, h);
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

    public NewsItemView(Context context) {
        super(context);
        establishSelf(context);
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        establishSelf(context);
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishSelf(context);
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishSelf(context);
    }


    private void establishSelf(Context context){
        userIcon = new ImageView(context);
        fromUser = new TextView(context);
        newsTitle = new TextView(context);

        userIcon.setImageDrawable(defIcon);

        newsTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        fromUser.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        fromUser.setTextColor(Color.WHITE);

        newsTitle.setTextColor(TitleColor);
        Spannable fromUserSpannable = new SpannableString("from " + "DEFAULT");
        fromUserSpannable.setSpan(new ForegroundColorSpan(FromColor),0, "from".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        fromUser.setText(fromUserSpannable);

        newsTitle.setText("DEFAULT");



        addView(userIcon);
        addView(fromUser);
        addView(newsTitle);

        this.setClickable(true);



    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        DashForeground.setState(getDrawableState());
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        userIcon.measure( MeasureSpec.makeMeasureSpec(ICON_SIZE, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(ICON_SIZE, MeasureSpec.EXACTLY));
        fromUser.measure( MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.UNSPECIFIED));
        newsTitle.measure( MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.UNSPECIFIED));
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(widthMeasureSpec,MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(ITEM_HEIGHT, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;

        userIcon.layout(ICON_SIZE/8,(ITEM_HEIGHT - ICON_SIZE)/2, ICON_SIZE*9/8, ITEM_HEIGHT - (ITEM_HEIGHT - ICON_SIZE)/2);
        fromUser.layout(
                ICON_SIZE+ICON_SIZE/4,
                ICON_SIZE/2-fromUser.getMeasuredHeight()/2-10,
                ICON_SIZE + fromUser.getMeasuredWidth()+ICON_SIZE/4,
                ICON_SIZE/2-10+fromUser.getMeasuredHeight()/2);
        newsTitle.layout(ICON_SIZE+ICON_SIZE/4,
                ICON_SIZE/2+newsTitle.getMeasuredHeight()/2-5,
                ICON_SIZE + newsTitle.getMeasuredWidth()+ICON_SIZE/4,
                ICON_SIZE/2 -5 + newsTitle.getMeasuredHeight()*3/2);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (isBackgroundEnabled) {
            canvas.save();
            DashBackground.setBounds(0, 0, getWidth(), ITEM_HEIGHT);
            DashBackground.draw(canvas);
            canvas.restore();
        }

        canvas.save();
        DashForeground.draw(canvas);
        canvas.restore();

        super.dispatchDraw(canvas);

    }

    public void setDashItem(NewsItem item) {
        Glide.with(getContext())
                .load(item.getUserIcon())
                .placeholder(new ColorDrawable(Color.BLACK))
                .fallback(defIcon)
                .circleCrop()
                .into(userIcon);

        Spannable fromUserSpannable = new SpannableString("from" + " " + item.getUsername());
        fromUserSpannable.setSpan(new ForegroundColorSpan(FromColor),0, "from".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        fromUser.setText(fromUserSpannable);
        newsTitle.setTextColor(TitleColor);
        newsTitle.setText(item.getTitle());

        requestLayout();
        invalidate();

    }

    public void enableBackground() {
        this.isBackgroundEnabled = true;
    }
}
