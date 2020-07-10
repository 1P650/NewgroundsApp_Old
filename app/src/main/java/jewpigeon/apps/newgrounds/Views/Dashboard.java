package jewpigeon.apps.newgrounds.Views;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;


public class Dashboard extends MaterialCardView {
private ConstraintLayout Label;
private TextView LabelName;
private ImageView LabelIcon;
private LinearLayout ButtonPanelLayout;
private TypedValue ripple = new TypedValue();
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
    private void establishComponents(Context context, @Nullable  AttributeSet attributeSet){
        inflate(getContext(), R.layout.dashboard, this);
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, ripple, true);
        Label = this.findViewById(R.id.dashboard_label);
        LabelName = this.findViewById(R.id.dashboard_label_name);
        ButtonPanelLayout = this.findViewById(R.id.dashboard_buttons_panel);
        LabelIcon = this.findViewById(R.id.dashboard_label_icon);


        TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Dashboard, 0, 0);
        try {
            boolean labelVisible = attributes.getBoolean(R.styleable.Dashboard_labelVisible, true);
            if(!labelVisible) removeLabel();
            String labelName = attributes.getString(R.styleable.Dashboard_labelText);
            LabelName.setText(labelName == null ? getResources().getString(R.string.LabelNameDefault):labelName);
            Drawable labelIcon = attributes.getDrawable(R.styleable.Dashboard_labelIcon);
            if(labelIcon != null) LabelIcon.setImageDrawable(labelIcon);
            int buttonsMenuId = attributes.getResourceId(R.styleable.Dashboard_PanelButtonsList, 0);

            if(buttonsMenuId!=0){
                MenuInflater v = new MenuInflater(getContext());
                Menu buttonsMenu = new MenuBuilder(getContext());
                v.inflate(buttonsMenuId, buttonsMenu);

                establishButtons(buttonsMenu);
            }


        }
        finally {
            attributes.recycle();
        }
    }

    private void establishButtons(Menu menu) {
       Button[] buttons = new Button[menu.size()];
       for (int i = 0; i < menu.size(); i++){
           buttons[i] = new Button(getContext());
           buttons[i].setText(menu.getItem(i).getTitle());
           buttons[i].setLayoutParams(new LayoutParams(200, 100));
           buttons[i].setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.dashboard_button_shape));
           buttons[i].setTextColor(ContextCompat.getColor(this.getContext(), R.color.colorAccent));
           buttons[i].setTextAlignment(TEXT_ALIGNMENT_CENTER);
           buttons[i].setTextSize(10);
           buttons[i].setAllCaps(false);
           buttons[i].setClickable(true);
           this.ButtonPanelLayout.addView(buttons[i]);
       }
    }


    public void removeLabel(){
        this.Label.setVisibility(GONE);

    }

}
