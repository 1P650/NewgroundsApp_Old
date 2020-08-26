package jewpigeon.apps.newgrounds.Views.ProfileWidgetData;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;

public class SlidingPanel extends LinearLayout implements View.OnClickListener {
    private GradientDrawable PanelBackground;
    private GradientDrawable MenuBackground;
    private ImageButton stateControlArrow;
    public boolean isExpanded = false;
    private ImageButton[] panel;
    private LinearLayout.LayoutParams panelParams;
    private SliderItemClickListener listener;
    private ColorStateList PANEL_MENU_STATE_LIST = new ColorStateList(new int[][]{

            new int[]{android.R.attr.state_pressed},
            new int[]{android.R.attr.state_enabled},
            new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
            new int[]{-android.R.attr.state_enabled},
            new int[]{}
    },
            new int[]{
                    ContextCompat.getColor(getContext(), R.color.colorProfileWidgetPanelButtonPressed),
                    ContextCompat.getColor(getContext(), R.color.colorProfileWidgetPanelButton),
                    ContextCompat.getColor(getContext(), R.color.colorProfileWidgetPanelButtonPressed),
                    ContextCompat.getColor(getContext(), R.color.colorProfileWidgetPanelButton),
                    ContextCompat.getColor(getContext(), R.color.colorProfileWidgetPanelButton)
            });

    private final int PROFILE_ICON_SIZE = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_ProfileIcon_size);
    private final int PANEL_MIN_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelUnexpandedWidth);
    private final int PANEL_MAX_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelExpandedMaxWidth);
    private final int PANEL_STROKE = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelStrokeWidth);
    private final int PANEL_ARROW_SIZE = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelArrowSize);

    {
        PanelBackground = new GradientDrawable();
        PanelBackground.setColor(ContextCompat.getColor(getContext(), R.color.colorProfileWidgetPanelBackground));
        PanelBackground.setCornerRadii(new float[]{64, 64, 0, 0, 0, 0, 64, 64});
        //PanelBackground.setStroke(PANEL_STROKE, ContextCompat.getColor(getContext(), R.color.colorProfileWidgetBordeline));

        MenuBackground = new GradientDrawable();
        MenuBackground.setColor(ContextCompat.getColor(getContext(), R.color.colorProfileWidgetMenuBackground));
        MenuBackground.setCornerRadii(new float[]{64, 64, 0, 0, 0, 0, 64, 64});

        stateControlArrow = new ImageButton(getContext());
        stateControlArrow.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ng_profile_arrow));
        stateControlArrow.setScaleType(ImageView.ScaleType.CENTER);
        stateControlArrow.setAdjustViewBounds(true);
        stateControlArrow.setPadding(16, 16, 16, 16);
        stateControlArrow.setImageTintList(PANEL_MENU_STATE_LIST);
        stateControlArrow.setBackgroundColor(Color.TRANSPARENT);
        stateControlArrow.setTag("ARROW");
        LinearLayout.LayoutParams stateArrowParams = new LayoutParams(PANEL_ARROW_SIZE, PANEL_ARROW_SIZE);
        stateArrowParams.topMargin = (PROFILE_ICON_SIZE - PANEL_ARROW_SIZE) / 2;
        stateControlArrow.setLayoutParams(stateArrowParams);

    }

    public SlidingPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishPanel(context);
    }

    public SlidingPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishPanel(context);
    }

    public SlidingPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishPanel(context);
    }

    public SlidingPanel(Context context) {
        super(context);
        establishPanel(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("RestrictedApi")
    private void establishPanel(Context context) {
        addView(stateControlArrow);
        panelParams = new LinearLayout.LayoutParams(PROFILE_ICON_SIZE, PROFILE_ICON_SIZE);
        this.setOrientation(HORIZONTAL);
        this.setMinimumWidth(PANEL_MIN_WIDTH);
        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, PROFILE_ICON_SIZE));
        this.setPadding(8, 0, 0, 8);

        MenuInflater v = new MenuInflater(getContext());
        Menu buttonsMenu = new MenuBuilder(getContext());
        v.inflate(R.menu.profile_widget_buttons, buttonsMenu);
        setMenu(buttonsMenu);
        this.setVisibility(GONE);

        stateControlArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                collapse();
            }
        });
    }


    public void expand() {
        isExpanded = true;
        expandSelf(this, 300);
    }

    private void expandSelf(final SlidingPanel widget, int duration) {
        int width = this.getWidth();
        widget.setVisibility(VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(width, PANEL_MAX_WIDTH);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                widget.getLayoutParams().width = (int) animation.getAnimatedValue();
                widget.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    private void collapseSelf(final SlidingPanel widget, int duration) {
        int prevHeight = widget.getWidth();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, PANEL_MIN_WIDTH);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                widget.getLayoutParams().width = (int) animation.getAnimatedValue();
                widget.requestLayout();
                if ((int) animation.getAnimatedValue() == PANEL_MIN_WIDTH) {
                    widget.setVisibility(GONE);
                }
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    public void collapse() {
        isExpanded = false;
        collapseSelf(this, 200);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        PanelBackground.setBounds(0, 0, getWidth(), getHeight());
        MenuBackground.setBounds(PANEL_ARROW_SIZE, PANEL_STROKE, getWidth(), getHeight() - PANEL_STROKE);
        PanelBackground.draw(canvas);
        MenuBackground.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public void setMenu(Menu menu) {
        panel = new ImageButton[menu.size()];
        for (int i = menu.size() - 1; i >= 0; i--) {
            panel[i] = new ImageButton(getContext());
            panel[i].setImageDrawable(menu.getItem(i).getIcon());
            panel[i].setScaleType(ImageView.ScaleType.FIT_CENTER);
            panel[i].setAdjustViewBounds(true);
            panel[i].setPadding(24, 24, 24, 24);
            panel[i].setBackgroundColor(Color.TRANSPARENT);
            panel[i].setId(menu.getItem(i).getItemId());
            panel[i].setLayoutParams(panelParams);
            panel[i].setImageTintList(PANEL_MENU_STATE_LIST);
            panel[i].setClickable(true);
            panel[i].setOnClickListener(this);
            this.addView(panel[i]);
        }

    }
    public void setMenuClickListener(SliderItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onItemClick(view);
    }
}