package jewpigeon.apps.newgrounds.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;


public class Dashboard extends MaterialCardView {
    private ConstraintLayout Label;
    private TextView LabelName;
    private ImageView LabelIcon;
    private LinearLayout ButtonPanelLayout;


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


    public Dashboard(@NonNull Context context) {
        super(context);
        establishComponents(context, null);

    }

    public Dashboard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishComponents(context, attrs);

    }

    public Dashboard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishComponents(context, attrs);

    }

    @SuppressLint("RestrictedApi")
    private void establishComponents(Context context, @Nullable AttributeSet attributeSet) {
        inflate(getContext(), R.layout.dashboard, this);

        Label = this.findViewById(R.id.dashboard_label);
        LabelName = this.findViewById(R.id.dashboard_label_name);
        ButtonPanelLayout = this.findViewById(R.id.dashboard_buttons_panel);
        LabelIcon = this.findViewById(R.id.dashboard_label_icon);


        TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Dashboard, 0, 0);
        try {
            boolean labelVisible = attributes.getBoolean(R.styleable.Dashboard_labelVisible, true);
            if (!labelVisible) removeLabel();
            String labelName = attributes.getString(R.styleable.Dashboard_labelText);
            LabelName.setText(labelName == null ? getResources().getString(R.string.LabelNameDefault) : labelName);
            Drawable labelIcon = attributes.getDrawable(R.styleable.Dashboard_labelIcon);
            if (labelIcon != null) LabelIcon.setImageDrawable(labelIcon);
            int buttonsMenuId = attributes.getResourceId(R.styleable.Dashboard_PanelButtonsList, 0);

            if (buttonsMenuId != 0) {
                MenuInflater v = new MenuInflater(getContext());
                Menu buttonsMenu = new MenuBuilder(getContext());
                v.inflate(buttonsMenuId, buttonsMenu);

                establishButtons(buttonsMenu);
            }


        } finally {
            attributes.recycle();
        }
    }

    private void establishButtons(Menu menu) {
        MaterialButton[] buttons = new MaterialButton[menu.size()];

        for (int i = 0; i < menu.size(); ++i) {
            buttons[i] = new MaterialButton(getContext());
            buttons[i].setIncludeFontPadding(false);
            buttons[i].setCornerRadius(10);
            buttons[i].setRippleColorResource(android.R.color.transparent);
            buttons[i].setMinWidth(0);
            buttons[i].setMinimumWidth(0);
            buttons[i].setBackgroundTintList(BUTTON_STATE_LIST);
            buttons[i].setText(menu.getItem(i).getTitle());
            buttons[i].setTextColor(BUTTON_TEXT_STATE_LIST);
            buttons[i].setTextAlignment(TEXT_ALIGNMENT_CENTER);
            buttons[i].setTextSize(12);
            buttons[i].setTypeface(getResources().getFont(R.font.pakeham));
            buttons[i].setAllCaps(false);
            buttons[i].setClickable(true);
            this.ButtonPanelLayout.addView(buttons[i]);
        }
    }


    public void removeLabel() {
        this.Label.setVisibility(GONE);

    }



    public void setLabelTitle(String title){
        this.LabelName.setText(title);
    }

}
