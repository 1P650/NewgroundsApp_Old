package jewpigeon.apps.newgrounds.Views.DashboardData.DashLayouts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;

public class GenericAuthorLayout extends ViewGroup {

    private Context selfContext = getContext();



    private ImageView AuthorProfilePicture;
    private TextView AuthorUsername;
    private TextView AuthorCredit;
    private MaterialButton FollowAuthor;


    private final int ITEM_HEIGHT = getContext().getResources().getDimensionPixelSize(R.dimen.dashboard_item_size_medium_list);
    private final int ICON_SIZE = ITEM_HEIGHT *8/9;

    private final int BUTTON_COLOR = ContextCompat.getColor(getContext(), R.color.colorAccentAlt);
    private final int USERNAME_TEXT_COLOR = ContextCompat.getColor(getContext(), R.color.colorAccent);
    private final int CREDIT_TEXT_COLOR = ContextCompat.getColor(getContext(), R.color.colorItemAuthorText);

    private Drawable defIcon;

    {
        defIcon = ContextCompat.getDrawable(getContext(), R.drawable.ng_icon_undefined_circle);
    }


    public GenericAuthorLayout(Context context) {
        super(context);
        establishSelf(context);
    }

    public GenericAuthorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        establishSelf(context);
    }

    public GenericAuthorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishSelf(context);
    }

    public GenericAuthorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishSelf(context);
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        AuthorProfilePicture.layout(ICON_SIZE/8,(ITEM_HEIGHT - ICON_SIZE)/2, ICON_SIZE*9/8, ITEM_HEIGHT - (ITEM_HEIGHT - ICON_SIZE)/2);
        AuthorUsername.layout(
                ICON_SIZE+ICON_SIZE/4,
                ICON_SIZE/2-AuthorUsername.getMeasuredHeight()/2-10,
                ICON_SIZE + AuthorUsername.getMeasuredWidth()+ICON_SIZE/4,
                ICON_SIZE/2-10+AuthorUsername.getMeasuredHeight()/2);
        AuthorCredit.layout(ICON_SIZE+ICON_SIZE/4,
                ICON_SIZE-AuthorCredit.getMeasuredHeight(),
                ICON_SIZE + AuthorCredit.getMeasuredWidth()+ICON_SIZE/4,
                ICON_SIZE);
        FollowAuthor.layout(getWidth() - FollowAuthor.getMeasuredWidth() - 32,
                (ITEM_HEIGHT - FollowAuthor.getMeasuredHeight())/2, getWidth() -32
                , (ITEM_HEIGHT + FollowAuthor.getMeasuredHeight())/2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        AuthorProfilePicture.measure( MeasureSpec.makeMeasureSpec(ICON_SIZE, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(ICON_SIZE, MeasureSpec.EXACTLY));
        AuthorUsername.measure( MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.UNSPECIFIED));
        AuthorCredit.measure( MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.UNSPECIFIED));
        FollowAuthor.measure(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.UNSPECIFIED));
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(widthMeasureSpec,MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(ITEM_HEIGHT, MeasureSpec.EXACTLY));
    }

    private void establishSelf(Context context) {
        AuthorProfilePicture = new ImageView(context);
        AuthorUsername = new TextView(context);
        AuthorCredit = new TextView(context);
        FollowAuthor = new MaterialButton(context);


        AuthorProfilePicture.setImageDrawable(defIcon);

        AuthorUsername.setTextColor(USERNAME_TEXT_COLOR);
        AuthorUsername.setTextSize(16);
        AuthorUsername.setTypeface(Typeface.DEFAULT_BOLD);

        AuthorCredit.setTextColor(CREDIT_TEXT_COLOR);
        AuthorCredit.setTextSize(12);



        FollowAuthor.setText("FOLLOW");
        FollowAuthor.setCornerRadius((int) DimensionTool.dp(4));
        FollowAuthor.setBackgroundColor(BUTTON_COLOR);
        FollowAuthor.setTextColor(Color.WHITE);


        FollowAuthor.setMinHeight(0);
        FollowAuthor.setMinimumHeight(0);
        FollowAuthor.setPadding(0, 28, 0, 28);

        AuthorUsername.setText("DEFAULT");
        AuthorCredit.setText("Creator");


        addView(AuthorProfilePicture);
        addView(AuthorUsername);
        addView(AuthorCredit);
        addView(FollowAuthor);


        this.setClickable(true);


    }

    public void setAuthorUsername(String username){

    }

    public void setAuthorRole(String role){

    }
    public void setAuthorIcon(Drawable icon){

    }

    public void setAuthorIcon(Uri icon){

    }
}
