package jewpigeon.apps.newgrounds.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
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
    public Dashboard(@NonNull Context context) {
        super(context);
    }

    public Dashboard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dashboard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.dashboard_labeled, this);
        Label = findViewById(R.id.dashboard_label);
        LabelName = findViewById(R.id.dashboard_label_name);
        LabelIcon = findViewById(R.id.dashboard_label_icon);
        LabelName.setText("Hello world!");

    }




}
