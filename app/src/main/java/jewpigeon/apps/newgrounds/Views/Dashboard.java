package jewpigeon.apps.newgrounds.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;

public class Dashboard extends LinearLayout {

    private final int LABEL_HEIGHT = (int) (getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_labelSize));
    private final int STROKE_WIDTH = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_stroke_width);
    private final int ICON_SIZE = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_iconSize);
    private final int TEXT_SIZE = (int) getContext().getResources().getDimension(R.dimen.dashboard_textSize);
    private final int ICON_PADDING = (int) DimensionTool.dp(2);
    private final int CORNER_RADIUS = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_corner_radius);
    private ConstraintLayout DashboardLabel;
    private TextView LabelHeader;
    private ImageView LabelIcon;
    private LinearLayout LabelButtons;
    private boolean IS_LABEL_VISIBLE = true;
    private ArrayList<Integer> childrenWithSeparators = new ArrayList<>();
    private Drawable LabelBackground;
    private GradientDrawable MainBackground;
    private int COLOR_BD = ContextCompat.getColor(getContext(), R.color.colorDashboardIconBackground);
    private int COLOR_BP = ContextCompat.getColor(getContext(), R.color.colorAccent);


    private ColorStateList BUTTON_STATE_LIST = new ColorStateList(new int[][]{

            new int[]{android.R.attr.state_pressed},
            new int[]{android.R.attr.state_enabled},
            new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
            new int[]{-android.R.attr.state_enabled},
            new int[]{}
    },
            new int[]{
                    COLOR_BP,
                    COLOR_BD,
                    COLOR_BP,
                    COLOR_BD,
                    COLOR_BD
            });


    private ColorStateList BUTTON_TEXT_STATE_LIST = new ColorStateList(new int[][]{
            new int[]{android.R.attr.state_pressed},
            new int[]{android.R.attr.state_enabled},
            new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
            new int[]{-android.R.attr.state_enabled},
            new int[]{}
    },
            new int[]{
                    COLOR_BD,
                    COLOR_BP,
                    COLOR_BD,
                    COLOR_BP,
                    COLOR_BP
            });


    {
        MainBackground = new GradientDrawable();
        MainBackground.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardBackground));
        MainBackground.setShape(GradientDrawable.RECTANGLE);


        LabelBackground = ContextCompat.getDrawable(getContext(), R.drawable.dashboard_label_gradient);
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

    private void establishSelf(Context context, AttributeSet attributes) {
        setOrientation(VERTICAL);
        setMinimumHeight(LABEL_HEIGHT * 3 / 2);
    }


    private void establishPanel(Context context, AttributeSet set) {

        DashboardLabel = new ConstraintLayout(context);
        DashboardLabel.setId(View.generateViewId());
        DashboardLabel.setVisibility(VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LABEL_HEIGHT);
        params.gravity = Gravity.TOP;
        DashboardLabel.setLayoutParams(params);


        LabelIcon = new ImageView(context);
        LabelIcon.setLayoutParams(new ViewGroup.LayoutParams(ICON_SIZE, ICON_SIZE));
        LabelIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ng_default_icon));
        LabelIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.dashboard_icon_shape));
        LabelIcon.setId(View.generateViewId());
        LabelIcon.setPadding(ICON_PADDING, ICON_PADDING, ICON_PADDING, ICON_PADDING);
        LabelIcon.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorAccent)));
        LabelIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);


        LabelHeader = new TextView(context);
        LabelHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE);
        LabelHeader.setTextColor(Color.WHITE);
        LabelHeader.setTypeface(context.getResources().getFont(R.font.pakeham));
        LabelHeader.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(30)});
        LabelHeader.setEllipsize(TextUtils.TruncateAt.END);
        LabelHeader.setId(View.generateViewId());
        LabelHeader.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

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

        labelConstraints.connect(LabelIcon.getId(), ConstraintSet.BOTTOM, DashboardLabel.getId(), ConstraintSet.BOTTOM, (int) DimensionTool.dp(6));
        labelConstraints.connect(LabelIcon.getId(), ConstraintSet.TOP, DashboardLabel.getId(), ConstraintSet.TOP, (int) DimensionTool.dp(8));
        labelConstraints.connect(LabelIcon.getId(), ConstraintSet.START, DashboardLabel.getId(), ConstraintSet.START, (int) DimensionTool.dp(8));

        labelConstraints.connect(LabelHeader.getId(), ConstraintSet.BOTTOM, LabelIcon.getId(), ConstraintSet.BOTTOM, (int) DimensionTool.dp(8));
        labelConstraints.connect(LabelHeader.getId(), ConstraintSet.START, LabelIcon.getId(), ConstraintSet.END, (int) DimensionTool.dp(4));
        labelConstraints.connect(LabelHeader.getId(), ConstraintSet.TOP, LabelIcon.getId(), ConstraintSet.TOP, (int) DimensionTool.dp(8));

        labelConstraints.connect(LabelButtons.getId(), ConstraintSet.BOTTOM, DashboardLabel.getId(), ConstraintSet.BOTTOM, 0);
        labelConstraints.connect(LabelButtons.getId(), ConstraintSet.END, DashboardLabel.getId(), ConstraintSet.END, (int) DimensionTool.dp(8));
        labelConstraints.connect(LabelButtons.getId(), ConstraintSet.TOP, DashboardLabel.getId(), ConstraintSet.TOP, 6);


        labelConstraints.applyTo(DashboardLabel);

        establishPanelData(set, context);

        this.addView(DashboardLabel);
    }

    public void establishPanelData(AttributeSet attributeSet, Context context) {
        if (attributeSet == null) return;
        TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Dashboard, 0, 0);
        try {
            boolean labelVisible = attributes.getBoolean(R.styleable.Dashboard_labelVisible, true);
            if (!labelVisible) removeLabel();
            String labelName = attributes.getString(R.styleable.Dashboard_labelText);
            LabelHeader.setText(labelName == null ? getResources().getString(R.string.LabelNameDefault) : labelName);
            Drawable labelIcon = attributes.getDrawable(R.styleable.Dashboard_labelIcon);
            if (labelIcon != null) LabelIcon.setImageDrawable(labelIcon);
            int buttonsMenuId = attributes.getResourceId(R.styleable.Dashboard_labelMenu, 0);

            if (buttonsMenuId != 0) {
                MenuInflater v = new MenuInflater(context);
                @SuppressLint("RestrictedApi") Menu buttonsMenu = new MenuBuilder(context);
                v.inflate(buttonsMenuId, buttonsMenu);

                establishPanelMenu(buttonsMenu);
            }

            int buttonsStringMenuId = attributes.getResourceId(R.styleable.Dashboard_labelMenuStrings, 0);
            if (buttonsStringMenuId != 0) {
                String[] buttonsLabels = context.getResources().getStringArray(buttonsStringMenuId);
                establishPanelMenu(buttonsLabels);
            }


        } finally {
            attributes.recycle();
        }
    }

    private void establishPanelMenu(Menu menu) {
        MaterialButton[] buttons = new MaterialButton[menu.size()];
        LinearLayout.LayoutParams ButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButtonParams.rightMargin = (int) DimensionTool.dp(8);
        ButtonParams.gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < menu.size(); ++i) {

            buttons[i] = new MaterialButton(getContext());
            buttons[i].setIncludeFontPadding(false);
            buttons[i].setCornerRadius(10);


            buttons[i].setMinWidth(0);
            buttons[i].setMinimumWidth(0);
            buttons[i].setPadding(16, 12, 16, 12);
            buttons[i].setBackgroundTintList(BUTTON_STATE_LIST);
            buttons[i].setRippleColorResource(android.R.color.transparent);
            if (!menu.getItem(i).getTitle().equals("")) {
                buttons[i].setText(menu.getItem(i).getTitle());
            }
            if (menu.getItem(i).getIcon() != null) {
                buttons[i].setIcon(menu.getItem(i).getIcon());
                if (menu.getItem(i).getIconTintList() != null) {
                    int color = menu.getItem(i).getIconTintList().getColorForState(new int[]{}, COLOR_BD);
                    ColorStateList ICON_TINT_LIST = new ColorStateList(new int[][]{
                            new int[]{android.R.attr.state_pressed},
                            new int[]{android.R.attr.state_enabled},
                            new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
                            new int[]{-android.R.attr.state_enabled},
                            new int[]{}
                    },
                            new int[]{
                                    COLOR_BD,
                                    color,
                                    COLOR_BD,
                                    color,
                                    color
                            });
                    buttons[i].setIconTint(ICON_TINT_LIST);

                } else {
                    buttons[i].setIconTint(BUTTON_TEXT_STATE_LIST);
                }

                buttons[i].setIconGravity(MaterialButton.ICON_GRAVITY_START);
                buttons[i].setIconSize((int) DimensionTool.dp(16));
                buttons[i].setIconPadding(0);
                buttons[i].setPadding(16, 12, 16, 12);

            }

            if (i != menu.size() - 1) {
                buttons[i].setLayoutParams(ButtonParams);
            }


            buttons[i].setTextColor(BUTTON_TEXT_STATE_LIST);
            buttons[i].setTextAlignment(TEXT_ALIGNMENT_CENTER);
            buttons[i].setTextSize(11);
            buttons[i].setLetterSpacing(0.05f);
            buttons[i].setAllCaps(false);
            buttons[i].setClickable(true);

            buttons[i].setId(menu.getItem(i).getItemId());
            this.LabelButtons.addView(buttons[i]);
        }
    }

    public void establishPanelMenu(String titles[]) {
        MaterialButton[] buttons = new MaterialButton[titles.length];
        LinearLayout.LayoutParams ButtonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButtonParams.rightMargin = (int) DimensionTool.dp(8);
        ButtonParams.gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < titles.length; ++i) {
            buttons[i] = new MaterialButton(getContext());
            buttons[i].setIncludeFontPadding(false);
            buttons[i].setCornerRadius(10);


            if (i != titles.length - 1) {
                buttons[i].setLayoutParams(ButtonParams);
            }
            buttons[i].setMinWidth(0);
            buttons[i].setMinimumWidth(0);
            buttons[i].setPadding(16, 12, 16, 12);

            buttons[i].setBackgroundTintList(BUTTON_STATE_LIST);
            buttons[i].setRippleColorResource(android.R.color.transparent);

            buttons[i].setText(titles[i]);
            buttons[i].setTextColor(BUTTON_TEXT_STATE_LIST);
            buttons[i].setTextAlignment(TEXT_ALIGNMENT_CENTER);
            buttons[i].setTextSize(11);
            buttons[i].setLetterSpacing(0.05f);
            buttons[i].setAllCaps(false);
            buttons[i].setClickable(true);

            buttons[i].setId(ViewCompat.generateViewId());

            this.LabelButtons.addView(buttons[i]);
        }


    }

    public void removeLabel() {
        IS_LABEL_VISIBLE = false;
        DashboardLabel.setVisibility(GONE);
    }


    public void setLabelTitle(String label) {
        this.LabelHeader.setText(label);
    }

    public void setLabelTitle(CharSequence label) {
        this.LabelHeader.setText(label);
    }


    public void inputChildWithSeparator(View view) {
        if (indexOfChild(view) != -1) childrenWithSeparators.add(indexOfChild(view));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        DashboardPainter painter = new DashboardPainter();
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
                            ((LayoutParams)child.getLayoutParams()).bottomMargin);
                }

            }
        }


    }


    private class DashboardPainter {
        private final int LABEL_HEIGHT = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_labelSize);
        private final int CORNER_RADIUS = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_corner_radius);

        private void drawInnerBorder(Canvas canvas) {
            Paint innerlinePaint = new Paint();
            innerlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardInnerBorderline));
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
            innerlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardInnerBorderline));
            innerlinePaint.setStrokeWidth(STROKE_WIDTH);
            innerlinePaint.setStyle(Paint.Style.STROKE);

            canvas.drawRoundRect(STROKE_WIDTH, STROKE_WIDTH,
                    getWidth() - STROKE_WIDTH, getHeight() - STROKE_WIDTH,
                    CORNER_RADIUS * 0.5f, CORNER_RADIUS * 0.5f, innerlinePaint);

        }

        private void drawOuterBorder(Canvas canvas) {
            Paint outerlinePaint = new Paint();
            outerlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardBorderline));
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
            outerlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardBorderline));
            outerlinePaint.setAntiAlias(true);
            outerlinePaint.setStrokeWidth(STROKE_WIDTH);
            outerlinePaint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(0, 0,
                    getWidth(), getHeight(),
                    CORNER_RADIUS, CORNER_RADIUS, outerlinePaint);
        }

        private void drawSeparator(Canvas canvas, int childBottom) {
            Paint outerlinePaint = new Paint();
            outerlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardBorderline));
            outerlinePaint.setAntiAlias(true);
            outerlinePaint.setStrokeWidth(STROKE_WIDTH);
            outerlinePaint.setStyle(Paint.Style.STROKE);
            Paint innerlinePaint = new Paint();
            innerlinePaint.setAntiAlias(true);
            innerlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorDashboardInnerBorderline));
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
            shadowPaint.setStrokeWidth(STROKE_WIDTH * 0.75f);
            shadowPaint.setMaskFilter(new BlurMaskFilter(DimensionTool.dp(3), BlurMaskFilter.Blur.NORMAL));
            canvas.drawLine(getWidth() + STROKE_WIDTH, CORNER_RADIUS, getWidth() + STROKE_WIDTH, getHeight() - CORNER_RADIUS / 2, shadowPaint);
            canvas.drawLine(CORNER_RADIUS, getHeight() + STROKE_WIDTH, getWidth() - CORNER_RADIUS / 2, getHeight() + STROKE_WIDTH, shadowPaint);
            canvas.drawArc(
                    getWidth() - CORNER_RADIUS - STROKE_WIDTH,
                    getHeight() - CORNER_RADIUS - STROKE_WIDTH,
                    getWidth() + STROKE_WIDTH,
                    getHeight() + STROKE_WIDTH,
                    0,
                    90,
                    false,
                    shadowPaint

            );
        }
    }


}
