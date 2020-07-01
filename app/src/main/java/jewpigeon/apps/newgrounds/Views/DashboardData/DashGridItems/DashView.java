package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;





import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import jewpigeon.apps.newgrounds.R;


public class DashView extends View {

    private StaticLayout DashTitle;
    private StaticLayout DashAuthor;
    private Drawable DashIcon;
    private Drawable DashTextBackground;

    private TextPaint DashTitlePainter;
    private TextPaint DashAuthorPainter;

    public DashView(Context context) {
        super(context);
        establishPainters();
    }

    public DashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        establishPainters();
    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establishPainters();
    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        establishPainters();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) dp(108),(int) dp(108));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DashIcon.draw(canvas);
        canvas.save();
        canvas.translate(0, dp(54));
        DashTextBackground.draw(canvas);
        canvas.save();
        DashTitle.draw(canvas);
        canvas.translate(0, DashTitle.getHeight());
        DashAuthor.draw(canvas);

        canvas.restore();
    }



    public void setDashItem(DashItem item){
        DashIcon = new ColorDrawable(Color.WHITE);
        DashTextBackground = new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorDashboardItemLabelBackground));
        if (item.getImage() != null){
            DashIcon = item.getImage();
        }
        else{
            DashIcon = ContextCompat.getDrawable(getContext(), R.drawable.ng_default_icon);
        }
        DashIcon.setBounds(0, 0, (int) dp(108), (int) dp(108));


        DashTitle = StaticLayout.Builder.obtain(item.getTitle(), 0, item.getTitle().length(), DashTitlePainter, (int)dp(108))
                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                .build();

        DashAuthor = StaticLayout.Builder.obtain(item.getAuthor(), 0, item.getAuthor().length(), DashAuthorPainter, (int) dp(108))
                .setAlignment(Layout.Alignment.ALIGN_CENTER)
                .build();

        DashTextBackground.setBounds(0,0,(int) dp(108),(int) dp(54));
        requestLayout();
        invalidate();
    }


    private void establishPainters(){
        DashTitlePainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashTitlePainter.setColor(Color.WHITE);
        DashTitlePainter.setTextSize(sp(20));

        DashAuthorPainter = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        DashAuthorPainter.setColor(Color.WHITE);
        DashAuthorPainter.setTextSize(sp(10));
    }
    private float sp(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private float dp(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
