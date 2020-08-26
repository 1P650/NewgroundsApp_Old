package jewpigeon.apps.newgrounds.Views;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.LruCache;

import java.util.LinkedHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.R;
import jewpigeon.apps.newgrounds.Utils.DimensionTool;
import jewpigeon.apps.newgrounds.Views.DashboardData.AutofitGridLayoutManager;
import jewpigeon.apps.newgrounds.Views.DashboardData.AutofitGridDecorator;

public class AutofitGridLayout extends RecyclerView {
    private AutofitGridLayoutManager ColumnManager;
    private AutofitGridDecorator ColumnDecorator;
    private final int DASH_ITEMSIZE_AVERAGE = getResources().getDimensionPixelSize(R.dimen.dashboard_item_size);
    private final int DASH_VERTICAL_SPACING = getResources().getDimensionPixelSize(R.dimen.dashboard_grid_vertical_spacing);
    private int ITEM_SIZE;

    public enum DASH_ITEM {
        AVERAGE, SMALL
    }

    public AutofitGridLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public AutofitGridLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutofitGridLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
       /* int COLUMN_SPACING = 0;
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

        );*/





       boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

       int COLUMN_SPACING = 0;
       int COLUMN_NUM = 0;

       Integer[] cachedValues = SpacingCache.getInstance(getContext()).valuesFor(ITEM_SIZE);

       if(isLandscape){
           COLUMN_SPACING = cachedValues[3];
           COLUMN_NUM = cachedValues[1];

       }
       else {
           COLUMN_SPACING = cachedValues[2];
           COLUMN_NUM = cachedValues[0];
       }

        ColumnManager = new AutofitGridLayoutManager(getContext(), ITEM_SIZE);
        setLayoutManager(ColumnManager);
        ColumnDecorator = new AutofitGridDecorator(COLUMN_NUM, DASH_VERTICAL_SPACING, COLUMN_SPACING);
        addItemDecoration(ColumnDecorator);
    }



    public void setItemDecorator(AutofitGridDecorator decorator){
        if(this.getItemDecorationCount() != 0) return;
        this.ColumnDecorator = decorator;
        addItemDecoration(decorator);
    }






    public AutofitGridLayoutManager getManager(){
        return ColumnManager;
    }



    private static class SpacingCache{
        private static SpacingCache instance;
        private Context context;

        private SpacingCache(Context context){
         this.context = context;
        }

        public static SpacingCache getInstance(Context context){
            if(instance == null) instance = new SpacingCache(context);
            return instance;
        }

        private LruCache<Integer, Integer[]> spacingLru = new LruCache<Integer, Integer[]>(128){
            @Override
            protected Integer[] create(Integer key) {
                int ColumnNumPortrait = 0;
                int ColumnNumLandscape = 0;
                int instanceScreenWidth = 0;
                int instanceScreenHeight = 0;
                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    instanceScreenHeight = DimensionTool.screenHeight();
                    instanceScreenWidth = DimensionTool.screenWidth();

                }
                else {
                    instanceScreenHeight = DimensionTool.screenWidth();
                    instanceScreenWidth = DimensionTool.screenHeight();

                }
                ColumnNumPortrait = DimensionTool.GRID_calcColumsFor(key, instanceScreenWidth, context);
                ColumnNumLandscape = DimensionTool.GRID_calcColumsFor(key, instanceScreenHeight, context);

                return new Integer[]{
                        ColumnNumPortrait,
                        ColumnNumLandscape,
                        DimensionTool.GRID_calcSpacing(key,ColumnNumPortrait,instanceScreenWidth, context),
                        DimensionTool.GRID_calcSpacing(key, ColumnNumLandscape, instanceScreenHeight, context)};

            }
        };

        public Integer[] valuesFor(Integer key){
            return spacingLru.get(key);
        }

    }

}