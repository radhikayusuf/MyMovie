package radhika.yusuf.id.mymovie.ui.detail_review;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import radhika.yusuf.id.mymovie.api.ApiClient;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.api.api_dao.MainResponse;
import radhika.yusuf.id.mymovie.api.api_dao.ReviewData;
import radhika.yusuf.id.mymovie.api.api_dao.ReviewResponse;
import radhika.yusuf.id.mymovie.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Radhika Yusuf on 12/07/17.
 */

public class ReviewVM implements Callback<ReviewResponse> {

    private static final int FIRST_PAGE = 1;
    private Call<ReviewResponse> req;
    private List<ReviewData> mData;

    public ReviewAdapter adapter;
    public LinearLayoutManager layoutManager;

    public ObservableField<Boolean> hideRecycler = new ObservableField<>(true);
    public ObservableField<Boolean> showProgress = new ObservableField<>(true);
    public ObservableField<Boolean> showButtonRefresh = new ObservableField<>(false);
    public ObservableField<Boolean> showNoReview = new ObservableField<>(false);

    public ReviewVM(Context context, MainData data) {
        req = ApiClient.service().getReviews(String.valueOf(data.getId()), Constant.API_KEY, FIRST_PAGE);
        req.enqueue(this);
        mData = new ArrayList<>();
        adapter = new ReviewAdapter(mData);
        layoutManager = new LinearLayoutManager(context);
    }

    @Override
    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
        mData.clear();
        if(response.isSuccessful() && response.code() == 200){
            for (ReviewData reviewData : response.body().getResults()) {
                mData.add(reviewData);
            }
            showProgress.set(false);
            hideRecycler.set(false);
            showNoReview.set(mData.size()!=0);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<ReviewResponse> call, Throwable t) {
        showProgress.set(false);
        hideRecycler.set(true);
        showButtonRefresh.set(true);
    }

    public void onClickRefresh(){
        showButtonRefresh.set(false);
        hideRecycler.set(true);
        req.clone().enqueue(this);
    }
}
