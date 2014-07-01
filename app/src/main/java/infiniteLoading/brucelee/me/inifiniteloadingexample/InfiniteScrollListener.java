package infiniteLoading.brucelee.me.inifiniteloadingexample;

import android.util.Log;
import android.widget.AbsListView;

/**
 * Created by Administrator on 2014/7/1.
 */
public abstract class InfiniteScrollListener implements AbsListView.OnScrollListener {
    private int bufferItemsCount = 3;
    private boolean isLoading = false;
    private int itemsCount;
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (isLoading && itemsCount < totalItemCount) {
            itemsCount = totalItemCount;
            isLoading = false;
        }

        if (!isLoading && (firstVisibleItem + visibleItemCount + bufferItemsCount) >= totalItemCount) {
            isLoading = true;
            loadMore();
        }
    }

    public abstract void loadMore();
}
