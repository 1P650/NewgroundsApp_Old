package jewpigeon.apps.newgrounds.Views.DashboardData;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jewpigeon.apps.newgrounds.Views.DashAutofitGrid;

public abstract class DashEndlessScrollListener extends RecyclerView.OnScrollListener {
    private int visibleThreshold = 6;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;

    private boolean loading = true;
    private int startingPageIndex = 0;

    RecyclerView.LayoutManager mLayoutManager;


    public DashEndlessScrollListener(DashAutofitGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();
        if (mLayoutManager instanceof DashAutofitGridLayoutManager) {
            lastVisibleItemPosition = ((DashAutofitGridLayoutManager) mLayoutManager).findLastVisibleItemPosition();

            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }

            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }

            if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                currentPage++;
                onLoadMore(currentPage, totalItemCount, view);
                loading = true;
            }
        }
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

}