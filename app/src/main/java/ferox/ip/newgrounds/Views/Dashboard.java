package ferox.ip.newgrounds.Views;

import android.content.Context;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import ferox.ip.newgrounds.R;


public class Dashboard extends CardView {
private ConstraintLayout Label;
private TextView LabelName;
private ImageView LabelIcon;
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
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.Dashboard, 0, 0);
        try {
            boolean labelVisible = ta.getBoolean(R.styleable.Dashboard_labelVisible, true);
            if(!labelVisible) removeLabel();
            String labelName = ta.getString(R.styleable.Dashboard_labelText);
            LabelName.setText(labelName == null ? getResources().getString(R.string.LabelNameDefault):labelName);
        } finally {
            ta.recycle();
        }
    }


    public void removeLabel(){
        this.Label.setVisibility(GONE);

    }

}
