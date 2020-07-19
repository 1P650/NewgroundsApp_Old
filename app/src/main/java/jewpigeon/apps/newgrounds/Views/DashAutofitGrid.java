package jewpigeon.apps.newgrounds.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashAutofitGridLayoutManager;
import jewpigeon.apps.newgrounds.Views.DashboardData.DashGridDecorator;

public class DashAutofitGrid extends RecyclerView {
    private DashAutofitGridLayoutManager ColumnManager;
    private DashGridDecorator ColumnDecorator;
    private final int DASH_ITEMSIZE_AVERAGE = getResources().getDimensionPixelSize(R.dimen.dashboard_item_size);
    private final int DASH_ITEMSIZE_SMALL = getResources().getDimensionPixelSize(R.dimen.dashboard_item_size_small);
    private final int DASH_VERTICAL_SPACING = 30;
    public enum DASH_ITEM {
        AVERAGE, SMALL
    }
    public DashAutofitGrid(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public DashAutofitGrid(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DashAutofitGrid(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setSize(DASH_ITEM type){
        int ITEM_SIZE = 0;
        switch (type){
            case SMALL:{
             establish(DASH_ITEMSIZE_SMALL);
            }
            case AVERAGE:{
                establish(DASH_ITEMSIZE_AVERAGE);
            }
        }


    }
    private void init(Context context, AttributeSet attributes){
        if(attributes != null){
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attributes, attrsArray);
            int ITEM_SIZE = array.getDimensionPixelSize(0, DASH_ITEMSIZE_AVERAGE);
            establish(ITEM_SIZE);
            array.recycle();
        }
        else {
            establish(DASH_ITEMSIZE_AVERAGE);
        }


    }

    private void establish(int ITEM_SIZE){
        int COLUMN_NUM = DimensionTool.GRID_calcColumsFor(ITEM_SIZE, getContext());
        ColumnManager = new DashAutofitGridLayoutManager(getContext(), ITEM_SIZE);
        ColumnDecorator = new DashGridDecorator(
                COLUMN_NUM,
                DASH_VERTICAL_SPACING,
                DimensionTool.GRID_calcSpacing(
                        ITEM_SIZE,COLUMN_NUM,getContext()
                )

        );
        setLayoutManager(ColumnManager);
        addItemDecoration(ColumnDecorator);
    }

}
