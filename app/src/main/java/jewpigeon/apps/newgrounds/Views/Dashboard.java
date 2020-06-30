package jewpigeon.apps.newgrounds.Views;

import android.content.Context;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import jewpigeon.apps.newgrounds.R;


public class Dashboard extends CardView {
private ConstraintLayout Label;
private TextView LabelName;
private ImageView LabelIcon;
private LinearLayout ButtonPanelLayout;
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

    private void establishComponents(Context context, @Nullable  AttributeSet attributeSet){
        inflate(getContext(), R.layout.dashboard, this);
        Label = this.findViewById(R.id.dashboard_label);
        LabelName = this.findViewById(R.id.dashboard_label_name);
        LabelIcon = this.findViewById(R.id.dashboard_label_icon);
        ButtonPanelLayout = this.findViewById(R.id.dashboard_buttons_panel);
        TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Dashboard, 0, 0);
        try {
            boolean labelVisible = attributes.getBoolean(R.styleable.Dashboard_labelVisible, true);
            if(!labelVisible) removeLabel();
            String labelName = attributes.getString(R.styleable.Dashboard_labelText);
            LabelName.setText(labelName == null ? getResources().getString(R.string.LabelNameDefault):labelName);
            int buttonsMenuId = attributes.getResourceId(R.styleable.Dashboard_PanelButtonsList, 0);
            if(buttonsMenuId!=0){
                Menu buttonsMenu = (Menu) LayoutInflater.from(getContext()).inflate(buttonsMenuId, null);
                establishButtons(buttonsMenu);
            }


        } finally {
            attributes.recycle();
        }
    }

    private void establishButtons(Menu menu) {
        Button[] buttons = new Button[menu.size()];
        for (int i = 0; i < menu.size(); i++){
            buttons[i] = new Button(getContext());
            buttons[i].setText(menu.getItem(i).getTitle());
            this.ButtonPanelLayout.addView(buttons[i]);
        }
    }


    public void removeLabel(){
        this.Label.setVisibility(GONE);

    }

}
