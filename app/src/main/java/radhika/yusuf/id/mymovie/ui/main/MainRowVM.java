package radhika.yusuf.id.mymovie.ui.main;

import android.content.Context;
import android.databinding.ObservableField;

import radhika.yusuf.id.mymovie.api.apiDao.MainData;

/**
 * Created by Radhika Yusuf on 16/06/17.
 */

public class MainRowVM {
    private Context mContext;
    private MainData mData;

    public ObservableField<String> bTitle = new ObservableField<>("");
    public ObservableField<String> bDesc = new ObservableField<>("");
    public ObservableField<String> bImage = new ObservableField<>("");
    public ObservableField<Boolean> bAdult = new ObservableField<>(false);

    public MainRowVM(Context mContext, MainData mainData) {
        this.mContext = mContext;
        this.mData = mainData;

        bTitle.set(mData.getTitle());
        bDesc.set(mData.getOverview());
        bImage.set(mData.getPoster_path());
        bAdult.set(mData.isAdult());
    }
}
