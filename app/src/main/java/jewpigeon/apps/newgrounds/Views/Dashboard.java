package jewpigeon.apps.newgrounds.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import jewpigeon.apps.newgrounds.R;

import static jewpigeon.apps.newgrounds.Utils.DimensionTool.dp;

public class Dashboard extends LinearLayout {

    @DimenRes
    private final int LABEL_HEIGHT = (int) (getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_labelSize));
    @DimenRes
    private final int STROKE_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_stroke_width);
    @DimenRes
    private final int ICON_SIZE = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_iconSize);
    @DimenRes
    private final int TEXT_SIZE = (int) getContext().getResources().getDimension(R.dimen.dashboard_textSize);
    private final int ICON_PADDING = (int) dp(2);
    @DimenRes
    private final int CORNER_RADIUS = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_corner_radius);


    private Context selfContext = getContext();


    private boolean IS_LABEL_VISIBLE = true;


    private ConstraintLayout DashboardLabel;
    private TextView LabelHeader;
    private ImageView LabelIcon;
    private LinearLayout LabelButtons;


    private Set<Integer> childrenWithSeparators = new HashSet<>();
    private Drawable LabelBackground;
    private GradientDrawable MainBackground;

    @ColorInt
    private int COLOR_BUTTON_DEFAULT = ContextCompat.getColor(selfContext, R.color.colorDashboardIconBackground);
    @ColorInt
    private int COLOR_BUTTON_PRESSED = ContextCompat.getColor(selfContext, R.color.colorAccent);

    private DashboardHelper helper;
    private DashboardPainter painter;

    {
        MainBackground = new GradientDrawable();
        MainBackground.setColor(ContextCompat.getColor(selfContext, R.color.colorDashboardBackground));
        MainBackground.setShape(GradientDrawable.RECTANGLE);


        LabelBackground = ContextCompat.getDrawable(selfContext, R.drawable.dashboard_label_gradient);
    }

    public Dashboard(Context context) {
        super(context);
        establishSelf(context, null);
        establishPanel(context, null);
    }

    public Dashboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        establishSelf(context, attrs);
        establishPanel(context, attrs);
    }

    public Dashboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishSelf(context, attrs);
        establishPanel(context, attrs);
    }

    public Dashboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishSelf(context, attrs);
        establishPanel(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        painter.drawShadow(canvas);
        if (IS_LABEL_VISIBLE) {
            LabelBackground.setBounds(0, 0, getWidth(), LABEL_HEIGHT);

            MainBackground.setCornerRadii(new float[]{
                    0, 0,
                    0, 0,
                    CORNER_RADIUS, CORNER_RADIUS,
                    CORNER_RADIUS, CORNER_RADIUS
            });

            MainBackground.setBounds(0, 0, getWidth(), getHeight() - LABEL_HEIGHT);


            LabelBackground.draw(canvas);
            canvas.translate(0, LABEL_HEIGHT);
            MainBackground.draw(canvas);
            canvas.translate(0, -LABEL_HEIGHT);
        } else {
            MainBackground.setCornerRadii(new float[]{
                    CORNER_RADIUS, CORNER_RADIUS,
                    CORNER_RADIUS, CORNER_RADIUS,
                    CORNER_RADIUS, CORNER_RADIUS,
                    CORNER_RADIUS, CORNER_RADIUS});
            MainBackground.setBounds(0, 0, getWidth(), getHeight());
            MainBackground.draw(canvas);
        }


        super.dispatchDraw(canvas);

        if (IS_LABEL_VISIBLE) {
            painter.drawInnerBorder(canvas);
            painter.drawOuterBorder(canvas);
        } else {
            painter.drawInnerBorder(canvas, true);
            painter.drawOuterBorder(canvas, true);
        }

        if (!childrenWithSeparators.isEmpty()) {

            for (int child_index : childrenWithSeparators
            ) {
                View child = getChildAt(child_index);
                if (child_index != getChildCount() - 1 && getChildAt(getChildCount() - 1).getVisibility() == VISIBLE) {
                    painter.drawSeparator(canvas, child.getBottom() +
                            ((LayoutParams) child.getLayoutParams()).bottomMargin);
                }

            }
        }
    }

    private void establishSelf(Context context, @Nullable AttributeSet attributes) {
        setOrientation(VERTICAL);
        setMinimumHeight(LABEL_HEIGHT * 4 / 3);

        helper = new DashboardHelper();
        painter = new DashboardPainter();
    }

    private void establishPanel(Context context, @Nullable AttributeSet set) {

        DashboardLabel = new ConstraintLayout(context);
        DashboardLabel.setId(View.generateViewId());
        DashboardLabel.setVisibility(VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LABEL_HEIGHT + STROKE_WIDTH / 2);
        params.gravity = Gravity.TOP;
        DashboardLabel.setLayoutParams(params);


        LabelIcon = new ImageView(context);
        LabelIcon.setLayoutParams(new ViewGroup.LayoutParams(ICON_SIZE, ICON_SIZE));
        LabelIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ng_default_icon));
        LabelIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.dashboard_icon_shape));
        LabelIcon.setId(View.generateViewId());
        LabelIcon.setPadding(ICON_PADDING, ICON_PADDING, ICON_PADDING, ICON_PADDING);
        LabelIcon.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(selfContext, R.color.colorAccent)));
        LabelIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);


        LabelHeader = new TextView(context);
        LabelHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE);
        LabelHeader.setTextColor(Color.WHITE);
        LabelHeader.setTypeface(context.getResources().getFont(R.font.pakeham));
        LabelHeader.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(30)});
        LabelHeader.setEllipsize(TextUtils.TruncateAt.END);
        LabelHeader.setId(View.generateViewId());
        LabelHeader.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LabelHeader.setText(getResources().getString(R.string.LabelNameDefault));

        LabelButtons = new LinearLayout(context);
        LabelButtons.setClickable(true);
        LabelButtons.setPadding(8, 0, 0, 0);
        LabelButtons.setGravity(Gravity.END);
        LabelButtons.setOrientation(HORIZONTAL);
        LabelButtons.setId(View.generateViewId());

        DashboardLabel.addView(LabelIcon);
        DashboardLabel.addView(LabelHeader);
        DashboardLabel.addView(LabelButtons);


        ConstraintSet labelConstraints = new ConstraintSet();

        labelConstraints.clone(DashboardLabel);

        labelConstraints.connect(LabelIcon.getId(), ConstraintSet.BOTTOM, DashboardLabel.getId(), ConstraintSet.BOTTOM, (int) dp(8));
        labelConstraints.connect(LabelIcon.getId(), ConstraintSet.TOP, DashboardLabel.getId(), ConstraintSet.TOP, (int) dp(8));
        labelConstraints.connect(LabelIcon.getId(), ConstraintSet.START, DashboardLabel.getId(), ConstraintSet.START, (int) dp(8));

        labelConstraints.connect(LabelHeader.getId(), ConstraintSet.BOTTOM, LabelIcon.getId(), ConstraintSet.BOTTOM, (int) dp(8));
        labelConstraints.connect(LabelHeader.getId(), ConstraintSet.START, LabelIcon.getId(), ConstraintSet.END, (int) dp(4));
        labelConstraints.connect(LabelHeader.getId(), ConstraintSet.TOP, LabelIcon.getId(), ConstraintSet.TOP, (int) dp(8));

        labelConstraints.connect(LabelButtons.getId(), ConstraintSet.BOTTOM, DashboardLabel.getId(), ConstraintSet.BOTTOM, 4);
        labelConstraints.connect(LabelButtons.getId(), ConstraintSet.END, DashboardLabel.getId(), ConstraintSet.END, (int) dp(8));
        labelConstraints.connect(LabelButtons.getId(), ConstraintSet.TOP, DashboardLabel.getId(), ConstraintSet.TOP, 4);


        labelConstraints.applyTo(DashboardLabel);

        if (set != null) {
            establishPanelData(set, context);
        }


        this.addView(DashboardLabel);
    }

    public void establishPanelData(AttributeSet attributeSet, Context context) {
        if (attributeSet == null) return;
        TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Dashboard, 0, 0);
        try {

            if (attributes.hasValue(R.styleable.Dashboard_labelVisible)) {
                boolean labelVisible = attributes.getBoolean(R.styleable.Dashboard_labelVisible, true);
                if (!labelVisible) removeLabel();
            }

            if (attributes.hasValue(R.styleable.Dashboard_labelText)) {
                String labelName = attributes.getString(R.styleable.Dashboard_labelText);
                LabelHeader.setText(labelName == null ? getResources().getString(R.string.LabelNameDefault) : labelName);
            }

            if (attributes.hasValue(R.styleable.Dashboard_labelIcon)) {
                Drawable labelIcon = attributes.getDrawable(R.styleable.Dashboard_labelIcon);
                if (labelIcon != null) LabelIcon.setImageDrawable(labelIcon);
            }


            int buttonsMenuId;

            if (attributes.hasValue(R.styleable.Dashboard_labelMenuType)) {
                buttonsMenuId = attributes.getResourceId(R.styleable.Dashboard_labelMenu, 0);
                int labelMenuType = attributes.getInt(R.styleable.Dashboard_labelMenuType, -1);


                switch (labelMenuType) {
                    case 0: {
                        if (buttonsMenuId != 0) {
                            String[] buttonsLabels = context.getResources().getStringArray(buttonsMenuId);
                            establishPanelMenu(buttonsLabels);
                        }
                        break;
                    }

                    case 1: {
                        if (buttonsMenuId != 0) {
                            MenuInflater v = new MenuInflater(context);
                            @SuppressLint("RestrictedApi") Menu buttonsMenu = new MenuBuilder(context);
                            v.inflate(buttonsMenuId, buttonsMenu);

                            establishPanelMenu(buttonsMenu);
                        }
                        break;
                    }

                    case 2: {
                        MenuInflater v = new MenuInflater(context);
                        @SuppressLint("RestrictedApi") Menu buttonsMenu = new MenuBuilder(context);
                        v.inflate(buttonsMenuId, buttonsMenu);
                        establishPanelPopupMenu(buttonsMenu);
                        break;
                    }

                    case 3: {
                        MenuInflater v = new MenuInflater(context);
                        @SuppressLint("RestrictedApi") Menu buttonsMenu = new MenuBuilder(context);
                        v.inflate(buttonsMenuId, buttonsMenu);
                        establishComplexMenu(buttonsMenu);
                    }

                    break;

                    case -1: {
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + labelMenuType);
                }
            }


        } finally {
            attributes.recycle();
        }
    }

    private void establishPanelMenu(@NonNull Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            View I = helper.StyleButton_Menu(menu.getItem(i), menu.size(), i);
            LabelButtons.addView(I);
        }
    }

    public void establishPanelMenu(@NonNull String titles[]) {
        for (int i = 0; i < titles.length; ++i) {
            Button I = helper.StyleButton_Strings(titles[i], titles.length, i);
            this.LabelButtons.addView(I);
        }


    }

    public void establishPanelPopupMenu(@NonNull final Menu buttonsMenu) {
        ImageButton spinnerButton = helper.StyleButton_PopupMenu(buttonsMenu);
        LabelButtons.addView(spinnerButton);
    }

    public void establishComplexMenu(@NonNull final Menu buttonsMenu) {
        for (int i = 0; i < buttonsMenu.size(); i++) {
            if (!buttonsMenu.getItem(i).getTitle().equals("popups")) {
                View I = helper.StyleButton_Menu(buttonsMenu.getItem(i), buttonsMenu.size(), i);
                LabelButtons.addView(I);
            } else {
                final ImageButton spinnerButton = helper.StyleButton_PopupMenu(buttonsMenu.getItem(i));
                LabelButtons.addView(spinnerButton);
            }
        }

    }

    public void removeLabel() {
        IS_LABEL_VISIBLE = false;
        DashboardLabel.setVisibility(GONE);
    }

    public void setLabelTitle(@NonNull String label) {
        this.LabelHeader.setText(label);
    }

    public void setLabelTitle(@StringRes int label) {
        this.LabelHeader.setText(getResources().getString(label));
    }

    public void setLabelTitle(@NonNull CharSequence label) {
        this.LabelHeader.setText(label);
    }

    public void setLabelIcon(@DrawableRes int icon){
        this.LabelIcon.setImageDrawable(ContextCompat.getDrawable(selfContext, icon));
    }

    public void setLabelIcon(@NonNull Drawable icon){
        this.LabelIcon.setImageDrawable(icon);
    }

    public void addChildWithSeparator(@NonNull View view) {
        if (indexOfChild(view) != -1) childrenWithSeparators.add(indexOfChild(view));
    }



    private class DashboardPainter {
        @DimenRes
        private final int LABEL_HEIGHT = getResources().getDimensionPixelSize(R.dimen.dashboard_labelSize);
        @DimenRes
        private final int CORNER_RADIUS = getResources().getDimensionPixelSize(R.dimen.dashboard_corner_radius);


        @ColorInt
        private final int COLOR_BORDERLINE = ContextCompat.getColor(selfContext, R.color.colorDashboardBorderline);
        @ColorInt
        private final int COLOR_INNER_BORDERLINE = ContextCompat.getColor(selfContext, R.color.colorDashboardInnerBorderline);

        private void drawInnerBorder(Canvas canvas) {
            Paint innerlinePaint = new Paint();
            innerlinePaint.setColor(COLOR_INNER_BORDERLINE);
            innerlinePaint.setStrokeWidth(STROKE_WIDTH);
            innerlinePaint.setStyle(Paint.Style.STROKE);
            innerlinePaint.setAntiAlias(true);

            canvas.save();
            canvas.clipRect(0, LABEL_HEIGHT + STROKE_WIDTH, getWidth(), getHeight());
            canvas.drawRoundRect(STROKE_WIDTH, 0,
                    getWidth() - STROKE_WIDTH, getHeight() - STROKE_WIDTH,
                    CORNER_RADIUS * 0.5f, CORNER_RADIUS * 0.5f, innerlinePaint);
            canvas.restore();
            canvas.drawLine(STROKE_WIDTH, LABEL_HEIGHT + STROKE_WIDTH * 1.5f, getWidth() - STROKE_WIDTH, LABEL_HEIGHT + STROKE_WIDTH * 1.5f, innerlinePaint);


        }

        private void drawInnerBorder(Canvas canvas, boolean NoBorders) {
            Paint innerlinePaint = new Paint();
            innerlinePaint.setAntiAlias(true);
            innerlinePaint.setColor(COLOR_INNER_BORDERLINE);
            innerlinePaint.setStrokeWidth(STROKE_WIDTH);
            innerlinePaint.setStyle(Paint.Style.STROKE);

            canvas.drawRoundRect(STROKE_WIDTH, STROKE_WIDTH,
                    getWidth() - STROKE_WIDTH, getHeight() - STROKE_WIDTH,
                    CORNER_RADIUS * 0.5f, CORNER_RADIUS * 0.5f, innerlinePaint);

        }


        private void drawOuterBorder(Canvas canvas) {
            Paint outerlinePaint = new Paint();
            outerlinePaint.setColor(COLOR_BORDERLINE);
            outerlinePaint.setStrokeWidth(STROKE_WIDTH);
            outerlinePaint.setAntiAlias(true);
            outerlinePaint.setStyle(Paint.Style.STROKE);

        /*    canvas.save();
            canvas.clipRect(0, LABEL_HEIGHT, getWidth(), getHeight());
            canvas.drawRoundRect(STROKE_WIDTH/2, STROKE_WIDTH/2,
                    getWidth()-STROKE_WIDTH/2, getHeight()-STROKE_WIDTH/2-0.5f,
                    CORNER_RADIUS, CORNER_RADIUS, outerlinePaint);
            canvas.drawLine(STROKE_WIDTH-1, LABEL_HEIGHT+STROKE_WIDTH/2+0.5f, getWidth()-STROKE_WIDTH+1,  LABEL_HEIGHT+STROKE_WIDTH/2+0.5f, outerlinePaint);
            canvas.restore();*/

            canvas.drawRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    CORNER_RADIUS, CORNER_RADIUS, outerlinePaint);
            canvas.drawLine(STROKE_WIDTH / 2,
                    LABEL_HEIGHT + STROKE_WIDTH / 2,
                    getWidth() - STROKE_WIDTH / 2,
                    LABEL_HEIGHT + STROKE_WIDTH / 2,
                    outerlinePaint);

        }

        private void drawOuterBorder(Canvas canvas, boolean NoBorders) {

            Paint outerlinePaint = new Paint();
            outerlinePaint.setColor(COLOR_BORDERLINE);
            outerlinePaint.setAntiAlias(true);
            outerlinePaint.setStrokeWidth(STROKE_WIDTH);
            outerlinePaint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(0, 0,
                    getWidth(), getHeight(),
                    CORNER_RADIUS, CORNER_RADIUS, outerlinePaint);
        }

        private void drawSeparator(Canvas canvas, int childBottom) {

            Paint outerlinePaint = new Paint();
            outerlinePaint.setColor(COLOR_BORDERLINE);
            outerlinePaint.setAntiAlias(true);
            outerlinePaint.setStrokeWidth(STROKE_WIDTH);
            outerlinePaint.setStyle(Paint.Style.STROKE);

            Paint innerlinePaint = new Paint();
            innerlinePaint.setAntiAlias(true);
            innerlinePaint.setColor(COLOR_INNER_BORDERLINE);
            innerlinePaint.setStrokeWidth(STROKE_WIDTH);
            innerlinePaint.setStyle(Paint.Style.STROKE);

            canvas.drawLine(0, childBottom, getWidth(), childBottom, outerlinePaint);
            canvas.drawLine(STROKE_WIDTH, childBottom - outerlinePaint.getStrokeWidth(), getWidth() - STROKE_WIDTH, childBottom - outerlinePaint.getStrokeWidth(), innerlinePaint);
            canvas.drawLine(STROKE_WIDTH, childBottom + outerlinePaint.getStrokeWidth(), getWidth() - STROKE_WIDTH, childBottom + outerlinePaint.getStrokeWidth(), innerlinePaint);

        }

        private void drawShadow(Canvas canvas) {
            Paint shadowPaint = new Paint();
            shadowPaint.setStyle(Paint.Style.STROKE);
            shadowPaint.setAntiAlias(true);
            shadowPaint.setColor(Color.BLACK);
            shadowPaint.setStrokeWidth(STROKE_WIDTH);

            float diff = 0.25f;
            shadowPaint.setMaskFilter(new BlurMaskFilter(dp(3), BlurMaskFilter.Blur.NORMAL));

            canvas.drawLine(
                    getWidth() + STROKE_WIDTH - diff,
                    CORNER_RADIUS,
                    getWidth() + STROKE_WIDTH - diff,
                    getHeight() - CORNER_RADIUS, shadowPaint
            );

            canvas.drawLine(CORNER_RADIUS,
                    getHeight() + STROKE_WIDTH - diff,
                    getWidth() - CORNER_RADIUS,
                    getHeight() + STROKE_WIDTH - diff, shadowPaint
            );


         /*   canvas.drawArc(
                    getWidth() - CORNER_RADIUS - diff,
                    getHeight() - CORNER_RADIUS - diff,
                    getWidth()+STROKE_WIDTH-diff,
                    getHeight()+STROKE_WIDTH-diff,
                    0,
                    90,
                    false,
                    shadowPaint
            );*/

            Path path = new Path();
            path.moveTo(getWidth() - CORNER_RADIUS, getHeight() + STROKE_WIDTH - diff);
         /*   path.quadTo(
                    getWidth(), getHeight(),
                    getWidth() + STROKE_WIDTH-diff, getHeight() - CORNER_RADIUS


            );
            */
            path.cubicTo(
                    getWidth() - CORNER_RADIUS, getHeight() + STROKE_WIDTH - diff,
                    getWidth() + STROKE_WIDTH, getHeight() + STROKE_WIDTH,
                    getWidth() + STROKE_WIDTH - diff, getHeight() - CORNER_RADIUS

            );
            canvas.drawPath(path, shadowPaint);
        }
    }

    private class DashboardHelper {

        private StateListDrawable ViewBackground = new StateListDrawable();

        private ColorStateList BUTTON_TEXT_STATE_LIST = new ColorStateList(new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
                new int[]{-android.R.attr.state_enabled},
                new int[]{}
        },
                new int[]{
                        COLOR_BUTTON_DEFAULT,
                        COLOR_BUTTON_PRESSED,
                        COLOR_BUTTON_DEFAULT,
                        COLOR_BUTTON_PRESSED,
                        COLOR_BUTTON_PRESSED
                });


        {
            Drawable def = ContextCompat.getDrawable(selfContext, R.drawable.dashboard_icon_shape);
            Drawable pressed = def.getConstantState().newDrawable().mutate();
            pressed.setTint(COLOR_BUTTON_PRESSED);

            ViewBackground.addState(new int[]{android.R.attr.state_pressed}, pressed);
            ViewBackground.addState(new int[]{android.R.attr.state_enabled}, def);
            ViewBackground.addState(new int[]{android.R.attr.state_focused, android.R.attr.state_pressed}, pressed);
            ViewBackground.addState(new int[]{-android.R.attr.state_enabled}, def);
            ViewBackground.addState(new int[]{}, def);
            ViewBackground.setEnterFadeDuration(25);
            ViewBackground.setExitFadeDuration(75);
        }

        public ImageButton StyleButton_PopupMenu(@NonNull final Menu menu) {
            final ImageButton spinnerButton = new ImageButton(selfContext);
            Drawable currentBackground = ViewBackground.getConstantState().newDrawable().mutate();
            spinnerButton.setBackground(currentBackground);
            spinnerButton.setScaleType(ImageView.ScaleType.CENTER);
            spinnerButton.setImageDrawable(ContextCompat.getDrawable(selfContext, androidx.appcompat.R.drawable.abc_ic_menu_overflow_material));
            spinnerButton.setImageTintList(helper.BUTTON_TEXT_STATE_LIST);

            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = 10;
            params.bottomMargin = 10;
            spinnerButton.setMinimumWidth(0);
            spinnerButton.setMinimumHeight(0);

            spinnerButton.setPadding(4, 16, 4, 16);
            spinnerButton.setLayoutParams(params);

            spinnerButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu PopupMenu = new PopupMenu(selfContext, view);
                    for (int i = 0; i < menu.size(); i++) {
                        MenuItem current = menu.getItem(i);
                        PopupMenu.getMenu().add(current.getTitle());
                    }
                    PopupMenu.show();
                }
            });
            return spinnerButton;
        }

        public ImageButton StyleButton_PopupMenu(@NonNull final MenuItem holderItem) {
            final ImageButton spinnerButton = new ImageButton(selfContext);
            Drawable currentBackground = ViewBackground.getConstantState().newDrawable().mutate();
            spinnerButton.setBackground(currentBackground);
            spinnerButton.setScaleType(ImageView.ScaleType.CENTER);
            spinnerButton.setImageDrawable(ContextCompat.getDrawable(selfContext, androidx.appcompat.R.drawable.abc_ic_menu_overflow_material));
            spinnerButton.setImageTintList(helper.BUTTON_TEXT_STATE_LIST);

            final LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = 10;
            params.bottomMargin = 10;
            spinnerButton.setMinimumWidth(0);
            spinnerButton.setMinimumHeight(0);

            spinnerButton.setPadding(4, 16, 4, 16);
            spinnerButton.setLayoutParams(params);

            spinnerButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu PopupMenu = new PopupMenu(selfContext, view);

                    SubMenu contextMenu = holderItem.getSubMenu();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        PopupMenu.setForceShowIcon(true);
                    }


                    for (int i = 0; i < contextMenu.size(); i++) {
                        MenuItem current = contextMenu.getItem(i);
                        PopupMenu.getMenu().add(current.getTitle());
                        //PopupMenu.getMenu().getItem(i).setIcon(current.getIcon());

                    }

                    PopupMenu.show();


                }
            });
            return spinnerButton;
        }

        /*private ListPopupWindow getPopup(SubMenu subMenu, View spinnerButton) {
            ListPopupWindow window = new ListPopupWindow(getContext(),null);
            window.setAnchorView(spinnerButton);
            window.setWidth((int) dp(128));
            return null;
        }*/

        public Button StyleButton_Strings(@NonNull String title, int length, int i) {
            LinearLayout.LayoutParams ButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ButtonParams.rightMargin = (int) dp(8);
            ButtonParams.topMargin = 10;
            ButtonParams.bottomMargin = 10;
            ButtonParams.gravity = Gravity.CENTER_VERTICAL;

            Button I = new Button(selfContext);
            I.setIncludeFontPadding(false);


            if (i != length - 1) {
                I.setLayoutParams(ButtonParams);
            } else {
                I.setLayoutParams(ButtonParams);
                ((LayoutParams) (I.getLayoutParams())).rightMargin = 0;
            }
            I.setMinWidth(0);
            I.setMinimumWidth(0);
            I.setMinHeight(0);
            I.setMinimumHeight(0);
            I.setPadding(16, 16, 16, 16);


            /*I.setBackground(ContextCompat.getDrawable(selfContext, R.drawable.dashboard_icon_shape));
            I.setBackgroundTintList(BUTTON_STATE_LIST);*/


            Drawable currentBackground = ViewBackground.getConstantState().newDrawable().mutate();
            I.setBackground(currentBackground);

            I.setText(title);
            I.setTextColor(BUTTON_TEXT_STATE_LIST);
            I.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            I.setTextSize(11);
            I.setLetterSpacing(0.05f);

            I.setAllCaps(false);
            I.setClickable(true);

            I.setId(ViewCompat.generateViewId());
            return I;
        }

        public View StyleButton_Menu(@NonNull MenuItem item, int size, int index) {
            LinearLayout.LayoutParams ButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ButtonParams.rightMargin = (int) dp(8);
            ButtonParams.topMargin = 10;
            ButtonParams.bottomMargin = 10;

            if (item.getIcon() != null) {
                ImageButton I = new ImageButton(selfContext);
                I.setScaleType(ImageView.ScaleType.CENTER);
                I.setAdjustViewBounds(true);
                Drawable currentBackground = ViewBackground.getConstantState().newDrawable().mutate();
                I.setBackground(currentBackground);
                I.setImageDrawable(item.getIcon());
                I.setMinimumWidth(0);
                I.setMinimumHeight(0);
                if (item.getIconTintList() != null) {
                    int color = item.getIconTintList().getColorForState(new int[]{}, COLOR_BUTTON_DEFAULT);
                    ColorStateList ICON_TINT_LIST = new ColorStateList(new int[][]{
                            new int[]{android.R.attr.state_pressed},
                            new int[]{android.R.attr.state_enabled},
                            new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
                            new int[]{-android.R.attr.state_enabled},
                            new int[]{}
                    },
                            new int[]{
                                    COLOR_BUTTON_DEFAULT,
                                    color,
                                    COLOR_BUTTON_DEFAULT,
                                    color,
                                    color
                            });
                    I.setImageTintList(ICON_TINT_LIST);
                } else {
                    I.setImageTintList(BUTTON_TEXT_STATE_LIST);
                }
                I.setLayoutParams(ButtonParams);
                if (index == size - 1) ((LayoutParams) I.getLayoutParams()).rightMargin = 0;
                I.setPadding(20, 16, 20, 16);
                I.setClickable(true);
                return I;
            } else if (item.getTitle() != null) {
                Button I = StyleButton_Strings(item.getTitle().toString(), size, index);
                LinearLayout.LayoutParams params = (LayoutParams) I.getLayoutParams();
                params.gravity = Gravity.CENTER_VERTICAL;
                I.setLayoutParams(params);
                return I;
            }

            return null;

        }


    }


}



