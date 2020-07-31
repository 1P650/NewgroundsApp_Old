package jewpigeon.apps.newgrounds.Views;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.LruCache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.PropertyResourceBundle;

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
    private final int DASH_VERTICAL_SPACING = getResources().getDimensionPixelSize(R.dimen.dashboard_grid_vertical_spacing);

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


    private void init(Context context, AttributeSet attributes) {
        if (attributes != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attributes, attrsArray);
            int ITEM_SIZE = array.getDimensionPixelSize(0, DASH_ITEMSIZE_AVERAGE);
            establish(ITEM_SIZE);
            array.recycle();
        } else {
            establish(DASH_ITEMSIZE_AVERAGE);
        }


    }

    private void establish(int ITEM_SIZE) {
        int COLUMN_SPACING = 0;
        int COLUMN_NUM = DimensionTool.GRID_calcColumsFor(ITEM_SIZE, getContext());
        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(Cache.getInstanceFor(ITEM_SIZE).getLru().get(isLandscape)==null){
            COLUMN_SPACING = DimensionTool.GRID_calcSpacing(ITEM_SIZE,COLUMN_NUM,getContext());
            Cache.getInstanceFor(ITEM_SIZE).getLru().put(isLandscape,COLUMN_SPACING);
        }
        else {
            COLUMN_SPACING = Cache.getInstanceFor(ITEM_SIZE).getLru().get(isLandscape);
        }

        ColumnManager = new DashAutofitGridLayoutManager(getContext(), ITEM_SIZE);
        ColumnDecorator = new DashGridDecorator(
                COLUMN_NUM,
                DASH_VERTICAL_SPACING,
            COLUMN_SPACING

        );
        setLayoutManager(ColumnManager);
        addItemDecoration(ColumnDecorator);
    }


    private static class Cache {

        private static LinkedHashMap<Integer,Cache> instances;
        private LruCache<Boolean, Integer> lru;

        private Cache() {

            lru = new LruCache<>(128);

        }

        public static Cache getInstanceFor(int size) {

            if (instances == null || instances.get(size) == null) {
                instances = new LinkedHashMap<>();
                Cache cache = new Cache();
                instances.put(size, cache);
                return cache;
            }
            return instances.get(size);

        }

        public LruCache<Boolean, Integer> getLru() {
            return lru;
        }
    }

}