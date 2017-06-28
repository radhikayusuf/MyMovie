package radhika.yusuf.id.mymovie.ui.detail;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import radhika.yusuf.id.mymovie.api.apiDao.MainData;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class DetailVM {

    private Context mContext;

    public ObservableField<String> bTitle = new ObservableField<>();
    public ObservableField<String> bDesc = new ObservableField<>();
    public ObservableField<String> bReleaseDate = new ObservableField<>();
    public ObservableField<String> bVote = new ObservableField<>();
    public ObservableField<Boolean> bAdult = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> imageUrlBackDrop = new ObservableField<>();

    public DetailVM(Context mContext, MainData data) {
        this.mContext = mContext;
        this.bTitle.set(data.getTitle());
        this.bDesc.set(data.getOverview());
        this.bAdult.set(data.isAdult());
        this.bVote.set(String.valueOf(data.getVote_average()));
        this.bReleaseDate.set("Release date : "+data.getRelease_date());
        this.imageUrl.set(data.getPoster_path());
        this.imageUrlBackDrop.set(data.getBackdrop_path());
    }


    public void closeDetail(View v){
        ((Activity)mContext).finish();
    }


}
