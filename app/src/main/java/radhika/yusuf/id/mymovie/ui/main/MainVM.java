package radhika.yusuf.id.mymovie.ui.main;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import radhika.yusuf.id.mymovie.R;
import radhika.yusuf.id.mymovie.api.ApiClient;
import radhika.yusuf.id.mymovie.api.apiDao.MainData;
import radhika.yusuf.id.mymovie.api.apiDao.MainResponse;
import radhika.yusuf.id.mymovie.ui.detail.DetailActivity;
import radhika.yusuf.id.mymovie.utils.Constant;
import radhika.yusuf.id.mymovie.utils.MyDialog;
import radhika.yusuf.id.mymovie.utils.MyPrefences;
import radhika.yusuf.id.mymovie.utils.RecyclerViewClickItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Radhika Yusuf on 14/06/17.
 */

public class MainVM implements Callback<MainResponse>,
        RecyclerViewClickItem {
    private Context mContext;
    private List<MainData> mData;
    private Call<MainResponse> req;
    private MainVMInterface callback;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    private static final int FIRST_PAGE = 1;

    public RecyclerView.LayoutManager layoutManager;
    public MainAdapter adapter;
    public RecyclerView.OnScrollListener scrollListener;
    public ObservableField<Boolean> bEmptyRecycler = new ObservableField<>(false);
    public ObservableField<Boolean> statusReq = new ObservableField<>(true);
    private int PAGE_SIZE = 20;
    private int helper = 0;


    public MainVM(Context context, boolean savedData, boolean landScape) {
        this.mContext = context;
        if (landScape) {
            layoutManager = new LinearLayoutManager(mContext);
        } else {
            layoutManager = new GridLayoutManager(mContext, 2);
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == mData.size() ? 2 : 1;
                }
            });
        }

        mData = new ArrayList<>();
        adapter = new MainAdapter(mData, this);
        callback = (MainVMInterface) context;
        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (helper != 0) {
                        callback.onScroll(false);
                        helper = 0;
                    }
                } else {
                    if (helper != 1) {
                        callback.onScroll(true);
                        helper = 1;
                    }
                }

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition;
                if (layoutManager instanceof GridLayoutManager) {
                    firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                } else {
                    firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                }
                if(!isLastPage && !isLoading){
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        isLoading = true;
                        //bAdapter.setLoading(true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                paginate();
                            }
                        }, 1000);
                    }
                }
            }
        };

        if (!savedData) {
            bEmptyRecycler.set(true);
            req = ApiClient.service().getMovie(sortToQuery(MyPrefences.getStateSort(mContext)), Constant.API_KEY, FIRST_PAGE);
            req.enqueue(this);
        }

    }

    private void paginate() {
        int page = (mData.size() / PAGE_SIZE) + FIRST_PAGE;
        req = ApiClient.service().getMovie(sortToQuery(MyPrefences.getStateSort(mContext)), Constant.API_KEY, page);
        req.clone().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                for (MainData mainData : response.body().getResults()) {
                    mData.add(mainData);
                }
                isLoading = response.body().getResults().size() < 20;
                isLastPage = response.body().getResults().size() < 20;
                adapter.setShowLoading(true);
                adapter.notifyDataSetChanged();
                statusReq.set(true);
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                adapter.setShowLoading(false);
                adapter.notifyDataSetChanged();
                statusReq.set(false);
                bEmptyRecycler.set(true);
            }
        });
    }

    public void sort(String item) {
        req = ApiClient.service().getMovie(sortToQuery(item.toLowerCase()), Constant.API_KEY, FIRST_PAGE);
        req.clone().enqueue(this);
    }

    public void refreshThis() {
        req = ApiClient.service().getMovie(sortToQuery(MyPrefences.getStateSort(mContext)), Constant.API_KEY, FIRST_PAGE);
        req.clone().enqueue(this);
    }

    public List<MainData> getData() {
        return mData;
    }

    public void setData(List<MainData> mData) {
        this.mData.addAll(mData);
        Log.wtf("setData: ", String.valueOf(this.mData.size()));
        adapter.notifyDataSetChanged();
    }

    private String sortToQuery(String text) {
        return text.equalsIgnoreCase(mContext.getString(R.string.populart_sort)) ? "popular" : "top_rated";
    }

    @Override
    public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
        mData.clear();
        for (MainData mainData : response.body().getResults()) {
            mData.add(mainData);
        }
        adapter.notifyDataSetChanged();
        bEmptyRecycler.set(false);
        callback.onDoneLoad();
        statusReq.set(true);

    }

    @Override
    public void onFailure(Call<MainResponse> call, Throwable t) {
        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
        callback.onDoneLoad();
        statusReq.set(false);
        bEmptyRecycler.set(true);
    }

    @Override
    public void onClickItem(int position) {
        DetailActivity.startThisActivity(mContext, mData.get(position));
    }


    public interface MainVMInterface {
        void onDoneLoad();

        void onScroll(boolean scroll);
    }
}
