package jewpigeon.apps.newgrounds.Views;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.solver.ArrayRow;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Views.ProfileWidgetData.SlidingPanel;

public class ProfileWidget extends ViewGroup{

    private static int PROFILE_ICON_SIZE;
    private static int WIDGET_HEIGHT;
    private static int PANEL_MIN_WIDTH;
    private static int PANEL_MAX_WIDTH;
    private static int PANEL_STROKE;
    private static int PANEL_ARROW_SIZE;
    private GradientDrawable cornerBackground;
    private Drawable defIcon = ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined);

    {
        PROFILE_ICON_SIZE = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_ProfileIcon_size);
        WIDGET_HEIGHT = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_SelfHeight);
        PANEL_MIN_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelUnexpandedWidth);
        PANEL_MAX_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelExpandedMaxWidth);
        PANEL_STROKE = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelStrokeWidth);
        PANEL_ARROW_SIZE = getContext().getResources().getDimensionPixelSize(R.dimen.profileWidget_PanelArrowSize);

        cornerBackground = new GradientDrawable();
        cornerBackground.setShape(GradientDrawable.OVAL);
        cornerBackground.setColor(ContextCompat.getColor(getContext(),R.color.colorProfileWidgetBordeline));
    }

    private ImageView ProfileIcon;
    private SlidingPanel ProfileSlidingMenu;

    public ProfileWidget(Context context) {
        super(context);
        establishSelf(context);
    }

    public void setProfileIcon(Drawable icon){
        Glide
                .with(getContext())
                .load(icon)
                .circleCrop()
                .into(ProfileIcon);
    }

    public ProfileWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        establishSelf(context);
    }

    public ProfileWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishSelf(context);
    }

    public ProfileWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishSelf(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int selfWidth = 0;
        ProfileSlidingMenu.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(ProfileSlidingMenu.getLayoutParams().height, MeasureSpec.EXACTLY));

        int iconSpec = MeasureSpec.makeMeasureSpec(PROFILE_ICON_SIZE, MeasureSpec.EXACTLY);
        ProfileIcon.measure(iconSpec, iconSpec);


        selfWidth += ProfileIcon.getMeasuredWidth();
        selfWidth += (ProfileSlidingMenu.getLayoutParams().width - ProfileIcon.getMeasuredWidth() / 2);
        selfWidth = Math.max(selfWidth, ProfileIcon.getMeasuredWidth());


        setMeasuredDimension(selfWidth, WIDGET_HEIGHT);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        int slider_padding = width - ProfileIcon.getMeasuredWidth() / 2;
       ProfileSlidingMenu.layout(slider_padding - ProfileSlidingMenu.getLayoutParams().width,
               0, slider_padding,
               ProfileSlidingMenu.getLayoutParams().height);
        ProfileIcon.layout(width - ProfileIcon.getMeasuredWidth(),
                (height - ProfileIcon.getMeasuredHeight()) / 2,
                width,
                height - (height - ProfileIcon.getMeasuredHeight()) / 2);
    }


    private void establishSelf(final Context context) {

        ProfileSlidingMenu = new SlidingPanel(context);
        ProfileSlidingMenu.collapse();
        addView(ProfileSlidingMenu);
        ProfileIcon = new ImageView(context);
        ProfileIcon.setBackground(cornerBackground);
        ProfileIcon.setPadding(8,8,8,8);
        setProfileIcon(defIcon);
        addView(ProfileIcon);
        ProfileIcon.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(ProfileSlidingMenu.isExpanded) { ProfileSlidingMenu.collapse();}
                else{ProfileSlidingMenu.expand();}
                return true;
            };
        });

    }





    }

