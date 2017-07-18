package radhika.yusuf.id.mymovie.ui.detail_video;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import radhika.yusuf.id.mymovie.api.ApiClient;
import radhika.yusuf.id.mymovie.api.api_dao.MainData;
import radhika.yusuf.id.mymovie.api.api_dao.VideosData;
import radhika.yusuf.id.mymovie.api.api_dao.VideosResponse;
import radhika.yusuf.id.mymovie.databinding.FragmentVideoBinding;
import radhika.yusuf.id.mymovie.ui.detail_review.ReviewAdapter;
import radhika.yusuf.id.mymovie.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Radhika Yusuf on 12/07/17.
 */

public class VideoVM implements Callback<VideosResponse> {

    private Call<VideosResponse> req;
    private List<VideosData> mData;
    private MainVMInterface callBack;
    private VideosAdapter.VideosInterface callBackVideo;
    private Context mContext;

    public VideosAdapter adapter;
    public LinearLayoutManager layoutManager;
    public ObservableBoolean bNullData = new ObservableBoolean(true);
    public ObservableBoolean bShowProgress = new ObservableBoolean(true);
    public ObservableBoolean bShowButtonRefresh = new ObservableBoolean(false);


    public VideoVM(Context context, MainData data, Context videoActivity) {
        this.callBack = (MainVMInterface) videoActivity;
        this.callBackVideo = (VideosAdapter.VideosInterface) videoActivity;
        mContext = context;
        req = ApiClient.service().getVideos(String.valueOf(data.getId()), Constant.API_KEY);
        req.enqueue(this);
        mData = new ArrayList<>();
        layoutManager = new LinearLayoutManager(context);
        adapter = new VideosAdapter(mData, callBackVideo);
    }

    @Override
    public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
        mData.clear();
        if (response.isSuccessful()) {
            for (VideosData videosData : response.body().getResults()) {
                mData.add(videosData);
            }
            bNullData.set(false);
            bShowButtonRefresh.set(false);
            bShowProgress.set(false);
        }
        adapter.notifyDataSetChanged();
        callBack.onDoneLoadData(true, mData);
    }

    @Override
    public void onFailure(Call<VideosResponse> call, Throwable t) {
        callBack.onDoneLoadData(false, null);
        bNullData.set(true);
        bShowButtonRefresh.set(true);
        bShowProgress.set(false);
    }

    public void onClickRefresh(){
        bShowButtonRefresh.set(false);
        bShowProgress.set(true);
        req.clone().enqueue(this);
    }


    public interface MainVMInterface{
        void onDoneLoadData(boolean success, List<VideosData> data);
    }
}
